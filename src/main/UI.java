package main;

import chess.Piece;
import chess.Slot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
    Graphics2D g2;
    GamePanel gamePanel;
    public BufferedImage whitePawn, blackPawn;
    public BufferedImage whiteRook, blackRook;
    public BufferedImage whiteKnight, blackKnight;
    public BufferedImage whiteBishop, blackBishop;
    public BufferedImage whiteQueen, blackQueen;
    public BufferedImage whiteKing, blackKing;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        loadPieceImages();
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        drawSlots();
        drawPieces();
        drawSelected();
    }
    public void drawPieces() {
        BufferedImage pieceImage = null;
        for(Slot slot : gamePanel.slots) {
            if(slot.piece != null) {
                if(slot.piece.isWhite) {
                    switch (slot.piece.id) {
                        case 0: pieceImage = whiteRook; break;
                        case 1: pieceImage = whiteKnight; break;
                        case 2: pieceImage = whiteBishop; break;
                        case 3: pieceImage = whiteKing; break;
                        case 4: pieceImage = whiteQueen; break;
                        case 5: pieceImage = whitePawn; break;
                    }
                } else {
                    switch (slot.piece.id) {
                        case 0: pieceImage = blackRook; break;
                        case 1: pieceImage = blackKnight; break;
                        case 2: pieceImage = blackBishop; break;
                        case 3: pieceImage = blackKing; break;
                        case 4: pieceImage = blackQueen; break;
                        case 5: pieceImage = blackPawn; break;
                    }
                }
            }
            if(slot.piece != null) {
                g2.drawImage(pieceImage, slot.x, slot.y, null);
            }
        }
    }
    private void drawSlots() {
         drawRow(1);
         drawRow(2);
         drawRow(3);
         drawRow(4);
         drawRow(5);
         drawRow(6);
         drawRow(7);
         drawRow(8);
    }
    public void drawRow(int row) {
        if(row % 2 == 1) {
            g2.setColor(gamePanel.slotWhite);
            for(int i = (row-1)*8; i < (row-1)*8+8; i+=2) {
                Slot slot = gamePanel.slots.get(i);
                g2.fillRect(slot.x, slot.y, slot.size, slot.size);
            }
            g2.setColor(gamePanel.slotGreen);
            for(int i = (row-1)*8+1; i < (row-1)*8+8; i+=2) {
                Slot slot = gamePanel.slots.get(i);
                g2.fillRect(slot.x, slot.y, slot.size, slot.size);
            }
        } else {
            g2.setColor(gamePanel.slotGreen);
            for(int i = (row-1)*8; i < (row-1)*8+8; i+=2) {
                Slot slot = gamePanel.slots.get(i);
                g2.fillRect(slot.x, slot.y, slot.size, slot.size);
            }
            g2.setColor(gamePanel.slotWhite);
            for(int i = (row-1)*8+1; i < (row-1)*8+8; i+=2) {
                Slot slot = gamePanel.slots.get(i);
                g2.fillRect(slot.x, slot.y, slot.size, slot.size);
            }
        }
    }
    public void loadPieceImages() {
        try {
            whitePawn = ImageIO.read(getClass().getResourceAsStream("/pieces/wp.png"));
            whiteRook = ImageIO.read(getClass().getResourceAsStream("/pieces/wr.png"));
            whiteKnight = ImageIO.read(getClass().getResourceAsStream("/pieces/wn.png"));
            whiteBishop = ImageIO.read(getClass().getResourceAsStream("/pieces/wb.png"));
            whiteQueen = ImageIO.read(getClass().getResourceAsStream("/pieces/wq.png"));
            whiteKing = ImageIO.read(getClass().getResourceAsStream("/pieces/wk.png"));

            blackPawn = ImageIO.read(getClass().getResourceAsStream("/pieces/bp.png"));
            blackRook = ImageIO.read(getClass().getResourceAsStream("/pieces/br.png"));
            blackKnight = ImageIO.read(getClass().getResourceAsStream("/pieces/bn.png"));
            blackBishop = ImageIO.read(getClass().getResourceAsStream("/pieces/bb.png"));
            blackQueen = ImageIO.read(getClass().getResourceAsStream("/pieces/bq.png"));
            blackKing = ImageIO.read(getClass().getResourceAsStream("/pieces/bk.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
    public void drawSelected() {
        if(gamePanel.mouseHandler.isPieceSelected) {
            g2.setColor(new Color(0, 0, 0, 50));
            int x = gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected).x;
            int y = gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected).y;
            g2.fillRect(x, y, gamePanel.slotSize, gamePanel.slotSize);

            if(gamePanel.mouseHandler.pieceSelected.id == 0) {
                drawRookMoves();
            } else if(gamePanel.mouseHandler.pieceSelected.id == 1) {
                drawKnightMoves();
            } else if(gamePanel.mouseHandler.pieceSelected.id == 2) {
                drawDiagonals();
            }
            else if(gamePanel.mouseHandler.pieceSelected.id == 3) {
                drawKingMoves();
            }
            else if(gamePanel.mouseHandler.pieceSelected.id == 4) {
                drawDiagonals();
                drawRookMoves();
            }
            else {
                drawPawnMoves();
            }
        }
    }
    public void drawRookMoves() {
        int x = gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected).x;
        int y = gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected).y;
        for(int i = 1; i <= 7; i++) {
            if(gamePanel.mouseHandler.slotIdFromSelected-i>=0 &&
                    gamePanel.mv.legalMove(gamePanel.mouseHandler.slotIdFromSelected-i, gamePanel.mouseHandler.pieceSelected)) {
                g2.fillOval(x + 20 - i*gamePanel.slotSize, y + 20, 20, 20 );
            }

            if(gamePanel.mouseHandler.slotIdFromSelected+i<=63 &&
                    gamePanel.mv.legalMove(gamePanel.mouseHandler.slotIdFromSelected+i, gamePanel.mouseHandler.pieceSelected)) {
                g2.fillOval(x + 20 + i*gamePanel.slotSize, y + 20, 20, 20 );
            }
            if(gamePanel.mouseHandler.slotIdFromSelected-i*8>=0 &&
                    gamePanel.mv.legalMove(gamePanel.mouseHandler.slotIdFromSelected-i*8, gamePanel.mouseHandler.pieceSelected)) {
                g2.fillOval(x + 20, y + 20 - i*gamePanel.slotSize, 20, 20 );
            }
            if(gamePanel.mouseHandler.slotIdFromSelected+i*8<=63 &&
                    gamePanel.mv.legalMove(gamePanel.mouseHandler.slotIdFromSelected+i*8, gamePanel.mouseHandler.pieceSelected)) {
                g2.fillOval(x + 20, y + 20 + i*gamePanel.slotSize, 20, 20 );
            }
        }


    }
    public void drawHorizontal() {
        for(int x = 0; x < gamePanel.slotSize*8; x+=gamePanel.slotSize) {
            int y = gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected).y;
            g2.fillOval(x + 20, y + 20, 20, 20 );
        }
    }
    public void drawVertical() {
        for(int y = 0; y < gamePanel.slotSize*8; y+=gamePanel.slotSize) {
            int x = gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected).x;
            g2.fillOval(x + 20, y + 20, 20, 20 );
        }
    }
    public void drawDiagonals() {

    }
    public void drawPawnMoves() {
        int x = gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected).x;
        int y = gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected).y;
        if(gamePanel.mouseHandler.pieceSelected.isWhite) {
            if(gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected-8).piece == null) {
                g2.fillOval(x + 20, y + 20 - gamePanel.slotSize, 20, 20 );
                if(gamePanel.mouseHandler.slotIdFromSelected-16>=0 && gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected-16).piece == null && !gamePanel.mouseHandler.pieceSelected.hasMoved) {
                    g2.fillOval(x + 20, y + 20 - gamePanel.slotSize*2, 20, 20 );
                }
            }
            if(gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected-9).piece != null && !gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected-9).piece.isWhite) {
                g2.fillOval(x + 20 - gamePanel.slotSize, y + 20 - gamePanel.slotSize, 20, 20 );
            }
            if(gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected-7).piece != null && !gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected-7).piece.isWhite) {
                g2.fillOval(x + 20 + gamePanel.slotSize, y + 20 - gamePanel.slotSize, 20, 20 );
            }
        } else {
            if(gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected+8).piece == null) {
                g2.fillOval(x + 20, y + 20 + gamePanel.slotSize, 20, 20 );
                if(gamePanel.mouseHandler.slotIdFromSelected+16<=63 && gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected+16).piece == null && !gamePanel.mouseHandler.pieceSelected.hasMoved) {
                    g2.fillOval(x + 20, y + 20 + gamePanel.slotSize*2, 20, 20 );
                }
            }
            if(gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected+7).piece != null && gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected+7).piece.isWhite) {
                g2.fillOval(x + 20 - gamePanel.slotSize, y + 20 + gamePanel.slotSize, 20, 20 );
            }
            if(gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected+9).piece != null && gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected+9).piece.isWhite) {
                g2.fillOval(x + 20 + gamePanel.slotSize, y + 20 + gamePanel.slotSize, 20, 20 );
            }
        }
    }
    public void drawKnightMoves() {
        int x = gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected).x;
        int y = gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected).y;

        if(x - gamePanel.slotSize >= 0 && y - gamePanel.slotSize*2 >= 0 && checkKnightSlot(gamePanel.mouseHandler.slotIdFromSelected-17)) {
            g2.fillOval(x + 20 - gamePanel.slotSize, y + 20 - gamePanel.slotSize*2, 20, 20 );
        }
        if(x + gamePanel.slotSize <= gamePanel.slotSize*8 && y - gamePanel.slotSize*2 >= 0 && checkKnightSlot(gamePanel.mouseHandler.slotIdFromSelected-15)) {
            g2.fillOval(x + 20 + gamePanel.slotSize, y + 20 - gamePanel.slotSize*2, 20, 20 );
        }
        if(x - gamePanel.slotSize*2 >= 0 && y - gamePanel.slotSize >= 0 && checkKnightSlot(gamePanel.mouseHandler.slotIdFromSelected-10)) {
            g2.fillOval(x + 20 - gamePanel.slotSize*2, y + 20 - gamePanel.slotSize, 20, 20 );
        }
        if(x - gamePanel.slotSize*2 >= 0 && y + gamePanel.slotSize <= gamePanel.slotSize*8 && checkKnightSlot(gamePanel.mouseHandler.slotIdFromSelected+6)) {
            g2.fillOval(x + 20 - gamePanel.slotSize*2, y + 20 + gamePanel.slotSize, 20, 20 );
        }
        if(x + gamePanel.slotSize*2 <= gamePanel.slotSize*8 && y - gamePanel.slotSize >= 0 && checkKnightSlot(gamePanel.mouseHandler.slotIdFromSelected-6)) {
            g2.fillOval(x + 20 + gamePanel.slotSize*2, y + 20 - gamePanel.slotSize, 20, 20 );
        }
        if(x + gamePanel.slotSize*2 <= gamePanel.slotSize*8 && y + gamePanel.slotSize <= gamePanel.slotSize*8 && checkKnightSlot(gamePanel.mouseHandler.slotIdFromSelected+10)) {
            g2.fillOval(x + 20 + gamePanel.slotSize*2, y + 20 + gamePanel.slotSize, 20, 20 );
        }
        if(x - gamePanel.slotSize >= 0 && y + gamePanel.slotSize*2 <= gamePanel.slotSize*8 && checkKnightSlot(gamePanel.mouseHandler.slotIdFromSelected+15)) {
            g2.fillOval(x + 20 - gamePanel.slotSize, y + 20 + gamePanel.slotSize*2, 20, 20 );
        }
        if(x + gamePanel.slotSize <= gamePanel.slotSize*8 && y + gamePanel.slotSize*2 <= gamePanel.slotSize*8&& checkKnightSlot(gamePanel.mouseHandler.slotIdFromSelected+17)) {
            g2.fillOval(x + 20 + gamePanel.slotSize, y + 20 + gamePanel.slotSize*2, 20, 20 );
        }
    }
    public void drawKingMoves() {
        int x = gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected).x;
        int y = gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected).y;

        if(x - gamePanel.slotSize >= 0 && gamePanel.mv.legalMove(gamePanel.mouseHandler.slotIdFromSelected-1, gamePanel.mouseHandler.pieceSelected)) {
            g2.fillOval(x + 20 - gamePanel.slotSize, y + 20, 20, 20 );
        }
        if(x + gamePanel.slotSize < gamePanel.slotSize*8 && gamePanel.mv.legalMove(gamePanel.mouseHandler.slotIdFromSelected+1, gamePanel.mouseHandler.pieceSelected)) {
            g2.fillOval(x + 20 + gamePanel.slotSize, y + 20, 20, 20 );
        }
        if(y - gamePanel.slotSize >= 0 && gamePanel.mv.legalMove(gamePanel.mouseHandler.slotIdFromSelected-8, gamePanel.mouseHandler.pieceSelected)) {
            g2.fillOval(x + 20, y + 20 - gamePanel.slotSize, 20, 20 );
        }
        if(y + gamePanel.slotSize < gamePanel.slotSize*8 && gamePanel.mv.legalMove(gamePanel.mouseHandler.slotIdFromSelected+8, gamePanel.mouseHandler.pieceSelected)) {
            g2.fillOval(x + 20, y + 20 + gamePanel.slotSize, 20, 20 );
        }

        if(x - gamePanel.slotSize >= 0 && y - gamePanel.slotSize >= 0 && gamePanel.mv.legalMove(gamePanel.mouseHandler.slotIdFromSelected-9, gamePanel.mouseHandler.pieceSelected)) {
            g2.fillOval(x + 20 - gamePanel.slotSize, y + 20 - gamePanel.slotSize, 20, 20 );
        }
        if(x - gamePanel.slotSize >= 0 && y + gamePanel.slotSize < gamePanel.slotSize*8 && gamePanel.mv.legalMove(gamePanel.mouseHandler.slotIdFromSelected+7, gamePanel.mouseHandler.pieceSelected)) {
            g2.fillOval(x + 20 - gamePanel.slotSize, y + 20 + gamePanel.slotSize, 20, 20 );
        }
        if(x + gamePanel.slotSize < gamePanel.slotSize*8 && y - gamePanel.slotSize >= 0 && gamePanel.mv.legalMove(gamePanel.mouseHandler.slotIdFromSelected-7, gamePanel.mouseHandler.pieceSelected)) {
            g2.fillOval(x + 20 + gamePanel.slotSize, y + 20 - gamePanel.slotSize, 20, 20 );
        }
        if(x + gamePanel.slotSize < gamePanel.slotSize*8 && y + gamePanel.slotSize >= 0 && gamePanel.mv.legalMove(gamePanel.mouseHandler.slotIdFromSelected+9, gamePanel.mouseHandler.pieceSelected)) {
            g2.fillOval(x + 20 + gamePanel.slotSize, y + 20 + gamePanel.slotSize, 20, 20 );
        }
    }
    public boolean checkKnightSlot(int slot) {
       if(slot < 0 || slot > 63) return false;
       return gamePanel.slots.get(slot).piece == null ||
                (gamePanel.mouseHandler.pieceSelected.isWhite && !gamePanel.slots.get(slot).piece.isWhite) ||
                (!gamePanel.mouseHandler.pieceSelected.isWhite && gamePanel.slots.get(slot).piece.isWhite);
    }
}
