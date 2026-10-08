package Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoolCharacteristic extends Characteristic<Boolean> {
    public BoolCharacteristic() {
        value = true;
    }
    public JCheckBox display() {
        JCheckBox checkBox = new JCheckBox();
        checkBox.addActionListener(new BoolSetter(this));
        return checkBox;
    }
}
class BoolSetter implements ActionListener {
    Characteristic<Boolean> characteristic;

    public BoolSetter(Characteristic<Boolean> characteristic) {
        this.characteristic = characteristic;
    }
    public void actionPerformed(ActionEvent e) {
        JCheckBox checkBox = (JCheckBox) e.getSource();
        System.out.println("Set");
        characteristic.setValue(checkBox.isSelected());
        System.out.println(characteristic.value);

    }
}
