package Data;

import WindowFolder.InputChecker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntegerCharacteristic extends Characteristic<Integer> {
    public JTextField display() {
        JTextField textField = new JTextField();
        textField.addActionListener(new IntegerSetter(this));
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
        InputChecker checker = new InputChecker();
        String text = field.getText();
        if(checker.checkOnDigits(text)) {
            characteristic.setValue(Integer.parseInt(text));
        }

    }
}
