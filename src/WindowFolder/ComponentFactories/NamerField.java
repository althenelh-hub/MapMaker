package WindowFolder.ComponentFactories;

import WindowFolder.Listeners.CharacteristicName;

import javax.swing.*;

public class NamerField extends JTextField {
    public NamerField(Namable name) {
        addActionListener(new CharacteristicName(name));
    }
}

