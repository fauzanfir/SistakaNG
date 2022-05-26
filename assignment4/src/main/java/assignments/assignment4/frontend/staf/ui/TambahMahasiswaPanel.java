package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Mahasiswa;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TambahMahasiswaPanel extends SistakaPanel {
    String[] program = {"SIK", "SSI", "MIK", "MTI", "DIK"};
    JTextField nama, tgl, akt;
    JComboBox<String> progStud;

    public TambahMahasiswaPanel(HomeGUI main) {
        super(main);
        setLayout(null);

        // Meng-set layout, menambahkan, mengatur posisi, dan membuat komponen
        JLabel judul = new JLabel("Tambah Mahasiswa");
        JLabel label1 = new JLabel("Nama :");
        JLabel label2 = new JLabel("Tanggal Lahir (DD/MM/YYYY) :");
        JLabel label3 = new JLabel("Program Studi :");
        JLabel label4 = new JLabel("Angkatan :");
        nama = new JTextField();
        tgl = new JTextField();
        akt = new JTextField();
        progStud = new JComboBox<>(program);
        JButton tambah = new JButton("Tambah");
        JButton kembali = new JButton("Kembali");

        judul.setFont(new Font("Times", Font.BOLD, 20));
        label1.setFont(new Font("Times", Font.PLAIN, 11));
        label2.setFont(new Font("Times", Font.PLAIN, 11));
        label3.setFont(new Font("Times", Font.PLAIN, 11));
        label4.setFont(new Font("Times", Font.PLAIN, 11));
        nama.setFont(new Font("Times", Font.PLAIN, 11));
        tgl.setFont(new Font("Times", Font.PLAIN, 11));
        akt.setFont(new Font("Times", Font.PLAIN, 11));
        progStud.setFont(new Font("Times", Font.PLAIN, 11));
        tambah.setFont(new Font("Times", Font.PLAIN, 11));
        kembali.setFont(new Font("Times", Font.PLAIN, 11));

        judul.setBounds(120, 50, 250, 50);
        label1.setBounds(50, 100, 175, 30);
        nama.setBounds(225, 100, 175, 30);
        label2.setBounds(50, 150, 175, 30);
        tgl.setBounds(225, 150, 175, 30);
        label3.setBounds(50, 200, 175, 30);
        progStud.setBounds(225, 200, 175, 30);
        label4.setBounds(50, 250, 175, 30);
        akt.setBounds(225, 250, 175, 30);
        tambah.setBounds(50, 300, 175, 30);
        kembali.setBounds(225, 300, 175, 30);

        add(judul);
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(nama);
        add(tgl);
        add(akt);
        add(progStud);
        add(tambah);
        add(kembali);

        tambah.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
            String inputName = nama.getText();      // Mengambil input pada texfield
            String inputTgl = tgl.getText();
            String inputAkt = akt.getText();
            String inputPs = (String)progStud.getSelectedItem();

            if(isNumeric(inputAkt) && isDateValid(inputTgl)){           // Jika input sesuai
                Mahasiswa mhs = SistakaNG.addMahasiswa(inputName, inputTgl, inputPs, inputAkt);
                if(mhs != null){           // Jika berhasil membuat object mahasiswa
                    showInfo(String.format("Berhasil menambahkan mahasiswa dengan ID %s", mhs.getId()));
                    main.setPanel("staf");
                }
                else{
                    showWarning("Tidak dapat menambahkan mahasiswa,\nsilahkan periksa kembali input Anda!");
                }
            }
            else{
                showWarning("Tidak dapat menambahkan mahasiswa,\nsilahkan periksa kembali input Anda!");
            }
            }
        });

        kembali.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("staf");
            }
        });
    }

    @Override
    public void refresh() {
        nama.setText("");
        tgl.setText("");
        akt.setText("");
        progStud.setSelectedItem("");

    }
}
