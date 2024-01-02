import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {




    Panel panel;


    Frame() {
        panel = new Panel();
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.setTitle("PONG");
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);


    }
}
