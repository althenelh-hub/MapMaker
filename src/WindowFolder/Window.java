package WindowFolder;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

import Factories.FactoryManager;
import Factories.ForestFactory;
import Listeners.CoordinateListener;
import Listeners.SizeListener;
import PaintInstructions.*;
public class Window extends JFrame {
    public Window() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        setVisible(true);
        setLayout(new BorderLayout());
        MapGrid mapGrid = new MapGrid(10,10,20,1, new Point(0,0));
        setMinimumSize(new Dimension(mapGrid.getWidth() + 100,mapGrid.getHeight()+100));
        BufferedImage mapSave = new BufferedImage(300,300, BufferedImage.TYPE_INT_RGB);
        PaintImageV2 imageInstruction = new PaintImageV2(new Point(mapGrid.getWidth(),mapGrid.getHeight()), mapSave);
        BufferCommunication paintPanel = new BufferCommunication(imageInstruction, new Point(mapGrid.getWidth(),mapGrid.getHeight()));
        BufferedQueuedPanelV2 bufferedPanel = new BufferedQueuedPanelV2(mapSave, paintPanel);
        GridInstruction grid = new GridInstruction(mapGrid, Color.RED, Color.BLUE);
        paintPanel.setPreferredSize(new Dimension(300,300));
        add(paintPanel, BorderLayout.CENTER);
        paintPanel.setStartingPoint();
        bufferedPanel.paintInit(grid, new Rectangle(0,0,mapGrid.getWidth(),mapGrid.getHeight()));
        paintPanel.addComponentListener(new SizeListener(mapGrid));
        FactoryManager manager = new FactoryManager();
        manager.addFactory("Forest", new ForestFactory());
        manager.changeFactory("Forest");
        CoordinateListener listener = new CoordinateListener(bufferedPanel, mapGrid, manager);
        new MOCreation(manager, new PaintTile());
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
