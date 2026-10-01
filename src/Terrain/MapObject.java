package Terrain;

import java.util.HashMap;

public class MapObject {
    public GameName name;
    HashMap<String, Integer> values;
    public void addValue(String name, Integer value) {
        values.put(name, value);
    }
}
