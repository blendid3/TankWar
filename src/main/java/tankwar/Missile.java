package tankwar;

import java.awt.*;

class Missile {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
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

    private int x;
    private int y;
    private Direction direction;

    public void setIsalive(boolean isalive) {
        this.isalive = isalive;
    }

    private boolean isalive = true;

    public boolean isIsalive() {
        return isalive;
    }

    public Image getImage() {
        return img_missile;
    }

    private  Image img_missile;
    public Missile(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        img_missile = Tools.getMissilesImg(direction).getImage();
    }

    void move() {

        int oldX = getX(); int oldY = getY();

        setY(getY() + Tank.SPEED * direction.getY()); setX(getX() + Tank.SPEED * direction.getX());

        int img_width = this.getImage().getWidth(null); int img_height = this.getImage().getHeight(null);
        if(getX() < 0 || getX() > GameClient.getInstance().getWidth() - img_width ) {
            setIsalive(false);
        }
        if(getY() < 0 || getY() > GameClient.getInstance().getHeight() - img_height ) {
            setIsalive(false);
        }

        if(isCollision()) {
            setIsalive(false);
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
        return new Rectangle(getX(), getY(), img_missile.getWidth(null), img_missile.getHeight(null));
    }
    public void draw(Graphics g) {
        move();
        g.drawImage(img_missile, x, y,null);
    }
}
