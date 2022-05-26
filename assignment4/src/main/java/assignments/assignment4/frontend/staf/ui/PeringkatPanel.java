package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class PeringkatPanel extends SistakaPanel {
    JLabel peringkat = new JLabel();
    public PeringkatPanel(HomeGUI main) {
        super(main);
        setLayout(null);
        JLabel judul = new JLabel("Peringkat");
        JButton kembali = new JButton("Kembali");

        judul.setFont(new Font("Times", Font.BOLD, 20));
        kembali.setFont(new Font("Times", Font.PLAIN, 11));

        judul.setBounds(200, 30, 200, 50);
        peringkat.setBounds(50, 0, 450, 600);
        kembali.setBounds(200, 500, 175, 30);

        add(judul);
        add(peringkat);
        add(kembali);
        
        kembali.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("staf");
            }
        });
    }

    @Override
    public void refresh() {
        peringkat.setText(SistakaNG.handleRankingAnggota());
    }
}
