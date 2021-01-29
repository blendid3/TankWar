package tankwar;

import javax.swing.*;

public class EnemyTank extends Tank {
    Direction direction;
    public EnemyTank(int x, int y, Direction direction) {
        
        super(x, y, direction);
        this.direction = direction;
    }
    @Override
    ImageIcon getImage() {
        switch (direction) {
            case UP: return new ImageIcon("assets/images/etankU.gif");
            case DOWN: return new ImageIcon("assets/images/etankD.gif");
            case RIGHT: return new ImageIcon("assets/images/etankR.gif");
            case LEFT: return new ImageIcon("assets/images/etankL.gif");
            case DOWNLEFT: return new ImageIcon("assets/images/etankLD.gif");
            case DOWNRIGHT: return new ImageIcon("assets/images/etankRD.gif");
            case UPLEFT: return new ImageIcon("assets/images/etankLU.gif");
            case UPRIGHT: return new ImageIcon("assets/images/etankRU.gif");
            default:
                throw new IllegalStateException("Unexpected value: " + this.direction);
        }
    }
//    @Override private ImageIcon get
}
