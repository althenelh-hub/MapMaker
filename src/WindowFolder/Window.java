package WindowFolder;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

import Data.MapData;
import Factories.FactoryManager;
import Factories.ForestFactory;
import Factories.UnitFactory;
import Listeners.CoordinateListener;
import Listeners.SizeListener;
import PaintInstructions.*;
import PaintInstructions.PaintForms.PaintCircleFactory;
import PaintInstructions.PaintForms.PaintTileFactory;
import PaintInstructions.Painter;
import WindowFolder.PaintPanel.BufferCommunication;

public class Window extends JFrame {
    public Window() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        setVisible(true);
        setLayout(new BorderLayout());
        MapGrid mapGrid = new MapGrid(10,10,20,1, new Point(0,0));
        setMinimumSize(new Dimension(mapGrid.getWidth() + 100,mapGrid.getHeight()+100));
        BufferedImage mapSave = new BufferedImage(1000,1000, BufferedImage.TYPE_INT_RGB);
        PaintImageV2 imageInstruction = new PaintImageV2(new Point(mapGrid.getWidth(),mapGrid.getHeight()), mapSave);
        BufferCommunication paintPanel = new BufferCommunication(imageInstruction, new Point(mapGrid.getWidth(),mapGrid.getHeight()));
        MapData data = new MapData(mapGrid.columns, mapGrid.rows);
        BufferedQueuedPanelV2 bufferedPanel = new BufferedQueuedPanelV2(mapSave, paintPanel);
        GridInstruction grid = new GridInstruction(mapGrid, Color.RED, Color.BLUE);
        paintPanel.setPreferredSize(new Dimension(300,300));
        add(paintPanel, BorderLayout.CENTER);
        paintPanel.setStartingPoint();
        bufferedPanel.paintInit(grid, new Rectangle(0,0,mapGrid.getWidth(),mapGrid.getHeight()));
        FactoryFormCommunication communication = new FactoryFormCommunication();
        communication.addFactory("Forest", new ForestFactory("Forest"), new PaintTileFactory());
        UnitFactory unitFactory = new UnitFactory("Unit");
        communication.addFactory("Unit", unitFactory, new PaintCircleFactory());
        paintPanel.addComponentListener(new SizeListener(mapGrid));
        FactoryManager factoryManager = communication.getFactoryManager();
        Painter painter = new Painter(bufferedPanel);
        DialogManager dialogManager = new DialogManager(communication.getFormFactoryManager(), factoryManager, painter);
        MObjectShow mObjectShow = new MObjectShow();
        CoordinateListener listener = new CoordinateListener(bufferedPanel, mapGrid, factoryManager, painter, mObjectShow, data);
        GameObjectPanel gameObjectPanel = new GameObjectPanel(factoryManager, painter, dialogManager, unitFactory.getFaction(),
                mObjectShow);
        add(gameObjectPanel, BorderLayout.WEST);
        add(mObjectShow.getMainPanel(), BorderLayout.EAST);
        MapControl mapControl = new MapControl(mapSave, paintPanel, mapGrid, imageInstruction, data, painter);
        SizePanel sizePanel = new SizePanel(10, 10,20, mapControl);
        add(sizePanel, BorderLayout.SOUTH);
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
