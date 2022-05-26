package assignments.assignment4.frontend;

import java.awt.Color;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.*;

public abstract class SistakaPanel extends JPanel {

    protected final HomeGUI main;

    public SistakaPanel(HomeGUI main) {
        this.main = main;
        // Menambahkan warna pada setiap komponen
        setBorder(new EmptyBorder(10,10,10,10));
        setBackground(new ColorUIResource(Color.decode("#E4CE7D")));
        UIManager.put("Panel.background", new ColorUIResource(Color.decode("#E4CE7D")));
        UIManager.put("Label.foreground", new ColorUIResource(Color.decode("#D0635C")));
        UIManager.put("Button.foreground", new ColorUIResource(Color.decode("#E4CE7D")));
        UIManager.put("TextField.foreground", new ColorUIResource(Color.decode("#D0635C")));
        UIManager.put("ComboBox.foreground", new ColorUIResource(Color.decode("#D0635C")));

    }

    public abstract void refresh();


    // Mengecek apakah angka
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    // Menunjukkan panel message
    public void showWarning(String text){
        JOptionPane.showMessageDialog(this, text, "Warning!",JOptionPane.WARNING_MESSAGE);
    }

    public void showInfo(String text){
        JOptionPane.showMessageDialog(this, text, "Info!",JOptionPane.INFORMATION_MESSAGE);
    }

    // Mengecek input tanggal
    public static boolean isDateValid(String tanggal){
        String[] arrOfTanggalLahir = tanggal.split("/");

        // Cek apakah ada 3 input (untuk dd, mm, dan yyyy)
        if (arrOfTanggalLahir.length != 3) {
            return false;
        }

        // Cek apakah semuanya numerik
        for (String s : arrOfTanggalLahir) {
            if (!isNumeric(s)) {
                return false;
            }
        }

        // Cek apakah jumlah digitnya memenuhi aturan (2 digit untuk tanggal dan bulan, serta 4 digit untuk year)
        if (arrOfTanggalLahir[0].length() != 2 || arrOfTanggalLahir[1].length() != 2 || arrOfTanggalLahir[2].length() != 4) {
            return false;
        }

        // Cek apakah memenuhi aturan tanggal (tanggal antara 1 - 31)
        int hari = Integer.parseInt(arrOfTanggalLahir[0]);
        if (hari < 1 || hari > 31) {
            return false;
        }

        // Cek apakah memenuhi aturan bulan (bulan antara 1 - 12)
        int bulan = Integer.parseInt(arrOfTanggalLahir[1]);
        return bulan >= 1 && bulan <= 12;
    }
}
