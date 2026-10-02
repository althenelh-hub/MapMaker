package Data;

import Factories.Factory;
import Terrain.MapObject;

import java.util.ArrayList;

public class Form {
    StringReceiver name;
    ArrayList<Characteristic> fields;
    public Form(StringReceiver name) {
        this.name = name;
        fields = new ArrayList<>();
    }
    public String getName() {
        return name.text;
    }
    public void fillMapObject(MapObject MObject) {
        for(Characteristic characteristic : fields) {
            MObject.addValue(characteristic.getName(), characteristic.value);
        }
    }
    public void emptyForm() {
        fields.clear();
    }
    public void addField(Characteristic characteristic) {
        fields.add(characteristic);
    }
    public void removeField(int index) {
        fields.remove(index);
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Characteristic c : fields) {
            builder.append(c).append("\n");
        }
        return builder.toString();
    }
}
