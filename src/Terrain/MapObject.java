package Terrain;

import java.util.HashMap;

public class MapObject {
    public String name;
    HashMap<String, Integer> values;
    public MapObject() {
        values = new HashMap<>();
    }
    public void addValue(String name, Integer value) {
        values.put(name, value);
    }
}
