package Data;

import Terrain.MapObject;

import java.util.ArrayList;

public class Form implements InfoStorage {
    StringReceiver name;
    ArrayList<NamedCharacteristic> fields;
    public Form(StringReceiver name) {
        this.name = name;
        fields = new ArrayList<>();
    }
    public String getName() {
        return name.text;
    }
    public void fillMapObject(MapObject MObject) {
        for(NamedCharacteristic characteristic : fields) {
            System.out.println(characteristic.getCharacteristic().value);
            MObject.addValue(characteristic.receiver.text, characteristic.getCharacteristic());
        }
    }
    public NamedCharacteristic createField() {
        return new NamedCharacteristic(new IntegerCharacteristic());
    }
    public void changeField(int index, Characteristic<?> characteristic) {
        fields.get(index).characteristic = characteristic;
    }
    public void setStringReceiver(StringReceiver receiver) {
        this.name = receiver;
    }
    public void emptyForm() {
        fields.clear();
    }
    public void addField(NamedCharacteristic characteristic) {
        fields.add(characteristic);
    }
    public void removeField(int index) {
        fields.remove(index);
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(NamedCharacteristic c : fields) {
            builder.append(c).append("\n");
        }
        return builder.toString();
    }
}
