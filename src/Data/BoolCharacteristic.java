package Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoolCharacteristic extends Characteristic<Boolean> {
    public BoolCharacteristic() {
        value = true;
    }
    public JCheckBox display(boolean changeable) {
        JCheckBox checkBox = new JCheckBox();
        checkBox.addActionListener(new BoolSetter(this, changeable));
        return checkBox;
    }
}
class BoolSetter implements ActionListener {
    Characteristic<Boolean> characteristic;
    final boolean changeable;
    public BoolSetter(Characteristic<Boolean> characteristic, boolean changeable) {
        this.characteristic = characteristic;
        this.changeable = changeable;
    }
    public void actionPerformed(ActionEvent e) {
        if(changeable) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            System.out.println("Set");
            characteristic.setValue(checkBox.isSelected());
            System.out.println(characteristic.value);
        }
        else {

        }
    }
}
