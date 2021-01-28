package com.javanerversleep.tankwar;

import javax.swing.*;
import java.awt.*;

public class Wall {
    private int x;
    private int y ;
    private int brick_num = 7;
    private boolean horizontal = false ;

    public Wall(int x, int y, boolean horizontal) {
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setBrick_num(int brick_num) {
        this.brick_num = brick_num;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBrick_num() {
        return brick_num;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public Wall(int x, int y, int brick_num, boolean horizontal) {
        this.x = x;
        this.y = y;
        this.brick_num = brick_num;
        this.horizontal = horizontal;
    }


    public void draw(Graphics g) {
        Image brick = new ImageIcon("assets/images/brick.png").getImage();
        for(int i = 0; i < brick_num; i++) {
            if(horizontal){
                g.drawImage(brick, this.getX() + i * brick.getWidth(null), this.getY(), null);
            } else {
                g.drawImage(brick, this.getX() , this.getY() + i * brick.getHeight(null), null);
            }

        }

    }
}
