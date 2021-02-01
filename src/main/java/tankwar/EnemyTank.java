package tankwar;

import javax.swing.*;
import java.awt.*;

public class EnemyTank extends Tank {
    public static final int move_speed = 5;
    Direction direction;
    public EnemyTank(int x, int y, Direction direction) {
        
        super(x, y, direction);
        this.direction = direction;
    }
    @Override
    Image getImage() {
        return direction.getEImage();
    }


}
