package WindowFolder;

import PaintInstructions.PaintDoubleCircle;

import java.awt.*;
import java.util.HashMap;

public class FactionManager {
    HashMap<String, Color> factions = new HashMap<>();
    HashMap<String, ColorSetter> unitsInstructions = new HashMap<>();
    public void setColor(String unitName, String faction) {
        unitsInstructions.get(unitName).setColor(factions.get(faction));
    }
    public void addFaction(String name, Color color) {
        factions.put(name, color);
    }
    public void addInstruction(String name, PaintDoubleCircle doubleCircle) {

    }
}
