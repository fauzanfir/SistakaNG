package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Dosen;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class TambahDosenPanel extends SistakaPanel {
    JTextField nama;
    public TambahDosenPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        setLayout(null);

        JLabel judul = new JLabel("Tambah Dosen");
        JLabel label1 = new JLabel("Nama :");
        nama = new JTextField();
        JButton tambah = new JButton("Tambah");
        JButton kembali = new JButton("Kembali");

        judul.setFont(new Font("Times", Font.BOLD, 20));
        nama.setFont(new Font("Times", Font.PLAIN, 11));
        label1.setFont(new Font("Times", Font.PLAIN, 11));
        tambah.setFont(new Font("Times", Font.PLAIN, 11));
        kembali.setFont(new Font("Times", Font.PLAIN, 11));

        judul.setBounds(120, 50, 250, 50);
        label1.setBounds(50, 250, 175, 30);
        nama.setBounds(225, 250, 175, 30);   
        tambah.setBounds(50, 300, 175, 30);
        kembali.setBounds(225, 300, 175, 30);

        add(judul);
        add(nama);
        add(label1);
        add(tambah);
        add(kembali);

        tambah.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
            String inputName = nama.getText();

            if(!inputName.equals("")){
                Dosen dosen = SistakaNG.addDosen(inputName);
                if(dosen != null){
                    // ArrayList<Anggota> temp = SistakaNG.getDaftarAnggota();
                    // temp.add(dosen);
                    // SistakaNG.setDaftarAnggota(temp);
                    showInfo(String.format("Berhasil menambahkan Dosen dengan ID %s", dosen.getId()));
                    main.setPanel("staf");
                }
                else{
                    showWarning("Tidak dapat menambahkan Dosen,\nsilahkan periksa kembali input Anda!");
                }
            }
            else{
                showWarning("Tidak dapat menambahkan ,\nsilahkan periksa kembali input Anda!");
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
    }
}
