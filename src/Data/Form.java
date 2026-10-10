package Data;

import Data.Characteristics.Characteristic;
import Terrain.MapObject;

import java.util.HashMap;

public class Form implements InfoModifier {
    StringReceiver name;
    HashMap<String, Characteristic<?>> characteristics;
    public Form(StringReceiver name) {
        this.name = name;
        characteristics = new HashMap<>();
    }
    public String getName() {
        return name.text;
    }
    public void fillMapObject(MapObject MObject) {
        for(String s : characteristics.keySet()){
            MObject.addValue(s, characteristics.get(s));
        }
    }

    @Override
    public void changeCharacteristicName(String name, String nextName) {
        Characteristic<?> characteristic = characteristics.get(name);
        characteristics.remove(name);
        characteristics.put(nextName, characteristic);
        System.out.println(nextName + " " + characteristics.get(nextName));
    }


    public void changeValue(String name, Characteristic<?> characteristic) {
        characteristics.replace(name, characteristic);
    }
    public void emptyFields() {
        characteristics.clear();
    }
    public void addValue(String name, Characteristic<?> characteristic) {
        characteristics.put(name,  characteristic);
    }
    public void removeField(String name) {
        characteristics.remove(name);
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(String s : characteristics.keySet()) {
            builder.append("Name: ").append(s).append(" Value: ").append(characteristics.get(s)).append("\n");
        }
        return builder.toString();
    }
}
