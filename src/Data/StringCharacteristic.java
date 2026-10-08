package Data;

import WindowFolder.InputChecker;

import javax.swing.*;
import java.awt.event.*;
public class StringCharacteristic extends Characteristic<String> {
    public JTextField display() {
        JTextField field = new JTextField();
        field.addActionListener(new StringSetter(this));
        return field;
    }
}
class StringSetter implements ActionListener {
    Characteristic<String> characteristic;
    public StringSetter(Characteristic<String> characteristic) {
        this.characteristic = characteristic;

    }
    public void actionPerformed(ActionEvent e) {

        JTextField field = (JTextField) e.getSource();
        String text = field.getText();
        InputChecker checker = new InputChecker();
        if (checker.checkPureLetters(text)) {
            characteristic.setValue(text);
        }

    }
}
