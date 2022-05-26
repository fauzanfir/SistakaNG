package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class AnggotaHomePanel extends SistakaPanel {
    JLabel welcome = new JLabel();
    public AnggotaHomePanel(HomeGUI main) {
        super(main);
        setLayout(null);
        setSize(450, 600);

        String label4 = "<html>" + "Peminjaman" +  "</html>";
        String label5 = "<html>" + "Pengembalian" + "</html>";
        String label6 = "<html>" + "Pembayaran" + "<br>" + "Denda" + "</html>";
        String label7 = "<html>" + "Detail" + "<br>" + "Anggota" + "</html>";

        JButton peminjaman = new JButton(label4);
        JButton pengembalian = new JButton(label5);
        JButton pembayaran = new JButton(label6);
        JButton detailAnggota = new JButton(label7);
        JButton logout = new JButton("Logout");

        peminjaman.setFont(new Font("Times", Font.PLAIN, 10));
        pengembalian.setFont(new Font("Times", Font.PLAIN, 10));
        pembayaran.setFont(new Font("Times", Font.PLAIN, 11));
        detailAnggota.setFont(new Font("Times", Font.PLAIN, 11));
        logout.setFont(new Font("Times", Font.PLAIN, 11));

        peminjaman.setBounds(125, 150, 100, 100);
        pengembalian.setBounds(225, 150, 100, 100);
        pembayaran.setBounds(125, 250, 100, 100);
        detailAnggota.setBounds(225, 250, 100, 100);
        logout.setBounds(125, 370, 200, 50);

        add(welcome);
        add(peminjaman);
        add(pembayaran);
        add(pengembalian);
        add(detailAnggota);
        add(logout);

        peminjaman.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("peminjaman");
            }
        });

        pengembalian.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("pengembalian");
            }
        });

        pembayaran.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("pembayaran");
            }
        });

        detailAnggota.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("detailUser");
            }
        });

        logout.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("login");
            }
        });

    }

    @Override
    public void refresh() {
        
        welcome.setText(String.format("Selamat Datang Kembali %s!", main.getUser().getNama()));
        welcome.setFont(new Font("Times", Font.BOLD, 15));
        welcome.setBounds(70, -50, 400, 200);

        add(welcome);
    }

}
