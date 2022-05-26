package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class PembayaranPanel extends SistakaPanel {
    JTextField jumlah;
    public PembayaranPanel(HomeGUI main) {
        super(main);
        setLayout(null);

        JLabel judul = new JLabel("Bayar Denda");
        JLabel label1 = new JLabel("Jumlah Denda :");
        jumlah = new JTextField();
        JButton bayar = new JButton("Bayar");
        JButton kembali = new JButton("Kembali");

        judul.setFont(new Font("Times", Font.BOLD, 20));
        jumlah.setFont(new Font("Times", Font.PLAIN, 11));
        label1.setFont(new Font("Times", Font.PLAIN, 11));
        bayar.setFont(new Font("Times", Font.PLAIN, 11));
        kembali.setFont(new Font("Times", Font.PLAIN, 11));

        judul.setBounds(120, 50, 250, 50);
        label1.setBounds(50, 250, 175, 30);
        jumlah.setBounds(225, 250, 175, 30);   
        bayar.setBounds(50, 300, 175, 30);
        kembali.setBounds(225, 300, 175, 30);

        add(judul);
        add(jumlah);
        add(label1);
        add(bayar);
        add(kembali);

        bayar.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
            String inputJumlah = jumlah.getText();
            if(!isNumeric(inputJumlah)){
                showWarning("Jumlah bayar harus berupa angka");
            }
            else{
                String info = SistakaNG.bayarDenda(Long.parseLong(inputJumlah));
                showInfo(info);
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
        jumlah.setText("");
    }
}
