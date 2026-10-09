package WindowFolder;

import Data.*;
import Terrain.MapObject;
import WindowFolder.ComponentFactories.RemoveStringStorage;
import WindowFolder.Listeners.NameChanger;
import WindowFolder.Listeners.RemoveFromString;
import WindowFolder.Listeners.RemoveIndex;

import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.awt.event.*;
public class InfoPanel extends JPanel {
    MObjectFiller filler = new MObjectFiller();
    MOManagePanel panel = new MOManagePanel();
    public InfoPanel() {
        add(panel);
        MapObject mObject = new MapObject();
        IntegerCharacteristic characteristic = new IntegerCharacteristic();
        characteristic.setValue(25);
        mObject.addValue("First", characteristic);
        displayUnitFromMap(mObject);
    }
    public void displayUnitFromMap(MapObject mapObject) {
        panel.emptyAll();
        mapObject.shareValues(filler);
        for(String s : filler.getKeySet()) {
            JTextField field = new JTextField(s);
            field.addActionListener(new UpdateFieldName(s, filler));
            field.setEnabled(false);
            Characteristic<?> characteristic = filler.getValue(s);
            Component characField = characteristic.display();
            characField.setEnabled(false);
            JButton button = new JButton();
            button.addActionListener(new RemoveFromString(filler, s));
            button.addActionListener(new RemoveIndex(panel, panel.getLength()));
            button.setEnabled(false);
            JComboBox<DisplayItem> comboBox = new JComboBox<>();
            comboBox.addItemListener(new ChangeView(filler, s, panel, panel.getLength()));
            comboBox.setEnabled(false);
            Component[] components = new Component[]{field, characField, button, comboBox};
            MOField mObjectField = new MOField(components);
            panel.addField(mObjectField);
        }
        JButton allowChange = new JButton();
        allowChange.addActionListener(new AllowChange(panel));
        JButton confirmChange = new JButton();
        JButton restoreValues = new JButton();
        confirmChange.addActionListener(new ConfirmChange(panel, restoreValues));
        restoreValues.addActionListener(new RestoreValues(this, mapObject));
        add(allowChange);
        add(confirmChange);
        add(restoreValues);
    }
}
class ConfirmChange implements ActionListener {
    MOManagePanel panel;
    Component restoreValues;
    public ConfirmChange(MOManagePanel panel, Component restoreValues) {
        this.panel = panel;
        this.restoreValues = restoreValues;
    }
    public void actionPerformed(ActionEvent e) {
        Component[] components = panel.getComponents();
        for(Component c : components) {
            c.setEnabled(false);
        }
        restoreValues.setEnabled(false);
    }
}
class RestoreValues implements ActionListener {
    InfoPanel panel;
    MapObject oldObject;
    public RestoreValues(InfoPanel panel, MapObject oldObject) {
        this.panel = panel;
        this.oldObject = oldObject;
    }
    public void actionPerformed(ActionEvent e) {
        panel.displayUnitFromMap(oldObject);
        oldObject.shareValues(panel.filler);
    }
}
class AllowChange implements ActionListener {
    MOManagePanel panel;
    public AllowChange(MOManagePanel panel) {
        this.panel = panel;
    }
    public void actionPerformed(ActionEvent e) {
        Component[] components = panel.getComponents();
        for(Component c : components) {
            c.setEnabled(true);
        }
    }
}
class ChangeView implements ItemListener {
    MObjectFiller filler;
    String valueName;
    MOManagePanel panel;
    int index;
    public ChangeView(MObjectFiller filler, String valueName, MOManagePanel panel, int index) {
        this.filler = filler;
        this.valueName = valueName;
        this.panel = panel;
        this.index = index;
    }
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
            JComboBox<DisplayItem> source = (JComboBox<DisplayItem>)e.getSource();
            DisplayItem item = source.getItemAt(source.getSelectedIndex());
            Characteristic<?> characteristic = new CharacteristicFactory().create(item.getDisplayMethod());
            filler.changeValue(valueName, characteristic);
            panel.replace(characteristic.display(), index, 1);
        }
    }
}

class UpdateFieldName implements ActionListener {
    NameChanger changer;
    public UpdateFieldName(String oldName, MObjectFiller filler) {
        changer = new NameChanger(filler, oldName);
    }
    public void actionPerformed(ActionEvent e) {
        changer.changeName(e);
    }
}
class MObjectFiller extends MapObject implements RemoveStringStorage, InfoModifier {
    public Characteristic<?> getValue(String name) {
        return values.get(name);
    }
    public void changeCharacteristicName(String oldName, String nextName) {
        Characteristic<?> characteristic = values.get(oldName);
        values.remove(oldName);
        values.put(nextName, characteristic);
    }
    public void removeField(String name) {
        values.remove(name);
    }
    public void changeValue(String name, Characteristic<?> characteristic) {
        values.replace(name, characteristic);
    }
    public Set<String> getKeySet() {
        return values.keySet();
    }
}


