package WindowFolder.ComponentFactories;

import java.awt.*;
import java.util.HashMap;

public class ColorManager {
   static HashMap<String, Color> colors = new HashMap<>();
    public static Color getColor(String name) {
        return colors.get(name);
    }
    public static void addColor(String name, Color color) {
        colors.put(name, color);
    }
}
