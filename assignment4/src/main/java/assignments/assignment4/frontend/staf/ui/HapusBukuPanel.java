package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HapusBukuPanel extends SistakaPanel {
    JComboBox<String> buku;
    public HapusBukuPanel(HomeGUI main) {
        super(main);
        setLayout(null);
        
        // Meng-set layout, menambahkan, mengatur posisi, dan membuat komponen
        JLabel judul = new JLabel("Hapus Buku");
        JLabel label1 = new JLabel("Judul : ");
        buku = new JComboBox<>();
        JButton hapus = new JButton("Hapus");
        JButton kembali = new JButton("Kembali");

        judul.setFont(new Font("Times", Font.BOLD, 20));
        label1.setFont(new Font("Times", Font.PLAIN, 11));
        buku.setFont(new Font("Times", Font.PLAIN, 11));
        hapus.setFont(new Font("Times", Font.PLAIN, 11));
        kembali.setFont(new Font("Times", Font.PLAIN, 11));

        judul.setBounds(120, 50, 250, 50);
        label1.setBounds(50, 250, 175, 30);
        buku.setBounds(225, 250, 175, 30);   
        hapus.setBounds(50, 300, 175, 30);
        kembali.setBounds(225, 300, 175, 30);

        add(judul);
        add(buku);
        add(label1);
        add(hapus);
        add(kembali);

        hapus.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
            String inputBuku = (String)buku.getSelectedItem();

            if(inputBuku == null){
                showWarning("Silahkan memilih buku!");      // Jika combobox tidak memilih buku apapun
            }
            else{  
                String[] temp = inputBuku.split(" oleh ");      // Memisahkan String toString pada buku
                Buku iniBuku = SistakaNG.findBuku(temp[0], temp[1]);
                String temp2 = SistakaNG.deleteBuku(iniBuku);

                buku.removeItem(inputBuku);
                showInfo(temp2);
                main.setPanel("staf");
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
        buku.setSelectedItem("");

        if(buku != null){
            buku.removeAllItems();
            for(Buku book : SistakaNG.getDaftarBuku()){
                buku.addItem(book.toString());
            }
        }
    }
}
