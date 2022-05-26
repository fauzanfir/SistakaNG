package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Anggota;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


// TODO: Implementasikan hal-hal yang diperlukan
public class DetailAnggotaPanel extends SistakaPanel {
    JComboBox<String> anggota;
    JLabel detail;
    public DetailAnggotaPanel(HomeGUI main) {
        super(main);

        // Meng-set layout, menambahkan, mengatur posisi, dan membuat komponen
        setLayout(null);

        JLabel judul = new JLabel("Lihat Detail Anggota");
        JLabel label1 = new JLabel("Pilih ID anggota");
        anggota = new JComboBox<>();
        JButton lihat = new JButton("Lihat");
        JButton kembali = new JButton("Kembali");

        judul.setFont(new Font("Times", Font.BOLD, 20));
        label1.setFont(new Font("Times", Font.PLAIN, 11));
        anggota.setFont(new Font("Times", Font.PLAIN, 11));
        lihat.setFont(new Font("Times", Font.PLAIN, 11));
        kembali.setFont(new Font("Times", Font.PLAIN, 11));

        judul.setBounds(80, 0, 250, 50);
        label1.setBounds(50, 100, 175, 30);
        anggota.setBounds(225, 100, 175, 30);   
        lihat.setBounds(50, 500, 175, 30);
        kembali.setBounds(225, 500, 175, 30);

        add(judul);
        add(anggota);
        add(label1);
        add(lihat);
        add(kembali);

        // Menambahkan panel yang dapat di scroll jika dibutuhkan
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
                String inputAnggota = (String) anggota.getSelectedItem();
                Anggota iniAnggota = SistakaNG.findAnggota(inputAnggota);
                if(inputAnggota != null){
                    detail.setText(iniAnggota.detail());        // Memasukkan detail pada panel scroll
                }
                else{
                    showWarning("Silahkan memilih ID anggota!");
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
        anggota.setSelectedItem("");
        // area.setText("");
        detail.setText("");
        if(anggota != null){
            anggota.removeAllItems();
            for(Anggota angg : SistakaNG.getDaftarAnggota()){
                anggota.addItem(angg.getId());
            }
        }
    }
}
