package com.javanerversleep.tankwar;

import javax.swing.*;
import java.awt.*;

public class GameClient extends JComponent {
    public GameClient() {
        this.setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("assets/images/tankD.gif").getImage(), 400, 100, null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("The Tank Game");
        frame.add(new GameClient());
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
