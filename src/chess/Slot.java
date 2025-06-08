package chess;

import java.awt.*;

public class Slot {
    public int id;
    public int x;
    public int y;
    public int size;
    public Piece piece = null;
    public Rectangle collisionBox;
    public boolean whiteVision = false;
    public boolean blackVision = false;

    public Slot(int id, int x, int y, int size) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.size = size;
        collisionBox = new Rectangle(x, y, size, size);
    }
}
