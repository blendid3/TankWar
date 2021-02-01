package tankwar;







import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public abstract class  Tank {

    public static final int SPEED = 5;
    private int x;

    private int y;

    public Direction getDirection() {
        return direction;
    }

    public boolean isIsalive() {
        return isalive;
    }

    boolean isalive = true;

    public void setIsalive(boolean isalive) {
        this.isalive = isalive;
    }

    private Direction direction = Direction.DOWN;

    public Tank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    Image getImage() {
        return direction.getImage();

    }

    private boolean up, left, right, down = false;

    public boolean isStopSign() {
        return stopSign;
    }

    private boolean stopSign = true;
    public void keyPressed(KeyEvent e) { // run when press key in every thread;
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP: up = true; break;
            case KeyEvent.VK_DOWN: down = true; break;
            case KeyEvent.VK_LEFT: left = true; break;
            case KeyEvent.VK_RIGHT: right = true; break;
            case KeyEvent.VK_SPACE: fire(); return;
        }
        stopSign = false;
        determineDirection();
    }


    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: up = false; break;
            case KeyEvent.VK_DOWN: down = false; break;
            case KeyEvent.VK_LEFT: left = false; break;
            case KeyEvent.VK_RIGHT: right = false; break;
        }
        this.stopSign = true;
    }
    private void fire() {
        Missile aMissile = new Missile(getX() + getImage().getWidth(null) / 2 - 6, getY() + getImage().getHeight(null) / 2 - 6, getDirection());
        GameClient.getInstance().addMissiles(aMissile);
        Media sound = new Media(new File("assets/audios/shoot.wav").toURI().toString());
        MediaPlayer player = new MediaPlayer(sound);
        player.play();
    }

    public void draw(Graphics g)  { // run in every 50 mills
        move();
        g.drawImage(this.getImage(), this.getX(), this.getY(), null);
    }

    void move() {
        if(isStopSign()) return;

        int oldX = getX(); int oldY = getY();

        setY(getY() + Tank.SPEED * direction.getY()); setX(getX() + Tank.SPEED * direction.getX());

        int img_width = this.getImage().getWidth(null); int img_height = this.getImage().getHeight(null);
        if(getX() < 0 || getX() > GameClient.getInstance().getWidth() - img_width ) {
            setX(oldX);
        }
        if(getY() < 0 || getY() > GameClient.getInstance().getHeight() - img_height ) {
            setY(oldY);
        }

        if(isCollision()) {
            setX(oldX); setY(oldY);
        }
    }

    private boolean isCollision() {
        for(Wall wall: GameClient.getInstance().getWallList()) {
           if (getRectangle().intersects(wall.getRectangule())) {
               return true;
           }
        }
        for(Tank enemyTank: GameClient.getInstance().getEnemyTanks()) {
            if(enemyTank.getRectangle().intersects(this.getRectangle())) {
                return true;
            }
        }
        return false;
    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), (int)(this.getImage().getWidth(null) * 0.9), (int)(this.getImage().getHeight(null) * 0.9));
    }

    private void determineDirection() {
        if(up && left && !down && !right) this.direction = Direction.UP_LEFT;
        if(up && !left && !down && !right) this.direction = Direction.UP;
        if(!up && left && !down && !right) this.direction = Direction.LEFT;
        if(!up && !left && down && !right) this.direction = Direction.DOWN;
        if(!up && !left && !down && right) this.direction = Direction.RIGHT;
        if(up && !left && !down && right) this.direction = Direction.UP_RIGHT;
        if(!up && left && down && !right) this.direction = Direction.DOWN_LEFT;
        if(!up && !left && down && right) this.direction = Direction.DOWN_RIGHT;

    }
}
