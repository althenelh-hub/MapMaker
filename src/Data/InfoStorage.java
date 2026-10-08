package Data;

import Terrain.MapObject;

import java.util.ArrayList;

public interface InfoStorage {
    void addField(NamedCharacteristic field);
    void removeField(int index);
    void changeField(int index, Characteristic<?> value);
    void emptyForm();
    NamedCharacteristic createField();
}
