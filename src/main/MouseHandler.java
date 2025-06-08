package main;

import chess.Piece;
import chess.Slot;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    GamePanel gamePanel;
    public boolean isPieceSelected = false;
    public Piece pieceSelected;
    public int slotIdFromSelected;

    public MouseHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point mouseClickedPosition = e.getPoint();
        if (!isPieceSelected) {
            for(Slot slot : gamePanel.slots) {
                if(slot.collisionBox.intersects(mouseClickedPosition.x, mouseClickedPosition.y, 1, 1)
                        && slot.piece != null) {
                    isPieceSelected = true;
                    pieceSelected = slot.piece;
                    slotIdFromSelected = slot.id;
                }
            }
        } else {
            for(Slot slot : gamePanel.slots) {
                if(slot.collisionBox.intersects(mouseClickedPosition.x, mouseClickedPosition.y, 1, 1)) {
                    if(slot.piece != null) {
                        if(slot.piece.isWhite == pieceSelected.isWhite) {
                            pieceSelected = slot.piece;
                            slotIdFromSelected = slot.id;
                        } else if(gamePanel.mv.legalMove(slot.id, pieceSelected)){
                            isPieceSelected = false;
                            slot.piece = pieceSelected;
                            slot.piece.hasMoved = true;
                            gamePanel.slots.get(slotIdFromSelected).piece = null;
                        } else {
                            isPieceSelected = false;
                            pieceSelected = null;
                        }
                    } else if(gamePanel.mv.legalMove(slot.id, pieceSelected)){
                        isPieceSelected = false;
                        slot.piece = pieceSelected;
                        slot.piece.hasMoved = true;
                        gamePanel.slots.get(slotIdFromSelected).piece = null;
                    } else {
                        isPieceSelected = false;
                        pieceSelected = null;
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
