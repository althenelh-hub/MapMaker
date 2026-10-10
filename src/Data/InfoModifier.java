package Data;

import Data.Characteristics.Characteristic;
import WindowFolder.ComponentFactories.RemoveStringStorage;


public interface InfoModifier extends RemoveStringStorage {
    void changeCharacteristicName(String name, String nextName);
    void addValue(String name, Characteristic<?> characteristic);
    void removeField(String name);
    void changeValue(String name, Characteristic<?> characteristic);
    void emptyFields();
}
