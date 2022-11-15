import javax.swing.*;
import java.awt.*;

public class FractalTree extends JFrame implements Runnable{

    public FractalTree(){
        super("Fractal Tree");
        setBounds(100,100,800,600);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void drawTree(Graphics graphics, int x1, int y1, double angle, int depth){
        if (depth == 0) return;
        int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * 10);
        int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * 10);

        graphics.drawLine(x1,y1, x2, y2);

        drawTree(graphics, x2, y2, angle - 20, depth - 1);
        drawTree(graphics, x2, y2, angle + 20, depth - 1);
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        drawTree(graphics, 400, 500, -90, 9);
    }

    @Override
    public void run() {

    }
}
