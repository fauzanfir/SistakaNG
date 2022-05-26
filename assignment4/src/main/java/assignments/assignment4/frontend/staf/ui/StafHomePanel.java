package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class StafHomePanel extends SistakaPanel {
    JLabel welcome = new JLabel();
    public StafHomePanel(HomeGUI main) {
        super(main);
        setLayout(null);
        setSize(450, 600);
        
        String label0 = "<html>" + "Tambah" + "<br>" + "Mahasiswa" + "</html>";
        String label1 = "<html>" + "Tambah" + "<br>" + "Dosen" + "</html>";
        String label2 = "<html>" + "Tambah" + "<br>" + "Kategori" + "</html>";
        String label3 = "<html>" + "Tambah" + "<br>" + "Buku" + "</html>";
        String label4 = "<html>" + "Hapus" + "<br>" + "Buku" + "</html>";
        String label5 = "<html>" + "3 Peringkat" + "<br>" + "Pertama" + "</html>";
        String label6 = "<html>" + "Detail" + "<br>" + "Anggota" + "</html>";
        String label7 = "<html>" + "Daftar" + "<br>" + "Peminjaman Buku" + "</html>";
        // I got this idea from https://www.roseindia.net/java/example/java/swing/MultilineLabelButton.shtml

        JButton addMhs = new JButton(label0);
        JButton addDos = new JButton(label1);
        JButton addKtg = new JButton(label2);
        JButton addBook = new JButton(label3);
        JButton delBook = new JButton(label4);
        JButton topThree = new JButton(label5);
        JButton detail = new JButton(label6);
        JButton borrowedBook = new JButton(label7);
        JButton logout = new JButton("Logout");

        addMhs.setFont(new Font("Times", Font.PLAIN, 11));
        addDos.setFont(new Font("Times", Font.PLAIN, 11));
        addKtg.setFont(new Font("Times", Font.PLAIN, 11));
        addBook.setFont(new Font("Times", Font.PLAIN, 11));
        delBook.setFont(new Font("Times", Font.PLAIN, 11));
        topThree.setFont(new Font("Times", Font.PLAIN, 11));
        detail.setFont(new Font("Times", Font.PLAIN, 11));
        borrowedBook.setFont(new Font("Times", Font.PLAIN, 11));
        logout.setFont(new Font("Times", Font.PLAIN, 11));

        addMhs.setBounds(125, 80, 100, 100);
        addDos.setBounds(225, 80, 100, 100);
        addKtg.setBounds(125, 180, 100, 100);
        addBook.setBounds(225, 180, 100, 100);
        delBook.setBounds(125, 280, 100, 100);
        topThree.setBounds(225, 280, 100, 100);
        detail.setBounds(125, 380, 100, 100);
        borrowedBook.setBounds(225, 380, 100, 100);
        logout.setBounds(125, 480, 200, 50);

        add(welcome);
        add(addMhs);
        add(addDos);
        add(addKtg);
        add(addBook);
        add(delBook);
        add(topThree);
        add(detail);
        add(borrowedBook);
        add(logout);

        addMhs.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("tambahMhs");
            }
        });

        addDos.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("tambahDosen");
            }
        });

        addKtg.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("tambahKategori");
            }
        });

        addBook.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("tambahBuku");
            }
        });

        delBook.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("hapusBuku");
            }
        });

        topThree.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("peringkat");
            }
        });

        detail.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("detailAnggota");
            }
        });

        borrowedBook.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("daftarPeminjam");
            }
        });

        logout.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("welcome");
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
