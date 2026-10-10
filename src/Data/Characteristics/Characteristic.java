package Data.Characteristics;

import javax.swing.*;

public abstract class Characteristic<T> {
    protected T value;
    final int itemIndex;
    public Characteristic(int itemIndex) {
        this.itemIndex = itemIndex;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public abstract JComponent display();
    public String toString() {
        return value.toString();
    }
    public int getItemIndex() {
        return itemIndex;
    }
}
