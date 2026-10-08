package WindowFolder;

import java.awt.*;

public class Keeper<T> {
    protected T value;
    public void setValue(T value) {
        this.value = value;
    }
    public T getValue() {
        return value;
    }
}
