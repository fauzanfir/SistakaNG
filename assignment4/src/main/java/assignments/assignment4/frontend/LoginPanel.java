package assignments.assignment4.frontend;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Pengguna;
import assignments.assignment4.backend.pengguna.Staf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class LoginPanel extends SistakaPanel {
    JTextField insertId;
    public LoginPanel(HomeGUI main){
        super(main);

        // Meng-set layout, menambahkan, mengatur posisi, dan membuat komponen
        setLayout(null);
        setSize(450, 600);

        JLabel welcome = new JLabel("Welcome");
        JLabel to = new JLabel("To");
        JLabel sng = new JLabel("SistakaNG");
        JLabel label1 = new JLabel("Masukkan ID Anda untuk mengakses:");
        insertId = new JTextField();
        JButton login = new JButton("Login");

        welcome.setFont(new Font("Times", Font.BOLD, 25));
        welcome.setBounds(30, 0, 200, 200);
        to.setFont(new Font("Times", Font.BOLD, 20));
        to.setBounds(55, 25, 200, 200);
        sng.setFont(new Font("Times", Font.BOLD, 25));
        sng.setBounds(80, 50, 200, 200);

        label1.setFont(new Font("Times", Font.PLAIN, 11));
        label1.setBounds(125,150,200,200);

        insertId.setBounds(125, 270, 200, 20);
        login.setBounds(200, 300, 50, 40);

        add(welcome);
        add(to);
        add(sng);
        add(label1);
        add(insertId);
        add(login);

        // Membuat aksi yang tepat setiap button di klik
        login.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String inputUser = insertId.getText();
                if(inputUser.equals("")){           // Jika user tidak menginput apa-apa
                    showWarning("Harap masukkan ID Anda pada kotak di atas!");
                }
                else if(SistakaNG.handleLogin(inputUser) == null){          // Jika user tidak ditemukan
                    showWarning(String.format("Pengguna dengnan ID %s tidak ditemukan", inputUser));
                }
                else{
                    Pengguna user = SistakaNG.handleLogin(inputUser);
                    main.setUser(user);
                    if(user instanceof Staf){           // Memisahkan untuk menu staf dan anggota
                        main.setPanel("staf");
                    }
                    else{
                        main.setPanel("anggota");
                    }
                }
            }
        });
    
    }

    @Override
    public void refresh() {
        insertId.setText("");   // Mengosongkan TextField
    }

}
