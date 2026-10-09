package Data;

import WindowFolder.ComponentFactories.RemoveStringStorage;

import java.util.Set;

public interface InfoModifier extends RemoveStringStorage {
    void changeCharacteristicName(String name, String nextName);
    void addValue(String name, Characteristic<?> characteristic);
    void removeField(String name);
    void changeValue(String name, Characteristic<?> characteristic);
    void emptyFields();
}
