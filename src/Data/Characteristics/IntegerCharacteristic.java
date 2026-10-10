package Data.Characteristics;

import WindowFolder.ComponentFactories.ConfirmationField;
import WindowFolder.FormatChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntegerCharacteristic extends Characteristic<Integer> {
    public IntegerCharacteristic() {
        super(0);
        value = 0;
    }
    public JTextField display() {
        JTextField textField = new ConfirmationField(Color.WHITE, Color.ORANGE);
        textField.addActionListener(new IntegerSetter(this));
        textField.setText(value+"");
        return textField;
    }

}
class IntegerSetter implements ActionListener {
    Characteristic<Integer> characteristic;
    public IntegerSetter(Characteristic<Integer> characteristic) {
        this.characteristic = characteristic;
    }
    public void actionPerformed(ActionEvent e) {
        JTextField field = (JTextField)e.getSource();
        FormatChecker checker = new FormatChecker();
        String text = field.getText();
        if(checker.checkOnDigits(text)) {
            characteristic.setValue(Integer.parseInt(text));
        }

    }
}
