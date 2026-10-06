package Data;

public class NamedCharacteristic {
    public Characteristic<?> characteristic;
    public StringReceiver receiver;
    public NamedCharacteristic(Characteristic<?> characteristic) {
        this.characteristic = characteristic;
        receiver = new StringReceiver();
    }
    public Characteristic<?> getCharacteristic() {
        return characteristic;
    }
    public StringReceiver getReceiver() {
        return receiver;
    }
}
