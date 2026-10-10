package Data;

import WindowFolder.ComponentFactories.Namable;

public class StringReceiver implements Namable {
    String text;
    public StringReceiver(){

    }
    public void giveName(String text) {
        this.text = text;
    }
}
