package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Anggota;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DetailUserPanel extends SistakaPanel {
    JLabel detail;
    public DetailUserPanel(HomeGUI main) {
        super(main);
        setLayout(null);


        JLabel judul = new JLabel("Lihat Detail Anggota");

        JButton lihat = new JButton("Lihat");
        JButton kembali = new JButton("Kembali");

        judul.setFont(new Font("Times", Font.BOLD, 20));
        kembali.setFont(new Font("Times", Font.PLAIN, 11));

        judul.setBounds(80, 0, 250, 50);
        kembali.setBounds(225, 500, 175, 30);

        add(judul);
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

        kembali.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("anggota");
            }
        });
    }

    @Override
    public void refresh() {
        detail.setText(((Anggota)SistakaNG.getPenggunaLoggedIn()).detail());
    }
}
