package Data;

import WindowFolder.DisplayMethod;

public class DisplayItem {
    String name;
    DisplayMethod displayMethod;
    public DisplayItem(String name, DisplayMethod method) {
        this.name = name;
        displayMethod = method;
    }
    public String toString() {
        return name;
    }
    public DisplayMethod getDisplayMethod() {
        return displayMethod;
    }
}
