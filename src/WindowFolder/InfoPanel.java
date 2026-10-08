package WindowFolder;

import Data.*;
import Terrain.MapObject;
import WindowFolder.ComponentFactories.RemoveStringStorage;
import WindowFolder.Listeners.NameChanger;
import WindowFolder.Listeners.RemoveFromString;

import javax.swing.*;
import java.util.Set;
import java.awt.event.*;
public class InfoPanel extends JPanel {
    MObjectFiller filler;
    MOManagePanel panel = new MOManagePanel();
    public void displayUnitFromMap(MapObject mapObject) {
        panel.emptyAll();
        filler.changeObject(mapObject);
        for(String s : filler.getKeySet()) {
            JTextField field = new JTextField();
            field.setText(s);
            field.addActionListener(new UpdateFieldName(s, filler));
            add(field);
            add(filler.getValue(s).display(mapObject.isModifiable()));
            JButton button = new JButton();
            button.addActionListener(new RemoveFromString(filler, s));
            add(button);
            JComboBox<DisplayItem> comboBox = new JComboBox<>();
            comboBox.addItem(new DisplayItem("Integer", DisplayMethod.TextFieldInt));
            comboBox.addItem(new DisplayItem("Bool", DisplayMethod.CheckBox));
            comboBox.addItem(new DisplayItem("String", DisplayMethod.TextFieldString));
            comboBox.addItemListener(new ChangeView(filler, s, panel, panel.getLength()));
            add(comboBox);
        }
    }
    public void displayTerrain(MapObject mObject) {
        filler.changeObject(mObject);
        panel.emptyAll();
        for(String s : filler.getKeySet()) {
            JTextField field = new JTextField();
            field.setText(s);
            field.addActionListener(new UpdateFieldName(s, filler));
            add(filler.getValue(s).display(mObject.isModifiable()));
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
    MapObject mObject;
    public MObjectFiller(MapObject mObject) {
        this.mObject = mObject;
    }
    public boolean isModifiable() {
        return mObject.isModifiable();
    }
    public void changeObject(MapObject mObject) {
        this.mObject = mObject;
    }
    public Characteristic<?> getValue(String name) {
        return values.get(name);
    }
    public void changeName(String name) {
        this.name = name;
    }
    public void changeCharacteristicName(String oldName, String nextName) {
        Characteristic<?> characteristic = values.get(oldName);
        values.remove(oldName);
        values.put(nextName, characteristic);
    }
    public void remove(String name) {
        values.remove(name);
    }
    public void addField(String name, Characteristic<?> characteristic) {
        values.put(name, characteristic);
    }
    public void changeValue(String name, Characteristic<?> characteristic) {
        values.replace(name, characteristic);
    }
    public boolean containsName(String name) {
        return values.containsKey(name);
    }
    public Set<String> getKeySet() {
        return values.keySet();
    }
}


