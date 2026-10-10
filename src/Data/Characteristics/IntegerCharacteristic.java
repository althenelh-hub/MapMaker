package Data.Characteristics;

import WindowFolder.ComponentFactories.ActionStrategy;
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
        ConfirmationField textField = new ConfirmationField(Color.WHITE, Color.ORANGE);
        textField.addActionStrategy(new IntegerSetter(this, textField));
        textField.setText(value+"");
        return textField;
    }

}
class IntegerSetter implements ActionStrategy {
    Characteristic<Integer> characteristic;
    JTextField field;
    public IntegerSetter(Characteristic<Integer> characteristic, JTextField field) {
        this.field = field;
        this.characteristic = characteristic;
    }
    public void realise() {
        characteristic.setValue(Integer.parseInt(field.getText()));
    }
}
