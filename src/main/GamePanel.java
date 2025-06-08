package main;

import chess.Board;
import chess.Piece;
import chess.Queen;
import chess.Slot;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    public final int slotSize = 60;
    public final int screenWidth = 800;
    public final int screenHeight = slotSize*8;
    public final int FPS = 30;


    public Color slotGreen = new Color(119, 149, 86);
    public Color slotWhite = new Color(235, 236, 208);

    public ArrayList<Slot> slots = new ArrayList<>(64);

    public UI ui = new UI(this);
    public Board board = new Board(this);
    public MoveValidator mv = new MoveValidator(this);
    public MouseHandler mouseHandler = new MouseHandler(this);
    public KeyHandler keyHandler = new KeyHandler(this);

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(0, 0, 0));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.setFocusable(true);
    }

    public void setupProgram() {
    }
    public void update() {
        for(int i = 0; i < 8; i++) {
            if(slots.get(i).piece != null && slots.get(i).piece.id == 5) {
                slots.get(i).piece = new Queen(true, 4);
            }
        }
        for(int i = 56; i < 64; i++) {
            if(slots.get(i).piece != null && slots.get(i).piece.id == 5) {
                slots.get(i).piece = new Queen(false, 4);
            }
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        ui.draw(g2);
        g2.dispose();
    }
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
}
