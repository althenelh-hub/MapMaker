package WindowFolder;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

import Listeners.CoordinateListener;
import Listeners.SizeListener;
import PaintInstructions.*;
public class Window extends JFrame {
    public Window() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setVisible(true);
        setLayout(new BorderLayout());
        BufferedImage mapSave = new BufferedImage(300,300, BufferedImage.TYPE_INT_RGB);
        MapGrid mapGrid = new MapGrid(10,10,20,1, new Point(0,0));
        BufferCommunication paintPanel = new BufferCommunication(mapSave, new Point(mapGrid.getWidth(), mapGrid.getHeight()));
        BufferedQueuedPanelV2 bufferedPanel = new BufferedQueuedPanelV2(mapSave, paintPanel);
        GridInstruction grid = new GridInstruction(mapGrid, Color.RED, Color.BLUE);
        ArrayList<PaintInstruction> instructions = new ArrayList<>();
        instructions.add(grid);
        paintPanel.setPreferredSize(new Dimension(300,300));
        add(paintPanel, BorderLayout.CENTER);
        add(new JPanel(), BorderLayout.LINE_START);
        bufferedPanel.paintInstructions(instructions, new Rectangle(0,0,300,300));
        paintPanel.addComponentListener(new SizeListener(mapGrid));
        CoordinateListener listener = new CoordinateListener(bufferedPanel, mapGrid);
        //paintPanel.setAlignmentX();
        System.out.println("window " + paintPanel.getBounds());
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
