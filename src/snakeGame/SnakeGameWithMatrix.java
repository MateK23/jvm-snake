package snakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class SnakeGameWithMatrix extends JFrame implements Runnable, KeyListener {
    // Setup & Defaults
    int unitSize = 20;
    char direction = 'd'; // W A S D
    int gameTickTime;
    boolean gameOver = false;

    // Matrix
    int[][] gameGrid;
    int gridWidth;
    int gridHeight;

    // UI
    Graphics g = null;
    Graphics2D g2 = null;
    int borderWidth;
    int borderHeight;

    // Tail
    int length = 3;

    // Food
    boolean newFood = true;

    public SnakeGameWithMatrix(int gridWidth, int gridHeight, int gameTickTime) {
        super("Snake");

        // Grid value meaning:
        // 0 - empty
        // 1 - player
        // 2 - player head ?
        // 3 - food


        gameGrid = new int[gridWidth][gridHeight];

        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                gameGrid[i][j] = 0;
            }
        }

        borderWidth = gridWidth * unitSize;
        borderHeight = gridHeight * unitSize;

        this.gameTickTime = gameTickTime;

        addKeyListener(this);

        init();
    }

    private void init() {
        setBounds(100, 100, borderWidth, borderHeight); // 100 100 ?
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
    }

    private void drawBoard() {
        if (g != null) {
            clearScreen();
            for (int i = 0; i < gridWidth; i++) {
                for (int j = 0; j < gridHeight; j++) {
                    if (gameGrid[i][j] == 0) {        // Empty
                        g.clearRect(i * unitSize, j * unitSize, unitSize, unitSize);
                    } else if (gameGrid[i][j] == 1) { // Snake Tail
                        g.fillRect(i * unitSize, j * unitSize, unitSize, unitSize);
                    } else if (gameGrid[i][j] == 2) { // Snake Head
                        // TODO Head different visual?
                        g.fillRect(i * unitSize, j * unitSize, unitSize, unitSize);
                    } else if (gameGrid[i][j] == 3) { // Food
                        g.fillRoundRect(i * unitSize, j * unitSize, unitSize, unitSize, 45, 45);
                    }
                }
            }
            repaint();
        }
    }

    private void clearScreen() {
        if (g != null) {
            g.clearRect(0, 0, borderWidth, borderHeight);

            repaint();
        }
    }

    private void setDirection(char direction) {
        if (!((this.direction == 'w' && direction == 's') || (this.direction == 's' && direction == 'w') || (this.direction == 'a' && direction == 'd') || (this.direction == 'd' && direction == 'a'))) {
            this.direction = direction;
        }
    }

    private void moveAndCheckNextPosition(char direction){
        if (gameGrid[0][0] > 0) {
            // TODO
        }
    }

    private void handleTail() {

        // TODO
        // Every game tick:
        // movement direction + length * step - Current tail location
        // Store tail current tail location as previous tail location
        // on next tick clear that tail location
        // length * stepSize


        // TODO
        // Current lenght reached
        // Clear previous tail

        // g.clearRect(prevTailPosX, prevTailPosY, unitSize, unitSize); // Clear previous tail
        repaint();


    }

    private void spawnRandomFood() {
        Random random = new Random();
        int randX = random.nextInt(0, (gridWidth));
        int randY = random.nextInt(0, (gridHeight));
        gameGrid[randX][randY] = 3;
    }

    private void eatFood() {
        length++;
        newFood = true;
    }

    @Override
    public void paint(Graphics graphics) {
        g = graphics;
        g.setColor(Color.GREEN);

        // g.drawRect(20, 40, gameWidth - 40, gameHeight - 60); // TODO Draw border

        if (!gameOver) drawBoard();
        else clearScreen();
        // spawnRandomFood();
        if (newFood) {
            spawnRandomFood();
            newFood = false;
        }
    }

    // Game loop

    @Override
    public void run() {
        while (true) {
            int i = 0;
            switch (direction) {
                case 'w' -> {
                    gameGrid[][] =posY -;
                    drawBox();
                }
                case 'a' -> {
                    posX = posX -;
                    drawBox();
                }
                case 's' -> {
                    posY = posY +;
                    drawBox();
                }
                case 'd' -> {
                    posX = posX +;
                    drawBox();
                }
                default -> {

                }
            }

            try {
                Thread.sleep(gameTickTime);
                drawBoard();
                // clearScreen();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gameOver = true;
        clearScreen();
    }

    // Input

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w' -> setDirection('w');
            case 'a' -> setDirection('a');
            case 's' -> setDirection('s');
            case 'd' -> setDirection('d');
            default -> {
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
