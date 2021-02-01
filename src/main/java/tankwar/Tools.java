package tankwar;

import javax.swing.*;
import java.awt.*;

public class Tools {
    public static  String getFileName(String filename) {
        return "assets/images/" + filename + ".gif";
    }

    public static ImageIcon getMissilesImg(Direction direction) {
        switch (direction) {
            case UP: return new ImageIcon("assets/images/missileU.gif");
            case DOWN: return new ImageIcon("assets/images/missileD.gif");
            case RIGHT: return new ImageIcon("assets/images/missileR.gif");
            case LEFT: return new ImageIcon("assets/images/missileL.gif");
            case DOWN_LEFT: return new ImageIcon("assets/images/missileLD.gif");
            case DOWN_RIGHT: return new ImageIcon("assets/images/missileRD.gif");
            case UP_LEFT: return new ImageIcon("assets/images/missileLU.gif");
            case UP_RIGHT: return new ImageIcon("assets/images/missileRU.gif");
            default:
//                throw new ClassNotFoundException("direction type doesn't find");
                throw new IllegalStateException("Unexpected value: " + direction);
        }
    }
    public static boolean isCollisoin(int x, int y, Image aImage, Rectangle rect) {
        int img_width = aImage.getWidth(null); int img_height = aImage.getHeight(null);
        if(x < 0 || x > GameClient.getInstance().getWidth() - img_width ) {
            return true;
        }
        if(y < 0 || y > GameClient.getInstance().getHeight() - img_height ) {
            return true;
        }
        for(Wall wall: GameClient.getInstance().getWallList()) {
            if (rect.intersects(wall.getRectangule())) {
                return true;
            }
        }
        for(Tank enemyTank: GameClient.getInstance().getEnemyTanks()) {
            if(enemyTank.getRectangle().intersects(rect)) {
                return true;
            }
        }
        return true;
    }
}
