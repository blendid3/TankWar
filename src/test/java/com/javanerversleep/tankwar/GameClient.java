package com.javanerversleep.tankwar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameClient extends JComponent {
    private Tank playerTank;
    private List<Tank> enemyTanks = new ArrayList<>();
    private List<Wall> wallList = new ArrayList<>();
    public GameClient() {
        playerTank = new MyTank(400, 100, Direction.DOWN);
        this.setPreferredSize(new Dimension(800, 600));
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
                enemyTanks.add(new EnemyTank(300 + i * 100, 300 + j * 60, Direction.UP));
            }
        }
        wallList.add(new Wall(200, 150, 12,true));
        wallList.add(new Wall(200, 550, 12,true));
        wallList.add(new Wall(100, 100, 12,false));
        wallList.add(new Wall(600, 100, 12,false));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(new ImageIcon("assets/images/tankD.gif").getImage(), this.playerTank.getX(), this.playerTank.getY(), null);
        this.playerTank.draw(g);
        for(int i = 0; i < this.enemyTanks.size(); i++) {
            this.enemyTanks.get(i).draw(g);
        }

        for (int i = 0; i < wallList.size(); i++) {
            this.wallList.get(i).draw(g);
        }

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
