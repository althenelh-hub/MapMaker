package Data;

public interface InfoModifier {
    void changeCharacteristicName(String name, String nextName);
    void addValue(String name, Characteristic<?> characteristic);
    void removeField(String name);
    void changeValue(String name, Characteristic<?> characteristic);
}
