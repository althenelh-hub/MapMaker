package Data;

import WindowFolder.DisplayMethod;

import java.util.HashMap;

public class CharacteristicFactory {
    HashMap<DisplayMethod, Creator> creators = new HashMap<>();
    public CharacteristicFactory() {
        creators.put(DisplayMethod.CheckBox, new BoolCreator());
        creators.put(DisplayMethod.TextFieldString, new StringCreator());
        creators.put(DisplayMethod.TextFieldInt, new IntegerCreator());
    }
    public Characteristic<?> create(DisplayMethod displayMethod) {
        Creator creator = creators.get(displayMethod);
        return creator.create();
    }
}
interface Creator {
    Characteristic<?> create();
}
class BoolCreator implements Creator {
    public BoolCharacteristic create() {
        return new BoolCharacteristic();
    }
}
class StringCreator implements Creator {
    public StringCharacteristic create() {
        return new StringCharacteristic();
    }
}
class IntegerCreator implements Creator {
    public IntegerCharacteristic create() {
        return new IntegerCharacteristic();
    }
}