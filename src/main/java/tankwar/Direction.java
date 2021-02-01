package tankwar;

import javax.swing.*;
import java.awt.*;

public enum Direction {
    UP("U", 0, -1), DOWN("D", 0, 1), RIGHT("R", 1, 0),
    LEFT("L", -1, 0), UP_LEFT("LU", -1, -1),
    UP_RIGHT("RU", 1, -1), DOWN_LEFT("LD", -1, 1),
    DOWN_RIGHT("RD", 1, 1);
    private final String abbrev;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private final int x ;
    private final int y ;

    Direction(String abbrev, int x, int y) {
        this.abbrev = abbrev;
        this.x = x;
        this.y = y;
    }


    Image getImage() {
        return new ImageIcon("assets/images/tank" + abbrev + ".gif").getImage();

    }
    Image getEImage() {
        return new ImageIcon("assets/images/etank" + abbrev + ".gif").getImage();
    }
}
