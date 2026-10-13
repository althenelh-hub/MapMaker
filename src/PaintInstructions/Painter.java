package PaintInstructions;

import Data.CellData;
import Data.MapData;
import PaintInstructions.PaintForms.PaintForm;
import WindowFolder.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import PaintInstructions.PaintForms.*;
import WindowFolder.PaintPanel.BufferedQueuedPanelV2;

public class Painter implements PaintAdder, PaintChanger, PaintInjector{ //how do I now which PaintForm I use
    BufferedQueuedPanelV2 paintPanel;
    PaintForm currentForm;
    HashMap<String, PaintForm> forms = new HashMap<>();
    public Painter(BufferedQueuedPanelV2 paintPanel) {
        this.paintPanel = paintPanel;
        PaintOval oval = new PaintOval();
        PaintTile baseTerrain = new PaintTile();
        baseTerrain.setColor(Color.GRAY);
        forms.put("baseTerrain", baseTerrain);
        oval.setColor(Color.BLUE);
        forms.put("Ally", oval);
        oval = new PaintOval();
        oval.setColor(Color.RED);
        forms.put("Enemy", oval);
    }
    public void repaintWhole(MapData data, MapGrid grid, BufferedImage image) {
        ArrayList<PaintInstructionV2> instructions = new ArrayList<>();
        Graphics g = image.createGraphics();
        for(int i = 0;i < grid.getColumnsCount();i++) {
            for(int j = 0;j < grid.getRowsCount();j++) {
               Point coordinate = new Point(i,j);
               CellData cellData = data.getCellData(coordinate);
               for(String name : cellData.mapObjects) {
                   forms.get(name).fulfill(g, grid.getRectToFill(coordinate));
               }
            }
        }
        paintPanel.instructionPanel.repaint();

    }
    public void addForm(String name, PaintForm form) {
        forms.put(name, form);
    }
    public void paintForm(CellData cellData, Rectangle rect) {
        ArrayList<PaintInstructionV2> instructions = new ArrayList<>();
        PaintForm instruction;
        for(String name : cellData.mapObjects) {
            instruction = forms.get(name);
            System.out.println(instruction + " instruction " + name);
            instructions.add(instruction);  //need layer sort

        }
        paintPanel.paintInstructions(instructions, rect);
    }
    public void setCurrentForm(String name) {
        currentForm = forms.get(name);
        System.out.println("Set color: " + currentForm.equals(forms.get(name)));

    }
}
