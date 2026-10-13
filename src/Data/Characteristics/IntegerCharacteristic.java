package Data.Characteristics;

import WindowFolder.ComponentFactories.ActionStrategy;
import WindowFolder.ComponentFactories.ConfirmationField;

import javax.swing.*;
import java.awt.*;

public class IntegerCharacteristic extends Characteristic<Integer> {
    public IntegerCharacteristic() {
        this(0);
    }
    public IntegerCharacteristic(int value) {
        super(0);
        this.value = value;
    }
    public JTextField display() {
        ConfirmationField textField = new ConfirmationField(Color.WHITE, Color.ORANGE, value+"");
        //textField.changeState(true);
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
