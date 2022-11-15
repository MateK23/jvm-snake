package snakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class SnakeGame extends JFrame implements Runnable, KeyListener {
    Graphics g = null;

    private int posX;
    private int posY;

    private int currentTailPosX = 0;
    private int currentTailPosY = 0;
    private int prevTailPosX = 0;
    private int prevTailPosY = 0;


    int stepSize = 20;
    int playerSize = 20;
    char direction = 'd'; // W A S D
    int gameTickTime;
    int gameWidth;
    int gameHeight;
    boolean gameOver = false;

    // Tail
    int length = 3;
    int travelledFromTmpTail = 0;
    boolean skipLengthCheck = false;

    // Food
    int foodPosX;
    int foodPosY;
    boolean newFood = true;

    public SnakeGame(int gameWidth, int gameHeight, int gameTickTime) {
        super("Snake");

        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.gameTickTime = gameTickTime;

        posX = gameWidth / 2;
        posY = gameHeight / 2;

        addKeyListener(this);

        init();
    }

    private void init() {
        setBounds(100, 100, gameWidth, gameHeight);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
    }

    private void drawBox() {
        if (g != null) {
            // clearScreen();

            g.fillRect(posX, posY, playerSize, playerSize);
            repaint();
        }
    }

    private void clearScreen() {


        if (g != null) {
            g.clearRect(0, 0, gameWidth, gameHeight);

            repaint();
        }
    }

    private void setDirection(char direction) {
        if (!((this.direction == 'w' && direction == 's') || (this.direction == 's' && direction == 'w') || (this.direction == 'a' && direction == 'd') || (this.direction == 'd' && direction == 'a'))) {
            this.direction = direction;
        }
    }

    private void handleTail() {
        char previousDirection = direction;
        switch (previousDirection){
            case 'w' -> currentTailPosY = currentTailPosY + stepSize * length;
            case 'a' -> currentTailPosX = currentTailPosX - stepSize * length;
            case 's' -> currentTailPosY = currentTailPosY - stepSize * length;
            case 'd' -> currentTailPosX = currentTailPosX + stepSize * length;
            default -> {

            }
        }
        // TODO
        // Every game tick:
        // movement direction + length * step - Current tail location
        // Store tail current tail location as previous tail location
        // on next tick clear that tail location
        // length * stepSize

        if (travelledFromTmpTail / stepSize == length || skipLengthCheck) { // for start
            skipLengthCheck = true;
            // TODO
            // Current lenght reached
            // Clear previous tail

            g.clearRect(prevTailPosX, prevTailPosY, playerSize, playerSize); // Clear previous tail
            repaint();
        }




        /*
        if (currentTailPosX == 0) { // Init
            // prevTailPosX = posX;
            prevTailPosY = posY;

            currentTailPosX = posX;
            currentTailPosY = posY;
        }

        prevTailPosX = posX - length * stepSize;

        if (travelledFromTmpTail / stepSize == length || skipLengthCheck) { // If length reached
            skipLengthCheck = true;

            System.out.println(prevTailPosX + " " + posX);
            g.clearRect(prevTailPosX, prevTailPosY, playerSize, playerSize); // Clear previous tail
            repaint();
        }



        if (currentTailPosX == 0){ // Init
            prevTailPosX = posX;
            prevTailPosY = posY;

            currentTailPosX = posX;
            currentTailPosY = posY;
        }

        currentTailPosX = posX - length;
        currentTailPosY = posY;

        if (travelledFromTmpTail / stepSize == length){ // If length reached
            prevTailPosX = currentTailPosX;
            prevTailPosY = currentTailPosY;

            g.clearRect(prevTailPosX, prevTailPosY, playerSize, playerSize); // Clear previous tail
        }
        System.out.println(travelledFromTmpTail);

         */
    }

    private boolean selfCollisionCheck() {
        // TODO
        return false;
    }

    private void spawnRandomFood() {
        Random random = new Random();
        foodPosX = stepSize * random.nextInt(2, (gameWidth - 40) / stepSize);
        foodPosY = stepSize * random.nextInt(3, (gameHeight - 40) / stepSize);
        if (g != null) g.fillRoundRect(foodPosX, foodPosY, playerSize, playerSize, 45, 45);
    }

    private void foodCollisionCheck() {
        if (posX == foodPosX && posY == foodPosY) {
            length++;
            newFood = true;
        }
    }

    @Override
    public void paint(Graphics graphics) {
        g = graphics;
        g.setColor(Color.GREEN);

        g.drawRect(20, 40, gameWidth - 40, gameHeight - 60);

        if (!gameOver) drawBox();
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


        while (posX <= gameWidth - 50 && posX >= 30 && posY >= 50 && posY <= gameHeight - 50) {
            switch (direction) {
                case 'w' -> {
                    posY = posY - stepSize;
                    drawBox();
                }
                case 'a' -> {
                    posX = posX - stepSize;
                    drawBox();
                }
                case 's' -> {
                    posY = posY + stepSize;
                    drawBox();
                }
                case 'd' -> {
                    posX = posX + stepSize;
                    drawBox();
                }
                default -> {

                }
            }

            // travelledFromTmpTail += stepSize;
            // handleTail();

            try {
                Thread.sleep(gameTickTime);
                foodCollisionCheck();
                // clearScreen();
                System.out.println(posX + " " + posY);
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
