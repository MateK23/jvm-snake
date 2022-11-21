package snakeGame;


public class Main {
    public static void main(String[] args) {
        SnakeGameWithMatrix snakeGame = new SnakeGameWithMatrix(800, 800, 200);
        Thread threadMain = new Thread(snakeGame);
        threadMain.start();
    }
}