package chess;

public abstract class Piece {
    public boolean isWhite;
    public int id = -1;
    public boolean hasMoved = false;
    public Piece(boolean isWhite, int id) {
        this.isWhite = isWhite;
        this.id = id;
    }
}
