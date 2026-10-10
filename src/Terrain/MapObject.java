package Terrain;

import Data.Characteristics.Characteristic;

import java.util.HashMap;

public class MapObject {
    public String name;
    //protected boolean modifiable;
    protected HashMap<String, Characteristic<?>> values;
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
    public void shareValues(MapObject mObject) {
        mObject.values.clear();
        for(String s : values.keySet()) {
            mObject.addValue(s, values.get(s));
        }
    }

}
