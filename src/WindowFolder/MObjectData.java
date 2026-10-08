package WindowFolder;

import Data.Characteristic;
import Data.CharacteristicFactory;
import Data.DisplayItem;
import Data.NamedCharacteristic;
import WindowFolder.ComponentFactories.NamerField;
import WindowFolder.ComponentFactories.RemoveButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MObjectData {
    MOCreationLink link;
    public MObjectData(MOCreationLink link) {
        this.link = link;
    }
    public void add() {
        NamedCharacteristic characteristic = link.form.createField();
        JTextField label = new NamerField(characteristic.receiver);
        JButton button = new RemoveButton(link, link.getLength());
        //label.addActionListener(new TransportCharacteristicName(characteristic.getReceiver()));
        JComboBox<DisplayItem> comboBox = new JComboBox<>();
        comboBox.addItem(new DisplayItem("Integer", DisplayMethod.TextFieldInt));
        comboBox.addItem(new DisplayItem("Bool", DisplayMethod.CheckBox));
        comboBox.addItem(new DisplayItem("String", DisplayMethod.TextFieldString));
        Component[] components = new Component[]{label, characteristic.characteristic.display(), button,comboBox};
        MOField field = new MOField(components);
        comboBox.addActionListener(new ChangeCharacteristicDisplay(link));
        link.add(field, characteristic);
    }

}


class ChangeCharacteristicDisplay implements ActionListener {
    int index;
    MOCreationLink link;
    public ChangeCharacteristicDisplay(MOCreationLink link) {
        this.index = link.getLength();
        this.link = link;
    }
    public void actionPerformed(ActionEvent e) {
        if(index < link.getLength()) {
            JComboBox<DisplayItem> comboBox = (JComboBox<DisplayItem>)e.getSource();
            DisplayItem item = comboBox.getItemAt(comboBox.getSelectedIndex());
            Characteristic<?> characteristic = new CharacteristicFactory().create(item.getDisplayMethod());
            JComponent component = characteristic.display();
            link.replace(component, index, characteristic, 1);
        }

    }
}
