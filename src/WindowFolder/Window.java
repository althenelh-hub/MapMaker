package WindowFolder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.*;

import Listeners.CoordinateListener;
import PaintInstructions.*;
public class Window extends JFrame {
    public Window() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setVisible(true);
        BufferedImage mapSave = new BufferedImage(300,300, BufferedImage.TYPE_INT_RGB);
        GridInstruction grid = new GridInstruction(10,10,20,Color.RED, Color.BLACK);
        InstructedPaintPanel paintPanel = new InstructedPaintPanel();
        add(paintPanel);
        BufferedPaintPanelV2 bufferedPanel = new BufferedPaintPanelV2(mapSave, paintPanel);
        bufferedPanel.paintInstruction(grid);
        CoordinateListener listener = new CoordinateListener(bufferedPanel);

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
