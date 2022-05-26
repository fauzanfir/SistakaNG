package assignments.assignment4;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;

import javax.swing.*;


public class SistakaNGUI {
    public static void main(String[] args) {
        // Membuat Frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SistakaNG");
        SistakaNG.registerStaf();

        // Membuat ukuran dari frame dan mengunci ukuran
        frame.setSize(450, 600);

        HomeGUI iniGui = new HomeGUI(frame);
        iniGui.setPanel("welcome");

        frame.setResizable(false);
        frame.setVisible(true);
    }

}
