package Data;


import WindowFolder.DisplayMethod;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class Characteristic<T> {
    protected T value;
    public Characteristic() {

    }
    public void setValue(T value) {
        this.value = value;
    }
    public abstract JComponent display(boolean changeable);
    public String toString() {
        return value.toString();
    }

}
