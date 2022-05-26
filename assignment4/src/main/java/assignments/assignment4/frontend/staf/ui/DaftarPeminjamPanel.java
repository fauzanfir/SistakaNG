package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class DaftarPeminjamPanel extends SistakaPanel {
    JComboBox<String> buku;

    JLabel detail;

    public DaftarPeminjamPanel(HomeGUI main) {
        super(main);
        setLayout(null);

        JLabel judul = new JLabel("Lihat Daftar Peminjam");
        JLabel label1 = new JLabel("Pilih buku");
        buku = new JComboBox<>();
        JButton lihat = new JButton("Lihat");
        JButton kembali = new JButton("Kembali");

        judul.setFont(new Font("Times", Font.BOLD, 20));
        label1.setFont(new Font("Times", Font.PLAIN, 11));
        buku.setFont(new Font("Times", Font.PLAIN, 11));
        lihat.setFont(new Font("Times", Font.PLAIN, 11));
        kembali.setFont(new Font("Times", Font.PLAIN, 11));

        judul.setBounds(80, 0, 250, 50);
        label1.setBounds(50, 100, 175, 30);
        buku.setBounds(225, 100, 175, 30);   
        lihat.setBounds(50, 500, 175, 30);
        kembali.setBounds(225, 500, 175, 30);

        add(judul);
        add(buku);
        add(label1);
        add(lihat);
        add(kembali);

        JPanel panel = new JPanel();
        detail = new JLabel();
        detail.setText("");

        JScrollPane scrollPanel = new JScrollPane(panel);
        scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.setBounds(50, 150, 350, 330);
        add(scrollPanel);

        panel.add(detail);
        lihat.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String inputBuku = (String)buku.getSelectedItem();
                if(inputBuku != null){
                    String[] temp = inputBuku.split(" oleh ");
                    Buku iniBuku = SistakaNG.findBuku(temp[0], temp[1]);
                    String bukuPinjam = SistakaNG.daftarPeminjam(iniBuku);
                    detail.setText(bukuPinjam);
                }
                else{
                    showWarning("Silahkan memilih ID anggota!");
                }
                //area.setText(iniAnggota.detail());
                //detail.setText(iniAnggota.detail());
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
        // area.setText("");
        detail.setText("");
        if(buku != null){
            buku.removeAllItems();
            for(Buku book : SistakaNG.getDaftarBuku()){
                buku.addItem(book.toString());
            }
        }
    }
}
