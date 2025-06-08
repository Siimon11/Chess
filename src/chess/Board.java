package chess;

import main.GamePanel;

public class Board {
    GamePanel gamePanel;
    public Board(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setSlots();
        setPieces();
    }
    private void setSlots() {
        int x = 0;
        int y = 0;
        for(int i = 0; i < 64; i++) {
            gamePanel.slots.add(new Slot(i, x, y, gamePanel.slotSize));
            x += gamePanel.slotSize;
            if(x > gamePanel.slotSize * 7) {
                x = 0;
                y += gamePanel.slotSize;
            }
        }
    }
    private void setPieces() {
        gamePanel.slots.get(0).piece = new Rook(false, 0);
        gamePanel.slots.get(1).piece = new Knight(false, 1);
        gamePanel.slots.get(2).piece = new Bishop(false, 2);
        gamePanel.slots.get(3).piece = new King(false, 3);
        gamePanel.slots.get(4).piece = new Queen(false, 4);
        gamePanel.slots.get(5).piece = new Bishop(false, 2);
        gamePanel.slots.get(6).piece = new Knight(false, 1);
        gamePanel.slots.get(7).piece = new Rook(false, 0);

        gamePanel.slots.get(8).piece = new Pawn(false, 5);
        gamePanel.slots.get(9).piece = new Pawn(false, 5);
        gamePanel.slots.get(10).piece = new Pawn(false, 5);
        gamePanel.slots.get(11).piece = new Pawn(false, 5);
        gamePanel.slots.get(12).piece = new Pawn(false, 5);
        gamePanel.slots.get(13).piece = new Pawn(false, 5);
        gamePanel.slots.get(14).piece = new Pawn(false, 5);
        gamePanel.slots.get(15).piece = new Pawn(false, 5);

        gamePanel.slots.get(48).piece = new Pawn(true, 5);
        gamePanel.slots.get(49).piece = new Pawn(true, 5);
        gamePanel.slots.get(50).piece = new Pawn(true, 5);
        gamePanel.slots.get(51).piece = new Pawn(true, 5);
        gamePanel.slots.get(52).piece = new Pawn(true, 5);
        gamePanel.slots.get(53).piece = new Pawn(true, 5);
        gamePanel.slots.get(54).piece = new Pawn(true, 5);
        gamePanel.slots.get(55).piece = new Pawn(true, 5);

        gamePanel.slots.get(56).piece = new Rook(true, 0);
        gamePanel.slots.get(57).piece = new Knight(true, 1);
        gamePanel.slots.get(58).piece = new Bishop(true, 2);
        gamePanel.slots.get(59).piece = new King(true, 3);
        gamePanel.slots.get(60).piece = new Queen(true, 4);
        gamePanel.slots.get(61).piece = new Bishop(true, 2);
        gamePanel.slots.get(62).piece = new Knight(true, 1);
        gamePanel.slots.get(63).piece = new Rook(true, 0);
    }
    public void calculateVisions() {

    }
}
