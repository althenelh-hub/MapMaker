package WindowFolder;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

import Factories.FactoryManager;
import Factories.ForestFactory;
import Factories.UnitFactory;
import Listeners.CoordinateListener;
import Listeners.SizeListener;
import PaintInstructions.*;
import PaintInstructions.Painter;

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
        FactoryFormCommunication communication = new FactoryFormCommunication();
        communication.addFactory("Forest", new ForestFactory("Forest"), new PaintTile(), new PaintTileFactory());
        communication.addFactory("Unit", new UnitFactory("Unit"), new PaintCircle(), new PaintCircleFactory());
        paintPanel.addComponentListener(new SizeListener(mapGrid));
        FactoryManager factoryManager = communication.getFactoryManager();
        FormManager formManager = communication.getFormManager();
        factoryManager.changeFactory("Forest");
        Painter painter = new Painter(bufferedPanel);
        CoordinateListener listener = new CoordinateListener(bufferedPanel, mapGrid, factoryManager.getFactorySwitch(), painter);
        DialogManager dialogManager = new DialogManager(communication.getFormFactoryManager());
        GameObjectPanel gameObjectPanel = new GameObjectPanel(factoryManager, painter, dialogManager, formManager);
        add(gameObjectPanel, BorderLayout.WEST);
        //new MOCreation(factoryManager, formManager, painter, gameObjectPanel);
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
