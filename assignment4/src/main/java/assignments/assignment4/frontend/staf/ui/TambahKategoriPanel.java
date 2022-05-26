package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Kategori;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class TambahKategoriPanel extends SistakaPanel {
    JTextField nama, poin;
    public TambahKategoriPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        setLayout(null);

        JLabel judul = new JLabel("Tambah Kategori");
        JLabel label1 = new JLabel("Nama :");
        JLabel label2 = new JLabel("Poin :");
        nama = new JTextField();
        poin = new JTextField();
        JButton tambah = new JButton("Tambah");
        JButton kembali = new JButton("Kembali");

        judul.setFont(new Font("Times", Font.BOLD, 20));
        nama.setFont(new Font("Times", Font.PLAIN, 11));
        label1.setFont(new Font("Times", Font.PLAIN, 11));
        label2.setFont(new Font("Times", Font.PLAIN, 11));
        poin.setFont(new Font("Times", Font.PLAIN, 11));
        tambah.setFont(new Font("Times", Font.PLAIN, 11));
        kembali.setFont(new Font("Times", Font.PLAIN, 11));

        judul.setBounds(120, 50, 250, 50);
        label1.setBounds(50, 200, 175, 30);
        nama.setBounds(225, 200, 175, 30);
        label2.setBounds(50, 250, 175, 30);
        poin.setBounds(225, 250, 175, 30);   
        tambah.setBounds(50, 300, 175, 30);
        kembali.setBounds(225, 300, 175, 30);

        add(judul);
        add(nama);
        add(label1);
        add(tambah);
        add(kembali);
        add(label2);
        add(poin);

        tambah.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
            String inputName = nama.getText();
            String inputPoin = poin.getText();

            if(!inputName.equals("") && !inputPoin.equals("") && isNumeric(inputPoin)){
                int poinKtg = Integer.parseInt(inputPoin);
                Kategori tempKat = SistakaNG.findKategori(inputName);
                if(tempKat == null){
                    Kategori ktg = SistakaNG.addKategori(inputName, poinKtg);
                    showInfo(String.format("Kategori %s dengan poin %d\nberhasil ditambahkan", ktg.getNama(), ktg.getPoin()));
                    main.setPanel("staf");
                }
                else{
                    showWarning(String.format("Kategori %s sudah pernah ditambahkan", tempKat.getNama()));
                }
            }
            else{
                showWarning("Tidak dapat menambahkan Kategori,\nsilahkan periksa kembali input Anda!");
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
        poin.setText("");
    }
}
