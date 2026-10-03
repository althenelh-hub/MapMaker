package PaintInstructions;

import Data.CellData;
import WindowFolder.*;
import Terrain.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Painter { //how do I now which PaintForm I use
    BufferedQueuedPanelV2 paintPanel;
    PaintForm currentForm;
    HashMap<String, PaintForm> forms = new HashMap<>();
    public Painter(BufferedQueuedPanelV2 paintPanel) {
        this.paintPanel = paintPanel;
    }
    public void addForm(String name, PaintForm form) {
        forms.put(name, form);
    }
    public void paintForm(CellData cellData, Rectangle rect) {
        ArrayList<PaintInstructionV2> instructions = new ArrayList<>();
        instructions.add(currentForm);
        PaintForm instruction;
        for(String name : cellData.mapObjects) {
            instruction = forms.get(name);
            if(currentForm.layer < instruction.layer) {
                instructions.add(instruction);
            }
        }
        paintPanel.paintInstructions(instructions, rect);
    }
    public void setCurrentForm(String name) {
        currentForm = forms.get(name);
    }
}
