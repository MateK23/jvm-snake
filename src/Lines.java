import javax.swing.*;
import java.awt.*;

public class Lines extends JFrame {
    public Lines(){
        super("Lines");
        setBounds(100,100,800,800);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void drawLine(Graphics graphics){


        graphics.drawLine(200, 200, 400, 400);

    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        drawLine(graphics);
    }
}
