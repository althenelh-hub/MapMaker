package PaintInstructions;

import Data.CellData;
import WindowFolder.*;
import Terrain.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Painter { //how do i now which PaintForm i use
    BufferedQueuedPanelV2 paintPanel;
    PaintForm currentForm;
    HashMap<GameName, PaintForm> forms = new HashMap<>();
    public Painter(BufferedQueuedPanelV2 paintPanel) {
        this.paintPanel = paintPanel;
        currentForm = new PaintTile();
        currentForm.setColor(Color.BLACK);
        forms.put(GameName.FOREST, currentForm);
        currentForm = new PaintCircle();
        currentForm.setColor(Color.BLUE);
        forms.put(GameName.WARRIOR, currentForm);

    }
    public void paintForm(CellData cellData, Rectangle rect) {
        ArrayList<PaintInstructionV2> instructions = new ArrayList<>();
        instructions.add(currentForm);
        PaintForm instruction;
        for(GameName name : cellData.mapObjects) {
            instruction = forms.get(name);
            if(currentForm.layer < instruction.layer) {
                instructions.add(instruction);
            }
        }
        paintPanel.paintInstructions(instructions, rect);
    }
    public void setCurrentForm(GameName name) {
        currentForm = forms.get(name);
    }
}
