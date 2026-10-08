package Terrain;

import Data.Characteristic;
import Data.Form;
import Data.NamedCharacteristic;
import Data.StringReceiver;

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
    public void shareValues(Form form) {
        for(String s : values.keySet()) {
            form.addField(new NamedCharacteristic(new StringReceiver(s), values.get(s)));
        }
    }
}
