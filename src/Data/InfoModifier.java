package Data;

import Data.Characteristics.Characteristic;
import Data.Characteristics.NamedStorage;
import WindowFolder.ComponentFactories.RemoveStringStorage;


public interface InfoModifier extends RemoveStringStorage, NamedStorage {
    void changeCharacteristicName(String name, String nextName);
    void addValue(String name, Characteristic<?> characteristic);
    void removeField(String name);
    void changeValue(String name, Characteristic<?> characteristic);
    boolean contains(String name);
    void emptyFields();
}
