package Data;

import WindowFolder.InputChecker;

import javax.swing.*;
import java.awt.event.*;
public class StringCharacteristic extends Characteristic<String> {
    public JTextField display(boolean changeable) {
        JTextField field = new JTextField();
        field.addActionListener(new StringSetter(this));
        return field;
    }
}
class StringSetter implements ActionListener {
    Characteristic<String> characteristic;
    final boolean changeable;
    public StringSetter(Characteristic<String> characteristic, boolean changeable) {
        this.characteristic = characteristic;
        this.changeable = changeable;
    }
    public void actionPerformed(ActionEvent e) {
        if(changeable) {
            JTextField field = (JTextField) e.getSource();
            String text = field.getText();
            InputChecker checker = new InputChecker();
            if (checker.checkPureLetters(text)) {
                characteristic.setValue(text);
            }
        }
    }
}
