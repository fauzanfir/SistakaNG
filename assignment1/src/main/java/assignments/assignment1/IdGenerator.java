package assignments.assignment1;

import java.util.HashMap;
import java.util.Scanner;
import java.lang.Object;

public class IdGenerator {
    static HashMap<Character, Integer> charToValue = new HashMap<>(36);
    static char[] valueToChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /*
     * Code pada method main tidak boleh diganti sama sekali!
     */
    public static void main(String[] args) {
        buildMapCharToValue();
        Scanner input = new Scanner(System.in);
        System.out.println("Selamat Datang di Perpustakaan!");

        boolean shouldTerminateProgram = false;

        while (!shouldTerminateProgram) {
            printMenu();
            System.out.print("Menu yang anda pilih: ");
            int menu = input.nextInt();
            input.nextLine();

            if (menu == 1) {
                System.out.print("Program Studi: ");
                String programStudi = input.nextLine();
                System.out.print("Angkatan: ");
                String angkatan = input.nextLine();
                System.out.print("Tanggal Lahir: ");
                String tanggalLahir = input.nextLine();

                System.out.println(generateId(programStudi, angkatan, tanggalLahir));
            } else if (menu == 2) {
                System.out.print("ID Anggota: ");
                String idAnggota = input.nextLine();

                System.out.print("Validitas: ");
                System.out.println(checkValidity(idAnggota));
            } else {
                shouldTerminateProgram = true;
                System.out.println("Sampai jumpa!");
            }
        }

        input.close();
    }

    /*
     * Method buildMapCodeToNumber adalah method untuk membuat mapping reference numbers Code 93
     */
    public static void buildMapCharToValue() {
        for (int count = 0; count < valueToChar.length; count++) {
            charToValue.put(valueToChar[count], count);
        }
    }

    /*
     * Method getCharFromValue adalah method yang akan mengembalikan Code 93 dari value yang diberikan
     */
    private static char getCharFromValue(int value) {
        return valueToChar[value];
    }

    /*
     * Method getValueFromChar adalah method yang akan mengembalikan value dari Code 93 yang diberikan
     */
    private static int getValueFromChar(char character) {
        return charToValue.get(character);
    }

    private static void printMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("Menu yang tersedia:");
        System.out.println("1. Generate ID anggota untuk anggota baru");
        System.out.println("2. Check validitas ID anggota");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
    }

    
     // Method generateId adalah method untuk membuat ID keanggotaan perpustakaan
    public static String generateId(String programStudi, String angkatan, String tanggalLahir){
        String ID = "";
        if (programStudi.equals("SSI") || programStudi.equals("SIK") ||     // Jika input programStudi nya SSI,DIK,MIK,MTI,DIK
            programStudi.equals("MIK") || programStudi.equals("DIK") ||
            programStudi.equals("MTI")){
            ID += programStudi;             // String ID akan menambahkannya sebagai 3 huruf awal ID
            }
        else{
            return "Input tidak valid!";        // Jika input buka programstudi yang ada di fasilkom
        }

        if (2000 <= Integer.parseInt(angkatan) && Integer.parseInt(angkatan) <= 2021){
            ID += angkatan.substring(2,4);          // Jika input angkatan diantara 2020 dan 2021 inklusif,
        }                                           // maka 2 angka terakhirnya akan ditambahkan ke ID
        else{
            return "Input tidak valid!";
        }
        if (tanggalLahir.length() == 10 &&                              // Jika panjang String tanggal lahir = 10
            tanggalLahir.charAt(2) == '/' &&                            // Jika terdapat format dd/mm/yyy
            tanggalLahir.charAt(5) == '/' &&
            Integer.parseInt(tanggalLahir.substring(0,2)) <= 30 &&      // Jika input tanggal, bulan, dan tahun adalah valid
            Integer.parseInt(tanggalLahir.substring(3,5)) <= 12 &&
            Integer.parseInt(tanggalLahir.substring(6,10)) <= 2021){
                ID += tanggalLahir.substring(0,2) +                     // Menambahkan ID dengan nomor tanggal lahir
                      tanggalLahir.substring(3,5) +
                      tanggalLahir.substring(8, 10);
            }
        else{
            return "Input tidak valid!";
        }
        ID += getCharFromValue(getCheckSumChar(ID));                // Memanggil method getCharFromValue()
        ID += getCharFromValue(getCheckSumChar(ID));                // dengan parameter method getCheckSumChar()

        return "ID Anggota: " + ID;
    }
    public static int getCheckSumChar(String ID) {
        int checksum = 0;                                       // Menginisiasi value 0 ke checksum
        for (int i = 0;i < ID.length();i++){
            checksum += (ID.length() - i) * getValueFromChar(ID.charAt(i));     // checksum = bobot * valueChar
        }
        return checksum % 36;               // Hasilnya akan di modulo dengan 36
    }

    /*
     * Method checkValidity adalah method untuk mengecek validitas ID keanggotaan perpustakaan
     * Parameter dan return type dari method ini tidak boleh diganti
     */
    public static boolean checkValidity(String idAnggota) {
        for (int i = 0; i < idAnggota.length(); i++){
            if (!Character.isUpperCase(idAnggota.charAt(i)) ||      // Jika huruf pada input ada yang tidak kapital,
                idAnggota.length() != 13){                          // atau panjang ID tidak 13
                return false;
            }
        }
        if (idAnggota.charAt(idAnggota.length()-2) !=                       // Jika huruf pertama dan kedua dari terakhir tidak sesuai dengan 
            getCharFromValue(getCheckSumChar(idAnggota.substring(0,idAnggota.length()-2))) &&   // aturan pada checksum
            idAnggota.charAt(idAnggota.length()-1) != 
            getCharFromValue(getCheckSumChar(idAnggota.substring(0,idAnggota.length()-1)))){
                return false;
            }
        return true;
    }
}
