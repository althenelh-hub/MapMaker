package Data;

import WindowFolder.ComponentFactories.Namable;

public class StringReceiver implements Namable {
    String text;
    public StringReceiver(){

    }
    public StringReceiver(String name) {
        text = name;
    }
    public void giveName(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
