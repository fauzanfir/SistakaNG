package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


// TODO: Implementasikan hal-hal yang diperlukan
public class PengembalianPanel extends SistakaPanel {
    JComboBox<String> buku;
    JTextField tgl;

    public PengembalianPanel(HomeGUI main) {
        super(main);
        setLayout(null);
        
        JLabel judul = new JLabel("Pengembalian Buku");
        JLabel label1 = new JLabel("Buku : ");
        JLabel label2 = new JLabel("Tanggal Peminjaman (DD/MM/YYYY) :");
        buku = new JComboBox<>();
        tgl = new JTextField();
        JButton pinjam = new JButton("Pinjam");
        JButton kembali = new JButton("Kembali");

        judul.setFont(new Font("Times", Font.BOLD, 20));
        label1.setFont(new Font("Times", Font.PLAIN, 11));
        label2.setFont(new Font("Times", Font.PLAIN, 11));
        tgl.setFont(new Font("Times", Font.PLAIN, 11));
        buku.setFont(new Font("Times", Font.PLAIN, 11));
        pinjam.setFont(new Font("Times", Font.PLAIN, 11));
        kembali.setFont(new Font("Times", Font.PLAIN, 11));


        judul.setBounds(120, 50, 250, 50);
        label1.setBounds(50, 200, 175, 30);
        buku.setBounds(225, 200, 175, 30);
        label2.setBounds(50, 250, 175, 30);
        tgl.setBounds(225, 250, 175, 30);   
        pinjam.setBounds(50, 300, 175, 30);
        kembali.setBounds(225, 300, 175, 30);

        add(judul);
        add(buku);
        add(label1);
        add(label2);
        add(tgl);
        add(pinjam);
        add(kembali);

        pinjam.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
            String inputBuku = (String)buku.getSelectedItem();
            String tanggal = tgl.getText();
            
            if(inputBuku == null){
                showWarning("Silahkan memilih buku!");
            }
            else{
                if(!isDateValid(tanggal)){
                    showWarning("Tanggal yang dimasukkan harus dalam format DD/MM/YYYY");
                }
                else{
                    String[] temp = inputBuku.split(" oleh ");
                    Buku iniBuku = SistakaNG.findBuku(temp[0], temp[1]);
                    String temp2 = SistakaNG.kembalikanBuku(iniBuku, tanggal);
                    showInfo(temp2);
                }
            }
            }
        });

        kembali.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("anggota");
            }
        });
    }

    @Override
    public void refresh() {
        buku.setSelectedItem("");
        tgl.setText("");

        if(buku != null){
            buku.removeAllItems();
            for(Buku book : SistakaNG.getDaftarBuku()){
                buku.addItem(book.toString());
            }
        }
    }
}
