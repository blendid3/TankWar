package tankwar;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class  Tank {
    private int x;



    private int y;
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

    ImageIcon getImage() {
        switch (direction) {
            case UP: return new ImageIcon("assets/images/tankU.gif");
            case DOWN: return new ImageIcon("assets/images/tankD.gif");
            case RIGHT: return new ImageIcon("assets/images/tankR.gif");
            case LEFT: return new ImageIcon("assets/images/tankL.gif");
            case DOWNLEFT: return new ImageIcon("assets/images/tankLD.gif");
            case DOWNRIGHT: return new ImageIcon("assets/images/tankRD.gif");
            case UPLEFT: return new ImageIcon("assets/images/tankLU.gif");
            case UPRIGHT: return new ImageIcon("assets/images/tankRU.gif");
            default:
//                throw new ClassNotFoundException("direction type doesn't find");
                throw new IllegalStateException("Unexpected value: " + this.direction);
        }

    }
    private boolean up, left, right, down = false;
    private boolean stopSign = true;
    public void keyPressed(KeyEvent e) { // run when press key in every thread;
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP: up = true; break;
            case KeyEvent.VK_DOWN: down = true; break;
            case KeyEvent.VK_LEFT: left = true; break;
            case KeyEvent.VK_RIGHT: right = true; break;
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
    public void draw(Graphics g)  { // run in every 50 mills
        move();

        for(Wall wall : GameClient.getInstance().getWallList()) {
//            this.getImage().getImage().
        }

        g.drawImage(this.getImage().getImage(), this.getX(), this.getY(), null);
    }

    private void move() {
        if(stopSign) return;
//        if(getX() <= 0 ||  )
        int oldX = getX(); int oldY = getY();
        switch (this.direction) {
            case DOWN: setY(getY() + 5); break;
            case UP: setY(getY() - 5); break;
            case LEFT: setX(getX() - 5); break;
            case RIGHT: setX(getX() + 5); break;
            case UPLEFT: setY(getY() - 5); setX(getX() - 5); break;
            case UPRIGHT: setY(getY() - 5); setX(getX() + 5);break;
            case DOWNLEFT: setY(getY() + 5); setX(getX() - 5); break;
            case DOWNRIGHT: setY(getY() + 5); setX(getX() + 5);  break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.direction);
        }
        int img_width = this.getImage().getImage().getWidth(null); int img_height = this.getImage().getImage().getHeight(null);
        if(getX() < 0 || getX() > GameClient.getInstance().getWidth() - img_width ) {
            setX(oldX);
        }
        if(getY() < 0 || getY() > GameClient.getInstance().getHeight() - img_height ) {
            setY(oldY);
        }
        for(Wall wall: GameClient.getInstance().getWallList()) {
           if (getRectangle().intersects(wall.getRectangule())) {
               setX(oldX); setY(oldY);
           }
        }
    }
    private Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), this.getImage().getImage().getWidth(null), this.getImage().getImage().getWidth(null));
    }

    private void determineDirection() {
        if(up && left && !down && !right) this.direction = Direction.UPLEFT;
        if(up && !left && !down && !right) this.direction = Direction.UP;
        if(!up && left && !down && !right) this.direction = Direction.LEFT;
        if(!up && !left && down && !right) this.direction = Direction.DOWN;
        if(!up && !left && !down && right) this.direction = Direction.RIGHT;
        if(up && !left && !down && right) this.direction = Direction.UPRIGHT;
        if(!up && left && down && !right) this.direction = Direction.DOWNLEFT;
        if(!up && !left && down && right) this.direction = Direction.DOWNRIGHT;

    }
}
