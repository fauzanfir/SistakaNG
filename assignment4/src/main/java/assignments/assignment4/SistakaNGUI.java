package assignments.assignment4;

import assignments.assignment4.backend.SistakaNG;

import javax.swing.*;


public class SistakaNGUI {
    public static void main(String[] args) {
        // Membuat Frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SistakaNG");
        SistakaNG.registerStaf();

        // TODO: Tambahkan hal-hal lain yang diperlukan

        frame.setVisible(true);
    }

}
