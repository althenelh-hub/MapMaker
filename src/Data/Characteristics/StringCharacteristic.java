package Data.Characteristics;

import WindowFolder.ComponentFactories.ConfirmationField;
import WindowFolder.FormatChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class StringCharacteristic extends Characteristic<String> {
    public StringCharacteristic() {
        super(2);
        value = "";
    }
    public JTextField display() {
        JTextField field = new ConfirmationField(Color.WHITE, Color.ORANGE);
        field.addActionListener(new StringSetter(this));
        field.setText(value);
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
        FormatChecker checker = new FormatChecker();
        if (checker.checkPureLetters(text)) {
            characteristic.setValue(text);
        }

    }
}
