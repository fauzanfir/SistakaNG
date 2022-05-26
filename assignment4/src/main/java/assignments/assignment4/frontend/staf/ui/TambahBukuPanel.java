package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.backend.buku.Kategori;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class TambahBukuPanel extends SistakaPanel {
    JTextField judulBuku, penulis, penerbit, stok;
    JComboBox<String> kategori;
    public TambahBukuPanel(HomeGUI main) {
        super(main);
        setLayout(null);

        JLabel judul = new JLabel("Tambah Buku");
        JLabel label1 = new JLabel("Judul : ");
        JLabel label2 = new JLabel("Penulis :");
        JLabel label3 = new JLabel("Penerbit :");
        JLabel label4 = new JLabel("Kategori :");
        JLabel label5 = new JLabel("Stok :");
        judulBuku = new JTextField();
        penulis = new JTextField();
        penerbit = new JTextField();
        stok = new JTextField();
        kategori = new JComboBox<>();

        JButton tambah = new JButton("Tambah");
        JButton kembali = new JButton("Kembali");

        judul.setFont(new Font("Times", Font.BOLD, 20));
        label1.setFont(new Font("Times", Font.PLAIN, 11));
        label2.setFont(new Font("Times", Font.PLAIN, 11));
        label3.setFont(new Font("Times", Font.PLAIN, 11));
        label4.setFont(new Font("Times", Font.PLAIN, 11));
        label5.setFont(new Font("Times", Font.PLAIN, 11));
        judulBuku.setFont(new Font("Times", Font.PLAIN, 11));
        penulis.setFont(new Font("Times", Font.PLAIN, 11));
        penerbit.setFont(new Font("Times", Font.PLAIN, 11));
        stok.setFont(new Font("Times", Font.PLAIN, 11));
        kategori.setFont(new Font("Times", Font.PLAIN, 11));
        tambah.setFont(new Font("Times", Font.PLAIN, 11));
        kembali.setFont(new Font("Times", Font.PLAIN, 11));

        judul.setBounds(120, 50, 250, 50);
        label1.setBounds(50, 100, 175, 30);
        judulBuku.setBounds(225, 100, 175, 30);
        label2.setBounds(50, 150, 175, 30);
        penulis.setBounds(225, 150, 175, 30);
        label3.setBounds(50, 200, 175, 30);
        penerbit.setBounds(225, 200, 175, 30);
        label4.setBounds(50, 250, 175, 30);
        kategori.setBounds(225, 250, 175, 30);
        label5.setBounds(50, 300, 175, 30);
        stok.setBounds(225, 300, 175, 30);
        tambah.setBounds(50, 350, 175, 30);
        kembali.setBounds(225, 350, 175, 30);

        add(judul);
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(judulBuku);
        add(penulis);
        add(penerbit);
        add(stok);
        add(kategori);
        add(tambah);
        add(kembali);

        tambah.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
            String inputJudul = judulBuku.getText();
            String inputPenulis = penulis.getText();
            String inputPenerbit = penerbit.getText();
            String inputStok = stok.getText();
            String inputKat = (String)kategori.getSelectedItem();
            Buku iniBuku = SistakaNG.findBuku(inputJudul, inputPenulis);

            if(!inputJudul.equals("") && !inputPenulis.equals("") && 
            inputKat != null && !inputPenerbit.equals("") && isNumeric(inputStok)){
                int stokBuku = Integer.parseInt(inputStok);
                if(stokBuku <= 0){
                    showWarning("Stok harus lebih dari 0!");
                }
                else if(iniBuku != null){
                    showWarning(String.format("Buku %s oleh %s\nsudah pernah diterbitkan", iniBuku.getJudul(), iniBuku.getPenulis()));
                }
                else{
                    Buku buku = SistakaNG.addBuku(inputJudul, inputPenulis, inputPenerbit, inputKat, stokBuku);
                    showInfo(String.format("Buku %s oleh  %s\nberhasil ditambahkan", buku.getJudul(), buku.getPenulis()));
                    main.setPanel("staf");
                }
            }
            else{
                showWarning("Tidak dapat menambahkan Buku,\nsilahkan periksa kembali input Anda!");
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
        judulBuku.setText("");
        penulis.setText("");
        penerbit.setText("");
        stok.setText("");
        kategori.setSelectedItem("");

        if(kategori != null){
            kategori.removeAllItems();
            for(Kategori ktg : SistakaNG.getDaftarKategori()){
                kategori.addItem(ktg.getNama());
            }
        }
    }
}
