package Data;

import Data.Characteristics.Characteristic;
import Data.Characteristics.IntegerCharacteristic;
import Terrain.MapObject;
import WindowFolder.Receiver;

import java.util.HashMap;

public class Form implements InfoModifier, Receiver<String> {
    String name;
    HashMap<String, Characteristic<?>> characteristics;
    public Form(String name) {
        this.name = name;
        characteristics = new HashMap<>();
        characteristics.put("Movement", new IntegerCharacteristic(5));
    }
    public void receive(String value) {
        name = value;
    }
    public String getName() {
        return name;
    }
    public void fillMapObject(MapObject MObject) {
        for(String s : characteristics.keySet()){
            MObject.addValue(s, characteristics.get(s));
        }
    }
    public boolean contains(String key) {
        return characteristics.containsKey(key);
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
