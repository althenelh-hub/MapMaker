package WindowFolder.ComponentFactories;

import Data.StringReceiver;
import WindowFolder.InputChecker;
import WindowFolder.Listeners.CharacteristicName;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NamerField extends JTextField {
    public NamerField(Namable name) {
        addActionListener(new CharacteristicName(name));
    }
}

