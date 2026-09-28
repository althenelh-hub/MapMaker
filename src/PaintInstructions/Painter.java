package PaintInstructions;

import Data.CellData;
import Data.TileData;
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
        currentForm.setRect(new Rectangle(0,0,20,20));
    }
    public void paintForm(CellData cellData, Rectangle rect) {
        ArrayList<PaintInstruction> instructions = new ArrayList<>();
        currentForm.setRect(rect);
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
    public void manageAllInstructions (Rectangle rect) {
        for (PaintForm entry : forms.values()) {
            entry.setRect(rect);
        }
    }

}
