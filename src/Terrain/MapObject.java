package Terrain;

import Data.Characteristic;

import java.util.HashMap;

public class MapObject {
    public String name;
    HashMap<String, Characteristic<?>> values;
    public MapObject() {
        values = new HashMap<>();
    }
    public void addValue(String name, Characteristic<?> value) {
        values.put(name, value);
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(String s : values.keySet()) {
            builder.append(s).append(": ").append(values.get(s)).append("\n");
        }
        return builder.toString();
    }
}
