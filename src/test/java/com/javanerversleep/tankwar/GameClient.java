package com.javanerversleep.tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameClient extends JComponent {
    private Tank playerTank;


    public GameClient() {
        playerTank = new Tank(400, 100, Direction.DOWN);
        this.setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(new ImageIcon("assets/images/tankD.gif").getImage(), this.playerTank.getX(), this.playerTank.getY(), null);
        this.playerTank.draw(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("The Tank Game");
        frame.setIconImage(new ImageIcon("assets/images/icon.png").getImage());
        GameClient client = new GameClient();
        frame.add(client);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                client.playerTank.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                client.playerTank.keyReleased(e);
            }
        });
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        while (true) {
            client.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }
        }
    }
}
