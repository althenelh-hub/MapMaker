package Data;


public class Characteristic {
    StringReceiver name;
    public int value;
    public Characteristic() {
        name = new StringReceiver();
    }
    public String toString() {
        return "Name: " + name + "Value: " + value;
    }
    public String getName() {
        return name.text;
    }
    public StringReceiver getReceiver () {
        return name;
    }
}
