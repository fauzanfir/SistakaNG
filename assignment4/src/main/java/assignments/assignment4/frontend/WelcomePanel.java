package assignments.assignment4.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomePanel extends SistakaPanel {
    public WelcomePanel(HomeGUI homeGUI) {
        super(homeGUI);

        setLayout(null);
        JLabel welcome = new JLabel("Welcome");
        JLabel to = new JLabel("To");
        JLabel sng = new JLabel("SistakaNG");
        JButton login = new JButton("Login");
        JButton exit = new JButton("Exit");

        welcome.setFont(new Font("Times", Font.BOLD, 25));
        welcome.setBounds(30, 0, 200, 200);
        to.setFont(new Font("Times", Font.BOLD, 20));
        to.setBounds(55, 25, 200, 200);
        sng.setFont(new Font("Times", Font.BOLD, 25));
        sng.setBounds(80, 50, 200, 200);

        login.setBounds(125, 400, 300, 50);
        exit.setBounds(125, 460, 300, 50);

        add(welcome);
        add(to);
        add(sng);
        add(login);
        add(exit);
        
        login.addActionListener((ActionListener) new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //new LoginPanel(iniGui);
                //this.setPanel("login");
                homeGUI.setPanel("login");
            }
        });

        exit.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e){
                homeGUI.exit();
            }
        });

    }

    @Override
    public void refresh() {
        // ignored
    }
}
