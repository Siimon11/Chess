package main;

import chess.Piece;

public class MoveValidator {
    GamePanel gamePanel;
    public String direction = "";
    public MoveValidator(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public boolean legalMove(int slot, Piece piece) {
        if(slot < 0 || slot > 63) return false;
        if(gamePanel.mouseHandler.pieceSelected == null) return false;
        int pieceSlot = gamePanel.mouseHandler.slotIdFromSelected;
        if(piece.id == 0) {
            //rook
            int maxLeft = pieceSlot;
            int maxRight = pieceSlot;
            int maxUp = pieceSlot;
            int maxDown = pieceSlot;
            int distance = 0;
            direction = "";
            while(maxLeft > 7) {
                maxLeft -= 8;
            }
            while(maxRight > 7) {
                maxRight -= 8;
            }
            maxRight = 7 - maxRight;
            if(maxUp < 8) {
                maxUp = 0;
            } else if (maxUp < 16) {
                maxUp = 1;
            } else if (maxUp < 24) {
                maxUp = 2;
            } else if (maxUp < 32) {
                maxUp = 3;
            } else if (maxUp < 40) {
                maxUp = 4;
            } else if (maxUp < 48) {
                maxUp = 5;
            } else if (maxUp < 56) {
                maxUp = 6;
            } else {
                maxUp = 7;
            }
            if(maxDown > 55) {
                maxDown = 0;
            } else if (maxDown > 47) {
                maxDown = 1;
            } else if (maxDown > 39) {
                maxDown = 2;
            } else if (maxDown > 31) {
                maxDown = 3;
            } else if (maxDown > 23) {
                maxDown = 4;
            } else if (maxDown > 15) {
                maxDown = 5;
            } else if (maxDown > 7) {
                maxDown = 6;
            } else {
                maxDown = 7;
            }

            for(int i = 1; i <= maxLeft; i++) {
                if(slot +i == pieceSlot) {
                    distance = i;
                    direction = "left";
                }
            }
            for(int i = 1; i <= maxRight; i++) {
                if(slot -i == pieceSlot) {
                    distance = i;
                    direction = "right";
                }
            }
            for(int i = 1; i <= maxUp; i++) {
                if(slot+i*8 == pieceSlot) {
                    distance = i;
                    direction = "up";
                }
            }
            for(int i = 1; i <= maxDown; i++) {
                if(slot-i*8 == pieceSlot) {
                    distance = i;
                    direction = "down";
                }
            }
            if(direction.isEmpty()) {
                return false;
            }
            if(direction.equals("left")) {
                int i = 1;
                while(i <= distance) {
                    if(i == distance) {
                        if(gamePanel.slots.get(pieceSlot-i).piece == null ||
                                (gamePanel.slots.get(pieceSlot).piece.isWhite && !gamePanel.slots.get(pieceSlot-i).piece.isWhite) ||
                                (!gamePanel.slots.get(pieceSlot).piece.isWhite && gamePanel.slots.get(pieceSlot-i).piece.isWhite)) {
                            return true;
                        }
                    }
                    if(gamePanel.slots.get(pieceSlot-i).piece != null) {
                        return false;
                    }
                    i++;
                }
            } else if (direction.equals("right")) {
                int i = 1;
                while(i <= distance) {
                    if(i == distance) {
                        if(gamePanel.slots.get(pieceSlot+i).piece == null ||
                                (gamePanel.slots.get(pieceSlot).piece.isWhite && !gamePanel.slots.get(pieceSlot+i).piece.isWhite) ||
                                (!gamePanel.slots.get(pieceSlot).piece.isWhite && gamePanel.slots.get(pieceSlot+i).piece.isWhite)) {
                            return true;
                        }
                    }
                    if(gamePanel.slots.get(pieceSlot+i).piece != null) {
                        return false;
                    }
                    i++;
                }
                return true;
            } else if (direction.equals("up")) {
                int i = 1;
                while(i <= distance) {
                    if(i == distance) {
                        if(gamePanel.slots.get(pieceSlot-i*8).piece == null ||
                                (gamePanel.slots.get(pieceSlot).piece.isWhite && !gamePanel.slots.get(pieceSlot-i*8).piece.isWhite) ||
                                (!gamePanel.slots.get(pieceSlot).piece.isWhite && gamePanel.slots.get(pieceSlot-i*8).piece.isWhite)) {
                            return true;
                        }
                    }
                    if(gamePanel.slots.get(pieceSlot-i*8).piece != null) {
                        return false;
                    }
                    i++;
                }
            } else { // down
                int i = 1;
                while(i <= distance) {
                    if(i == distance) {
                        if(gamePanel.slots.get(pieceSlot+i*8).piece == null ||
                                (gamePanel.slots.get(pieceSlot).piece.isWhite && !gamePanel.slots.get(pieceSlot+i*8).piece.isWhite) ||
                                (!gamePanel.slots.get(pieceSlot).piece.isWhite && gamePanel.slots.get(pieceSlot+i*8).piece.isWhite)) {
                            return true;
                        }
                    }
                    if(gamePanel.slots.get(pieceSlot+i*8).piece != null) {
                        return false;
                    }
                    i++;
                }
            }
        }
        else if(piece.id == 1) {
            //knight
            if(gamePanel.mouseHandler.pieceSelected.isWhite) {
                if(slot+17 == pieceSlot || slot+15 == pieceSlot ||
                        slot+10 == pieceSlot || slot+6 == pieceSlot ||
                        slot-17 == pieceSlot || slot-15 == pieceSlot ||
                        slot-10 == pieceSlot || slot-6 == pieceSlot) {
                    if(gamePanel.slots.get(slot).piece == null) {
                        return true;
                    } else {
                        if(!gamePanel.slots.get(slot).piece.isWhite) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } else {
                if(slot+17 == pieceSlot || slot+15 == pieceSlot ||
                        slot+10 == pieceSlot || slot+6 == pieceSlot ||
                        slot-17 == pieceSlot || slot-15 == pieceSlot ||
                        slot-10 == pieceSlot || slot-6 == pieceSlot) {
                    if(gamePanel.slots.get(slot).piece == null) {
                        return true;
                    } else {
                        if(gamePanel.slots.get(slot).piece.isWhite) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
        }
        else if(piece.id == 2) {
            //bishop
            int maxLeft = pieceSlot;
            int maxRight = pieceSlot;
            int maxUp = pieceSlot;
            int maxDown = pieceSlot;
            while(maxLeft > 7) {
                maxLeft -= 8;
            }
            while(maxRight > 7) {
                maxRight -= 8;
            }
            maxRight = 7 - maxRight;
            if(maxUp < 8) {
                maxUp = 0;
            } else if (maxUp < 16) {
                maxUp = 1;
            } else if (maxUp < 24) {
                maxUp = 2;
            } else if (maxUp < 32) {
                maxUp = 3;
            } else if (maxUp < 40) {
                maxUp = 4;
            } else if (maxUp < 48) {
                maxUp = 5;
            } else if (maxUp < 56) {
                maxUp = 6;
            } else {
                maxUp = 7;
            }
            if(maxDown > 55) {
                maxDown = 0;
            } else if (maxDown > 47) {
                maxDown = 1;
            } else if (maxDown > 39) {
                maxDown = 2;
            } else if (maxDown > 31) {
                maxDown = 3;
            } else if (maxDown > 23) {
                maxDown = 4;
            } else if (maxDown > 15) {
                maxDown = 5;
            } else if (maxDown > 7) {
                maxDown = 6;
            } else {
                maxDown = 7;
            }

            int maxTopLeft = Math.min(maxLeft, maxUp);
            int maxTopRight = Math.min(maxRight, maxUp);
            int maxBottomLeft = Math.min(maxLeft, maxDown);
            int maxBottomRight = Math.min(maxRight, maxDown);
            int distance = 0;
            direction = "";

            for(int i = 1; i <= maxTopLeft; i++) {
                if(slot +i*9 == pieceSlot) {
                    distance = i;
                    direction = "topLeft";
                }
            }
            for(int i = 1; i <= maxTopRight; i++) {
                if(slot +i*7 == pieceSlot) {
                    distance = i;
                    direction = "topRight";
                }
            }
            for(int i = 1; i <= maxBottomLeft; i++) {
                if(slot -i*7 == pieceSlot) {
                    distance = i;
                    direction = "bottomLeft";
                }
            }
            for(int i = 1; i <= maxBottomRight; i++) {
                if(slot -i*9 == pieceSlot) {
                    distance = i;
                    direction = "bottomRight";
                }
            }
            if(direction.isEmpty()) {
                return false;
            }
            else if(direction.equals("topLeft")) {
                int i = 1;
                while(i <= distance) {
                    if(i == distance) {
                        if(gamePanel.slots.get(pieceSlot-i*9).piece == null ||
                                (gamePanel.slots.get(pieceSlot).piece.isWhite && !gamePanel.slots.get(pieceSlot-i*9).piece.isWhite) ||
                                (!gamePanel.slots.get(pieceSlot).piece.isWhite && gamePanel.slots.get(pieceSlot-i*9).piece.isWhite)) {
                            return true;
                        }
                    }
                    if(gamePanel.slots.get(pieceSlot-i*9).piece != null) return false;
                    i++;
                }
            } else if(direction.equals("topRight")) {
                int i = 1;
                while(i <= distance) {
                    if(i == distance) {
                        if(gamePanel.slots.get(pieceSlot-i*7).piece == null ||
                                (gamePanel.slots.get(pieceSlot).piece.isWhite && !gamePanel.slots.get(pieceSlot-i*7).piece.isWhite) ||
                                (!gamePanel.slots.get(pieceSlot).piece.isWhite && gamePanel.slots.get(pieceSlot-i*7).piece.isWhite)) {
                            return true;
                        }
                    }
                    if(gamePanel.slots.get(pieceSlot-i*7).piece != null) return false;

                    i++;
                }
            } else if(direction.equals("bottomLeft")) {
                int i = 1;
                while(i <= distance) {
                    if(i == distance) {
                        if(gamePanel.slots.get(pieceSlot+i*7).piece == null ||
                                (gamePanel.slots.get(pieceSlot).piece.isWhite && !gamePanel.slots.get(pieceSlot+i*7).piece.isWhite) ||
                                (!gamePanel.slots.get(pieceSlot).piece.isWhite && gamePanel.slots.get(pieceSlot+i*7).piece.isWhite)) {
                            return true;
                        }
                    }
                    if(gamePanel.slots.get(pieceSlot+i*7).piece != null) return false;

                    i++;
                }
            } else { // bottom right
                int i = 1;
                while(i <= distance) {
                    if(i == distance) {
                        if(gamePanel.slots.get(pieceSlot+i*9).piece == null ||
                                (gamePanel.slots.get(pieceSlot).piece.isWhite && !gamePanel.slots.get(pieceSlot+i*9).piece.isWhite) ||
                                (!gamePanel.slots.get(pieceSlot).piece.isWhite && gamePanel.slots.get(pieceSlot+i*9).piece.isWhite)) {
                            return true;
                        }
                    }
                    if(gamePanel.slots.get(pieceSlot+i*9).piece != null) return false;
                    i++;
                }
            }
        }
        else if(piece.id == 3) {
            // king
            if((gamePanel.mouseHandler.pieceSelected.isWhite && gamePanel.slots.get(slot).blackVision) || (!gamePanel.mouseHandler.pieceSelected.isWhite && gamePanel.slots.get(slot).blackVision)) {
                return false;
            }
            else if((slot+9 == pieceSlot) || (slot+8 == pieceSlot) ||
                    (slot+7 == pieceSlot) || (slot+1 == pieceSlot) ||
                    (slot-1 == pieceSlot) || (slot-7 == pieceSlot) ||
                    (slot-8 == pieceSlot) || (slot-9 == pieceSlot)) {

            } else if(slot+2 == pieceSlot || slot-2 == pieceSlot){
                if(slot+2 == pieceSlot && !gamePanel.mouseHandler.pieceSelected.hasMoved &&
                        gamePanel.slots.get(slot-1).piece != null &&
                        !gamePanel.slots.get(slot-1).piece.hasMoved &&
                        gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected-1).piece == null &&
                        gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected-2).piece == null) {
                    gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected-2).piece = gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected).piece;
                    gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected-1).piece = gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected-3).piece;
                    gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected-3).piece = null;
                    return true;
                } else if (slot-2 == pieceSlot && !gamePanel.mouseHandler.pieceSelected.hasMoved &&
                        gamePanel.slots.get(slot+2).piece != null &&
                        !gamePanel.slots.get(slot+2).piece.hasMoved &&
                        gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected+1).piece == null &&
                        gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected+2).piece == null &&
                        gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected+3).piece == null) {
                    gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected+2).piece = gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected).piece;
                    gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected+1).piece = gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected+4).piece;
                    gamePanel.slots.get(gamePanel.mouseHandler.slotIdFromSelected+4).piece = null;
                    return true;
                } else {
                    return false;
                }
            }
            else {
                return false;
            }
            if(gamePanel.slots.get(slot).piece == null && safeSquare()) return true;

            return (piece.isWhite && !gamePanel.slots.get(slot).piece.isWhite) || (!piece.isWhite && gamePanel.slots.get(slot).piece.isWhite);
        }
        else if(piece.id == 4) {
            //queen
            int maxLeft = pieceSlot;
            int maxRight = pieceSlot;
            int maxUp = pieceSlot;
            int maxDown = pieceSlot;
            int distance = 0;
            direction = "";
            while(maxLeft > 7) {
                maxLeft -= 8;
            }
            while(maxRight > 7) {
                maxRight -= 8;
            }
            maxRight = 7 - maxRight;
            if(maxUp < 8) {
                maxUp = 0;
            } else if (maxUp < 16) {
                maxUp = 1;
            } else if (maxUp < 24) {
                maxUp = 2;
            } else if (maxUp < 32) {
                maxUp = 3;
            } else if (maxUp < 40) {
                maxUp = 4;
            } else if (maxUp < 48) {
                maxUp = 5;
            } else if (maxUp < 56) {
                maxUp = 6;
            } else {
                maxUp = 7;
            }
            if(maxDown > 55) {
                maxDown = 0;
            } else if (maxDown > 47) {
                maxDown = 1;
            } else if (maxDown > 39) {
                maxDown = 2;
            } else if (maxDown > 31) {
                maxDown = 3;
            } else if (maxDown > 23) {
                maxDown = 4;
            } else if (maxDown > 15) {
                maxDown = 5;
            } else if (maxDown > 7) {
                maxDown = 6;
            } else {
                maxDown = 7;
            }

            for(int i = 1; i <= maxLeft; i++) {
                if(slot +i == pieceSlot) {
                    distance = i;
                    direction = "left";
                }
            }
            for(int i = 1; i <= maxRight; i++) {
                if(slot -i == pieceSlot) {
                    distance = i;
                    direction = "right";
                }
            }
            for(int i = 1; i <= maxUp; i++) {
                if(slot+i*8 == pieceSlot) {
                    distance = i;
                    direction = "up";
                }
            }
            for(int i = 1; i <= maxDown; i++) {
                if(slot-i*8 == pieceSlot) {
                    distance = i;
                    direction = "down";
                }
            }
            int maxTopLeft = Math.min(maxLeft, maxUp);
            int maxTopRight = Math.min(maxRight, maxUp);
            int maxBottomLeft = Math.min(maxLeft, maxDown);
            int maxBottomRight = Math.min(maxRight, maxDown);

            for(int i = 1; i <= maxTopLeft; i++) {
                if(slot +i*9 == pieceSlot) {
                    distance = i;
                    direction = "topLeft";
                }
            }
            for(int i = 1; i <= maxTopRight; i++) {
                if(slot +i*7 == pieceSlot) {
                    distance = i;
                    direction = "topRight";
                }
            }
            for(int i = 1; i <= maxBottomLeft; i++) {
                if(slot -i*7 == pieceSlot) {
                    distance = i;
                    direction = "bottomLeft";
                }
            }
            for(int i = 1; i <= maxBottomRight; i++) {
                if(slot -i*9 == pieceSlot) {
                    distance = i;
                    direction = "bottomRight";
                }
            }
            if(direction.isEmpty()) return false;

            if(direction.equals("left")) {
                int i = 1;
                while(i <= distance) {
                    if(i == distance) {
                        if(gamePanel.slots.get(pieceSlot-i).piece == null ||
                                (gamePanel.slots.get(pieceSlot).piece.isWhite && !gamePanel.slots.get(pieceSlot-i).piece.isWhite) ||
                                (!gamePanel.slots.get(pieceSlot).piece.isWhite && gamePanel.slots.get(pieceSlot-i).piece.isWhite)) {
                            return true;
                        }
                    }
                    if(gamePanel.slots.get(pieceSlot-i).piece != null) {
                        gamePanel.mouseHandler.isPieceSelected = false;
                        gamePanel.mouseHandler.pieceSelected = null;
                        return false;
                    }
                    i++;
                }
            } else if (direction.equals("right")) {
                int i = 1;
                while(i <= distance) {
                    if(i == distance) {
                        if(gamePanel.slots.get(pieceSlot+i).piece == null ||
                                (gamePanel.slots.get(pieceSlot).piece.isWhite && !gamePanel.slots.get(pieceSlot+i).piece.isWhite) ||
                                (!gamePanel.slots.get(pieceSlot).piece.isWhite && gamePanel.slots.get(pieceSlot+i).piece.isWhite)) {
                            return true;
                        }
                    }
                    if(gamePanel.slots.get(pieceSlot+i).piece != null)  return false;

                    i++;
                }
                return true;
            } else if (direction.equals("up")) {
                int i = 1;
                while(i <= distance) {
                    if(i == distance) {
                        if(gamePanel.slots.get(pieceSlot-i*8).piece == null ||
                                (gamePanel.slots.get(pieceSlot).piece.isWhite && !gamePanel.slots.get(pieceSlot-i*8).piece.isWhite) ||
                                (!gamePanel.slots.get(pieceSlot).piece.isWhite && gamePanel.slots.get(pieceSlot-i*8).piece.isWhite)) {
                            return true;
                        }
                    }
                    if(gamePanel.slots.get(pieceSlot-i*8).piece != null) return false;

                    i++;
                }
            } else if(direction.equals("down")){
                int i = 1;
                while(i <= distance) {
                    if(i == distance) {
                        if(gamePanel.slots.get(pieceSlot+i*8).piece == null ||
                                (gamePanel.slots.get(pieceSlot).piece.isWhite && !gamePanel.slots.get(pieceSlot+i*8).piece.isWhite) ||
                                (!gamePanel.slots.get(pieceSlot).piece.isWhite && gamePanel.slots.get(pieceSlot+i*8).piece.isWhite)) {
                            return true;
                        }
                    }
                    if(gamePanel.slots.get(pieceSlot+i*8).piece != null) return false;
                    i++;
                }
            } else if(direction.equals("topLeft")) {
                int i = 1;
                while(i <= distance) {
                    if(i == distance) {
                        if(gamePanel.slots.get(pieceSlot-i*9).piece == null ||
                                (gamePanel.slots.get(pieceSlot).piece.isWhite && !gamePanel.slots.get(pieceSlot-i*9).piece.isWhite) ||
                                (!gamePanel.slots.get(pieceSlot).piece.isWhite && gamePanel.slots.get(pieceSlot-i*9).piece.isWhite)) {
                            return true;
                        }
                    }
                    if(gamePanel.slots.get(pieceSlot-i*9).piece != null) return false;

                    i++;
                }
            } else if(direction.equals("topRight")) {
                int i = 1;
                while(i <= distance) {
                    if(i == distance) {
                        if(gamePanel.slots.get(pieceSlot-i*7).piece == null ||
                                (gamePanel.slots.get(pieceSlot).piece.isWhite && !gamePanel.slots.get(pieceSlot-i*7).piece.isWhite) ||
                                (!gamePanel.slots.get(pieceSlot).piece.isWhite && gamePanel.slots.get(pieceSlot-i*7).piece.isWhite)) {
                            return true;
                        }
                    }
                    if(gamePanel.slots.get(pieceSlot-i*7).piece != null) return false;

                    i++;
                }
            } else if(direction.equals("bottomLeft")) {
                int i = 1;
                while(i <= distance) {
                    if(i == distance) {
                        if(gamePanel.slots.get(pieceSlot+i*7).piece == null ||
                                (gamePanel.slots.get(pieceSlot).piece.isWhite && !gamePanel.slots.get(pieceSlot+i*7).piece.isWhite) ||
                                (!gamePanel.slots.get(pieceSlot).piece.isWhite && gamePanel.slots.get(pieceSlot+i*7).piece.isWhite)) {
                            return true;
                        }
                    }
                    if(gamePanel.slots.get(pieceSlot+i*7).piece != null) return false;

                    i++;
                }
            } else { // bottom right
                int i = 1;
                while(i <= distance) {
                    if(i == distance) {
                        if(gamePanel.slots.get(pieceSlot+i*9).piece == null ||
                                (gamePanel.slots.get(pieceSlot).piece.isWhite && !gamePanel.slots.get(pieceSlot+i*9).piece.isWhite) ||
                                (!gamePanel.slots.get(pieceSlot).piece.isWhite && gamePanel.slots.get(pieceSlot+i*9).piece.isWhite)) {
                            return true;
                        }
                    }
                    if(gamePanel.slots.get(pieceSlot+i*9).piece != null) return false;
                    i++;
                }
            }
        }
        else {
            // pawn
            if(gamePanel.mouseHandler.pieceSelected.isWhite) {
                if((slot+9 == pieceSlot && !gamePanel.slots.get(slot).piece.isWhite) ||
                        (slot+8 == pieceSlot && gamePanel.slots.get(slot).piece == null) ||
                        (slot+7 == pieceSlot && !gamePanel.slots.get(slot).piece.isWhite) ||
                        (slot+16 == pieceSlot && gamePanel.slots.get(slot).piece == null &&
                                gamePanel.slots.get(slot+8).piece == null &&
                                !gamePanel.mouseHandler.pieceSelected.hasMoved)){
                } else {
                    return false;
                }
            } else {
                if((slot-9 == pieceSlot && gamePanel.slots.get(slot).piece.isWhite) ||
                        (slot-8 == pieceSlot && gamePanel.slots.get(slot).piece == null) ||
                        (slot-7 == pieceSlot && gamePanel.slots.get(slot).piece.isWhite) ||
                        (slot-16 == pieceSlot && gamePanel.slots.get(slot).piece == null &&
                                gamePanel.slots.get(slot-8).piece == null &&
                                !gamePanel.mouseHandler.pieceSelected.hasMoved)){
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean safeSquare() {
        return true;
    }
}
