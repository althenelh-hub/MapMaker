package WindowFolder;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import PaintInstructions.*;
public class Window extends JFrame {
    public Window() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setVisible(true);
        BufferedImage mapSave = new BufferedImage(300,300, BufferedImage.TYPE_INT_RGB);
        Graphics g = mapSave.getGraphics();
        g.setColor(Color.RED);
        g.fillRect(0,0,300,300);
        PaintImage paintImage = new PaintImage(mapSave);
        InstructedPaintPanel paintedPanel = new InstructedPaintPanel();
        paintedPanel.setInstruction(paintImage);
        add(paintedPanel);
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new Window();
            }
        });
    }
}
