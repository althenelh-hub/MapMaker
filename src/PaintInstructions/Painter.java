package PaintInstructions;

import WindowFolder.BufferedPaintPanelV2;
import Terrain.*;

import java.awt.*;
import java.util.HashMap;
import Data.CellData;

public class Painter { //how do i now which PaintForm i use
    BufferedPaintPanelV2 paintPanel;
    PaintForm currentForm;
    HashMap<GameName, Color> colors = new HashMap<>();
    HashMap<Form, PaintForm> forms = new HashMap<>();
    public Painter(BufferedPaintPanelV2 paintPanel) {
        this.paintPanel = paintPanel;
        forms.put(Form.Tile, new PaintTile());
        forms.put(Form.Circle, new PaintCircle());
    }
    public void paintForm() {
        paintPanel.paintInstruction(currentForm, currentForm.rect);
    }
    public void paintForm(CellData cellData) {
        paintPanel.paintInstruction(currentForm, currentForm.rect);
        int currentLayer = currentForm.layer;

    }
    public PaintInstruction fillInstruction(Unit unit) {
        PaintForm form = forms.get(Form.Circle);
        form.setColor(colors.get(unit.name));
        return form;
    }
    public void manageAllInstructions (Rectangle rect) {
        for (PaintForm entry : forms.values()) {
            entry.setRect(rect);
        }
    }
    public void manageInstruction(Form form) {
        if(forms.containsKey(form)) {
            currentForm = forms.get(form);
        }
    }
    public void manageInstruction(GameName name) {
        if(colors.containsKey(name)) {
            currentForm.setColor(colors.get(name));
        }
    }
    public void paintOverUnit(GameName name) {

    }
}
