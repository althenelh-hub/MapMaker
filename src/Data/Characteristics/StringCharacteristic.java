package Data.Characteristics;

import WindowFolder.ComponentFactories.ActionStrategy;
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
        ConfirmationField field = new ConfirmationField(Color.WHITE, Color.ORANGE,value);
        field.changeState(false);
        field.addActionStrategy(new StringSetter(this, field));
        field.setText(value);
        return field;
    }
}
class StringSetter implements ActionStrategy {
    Characteristic<String> characteristic;
    JTextField field;
    public StringSetter(Characteristic<String> characteristic, JTextField field) {
        this.field = field;
        this.characteristic = characteristic;

    }
    public void realise() {
        characteristic.setValue(field.getText());
    }
}
