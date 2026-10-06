package Data;

public abstract class CharacteristicField<T> {
    protected StringReceiver name;
    protected Characteristic<T> characteristic;
    protected int index;
    public CharacteristicField(StringReceiver name, Characteristic<T> characteristic, int index) {
        this.name = name;
        this.characteristic = characteristic;
        this.index = index;
    }
    public String getName() {
        return name.text;
    }
    public StringReceiver getReceiver () {
        return name;
    }
    public void setCharacteristic(Characteristic<T> characteristic) {
        this.characteristic = characteristic;
    }
    public abstract void display();
}
