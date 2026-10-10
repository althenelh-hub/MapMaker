package WindowFolder;

import Data.*;
import Data.Characteristics.Characteristic;
import Data.Characteristics.IntegerCharacteristic;
import Terrain.MapObject;
import WindowFolder.ComponentFactories.RemoveStringStorage;
import WindowFolder.Listeners.RemoveFromString;
import WindowFolder.Listeners.RemoveIndex;
import WindowFolder.Listeners.*;
import javax.swing.*;
import java.awt.*;
import java.util.Set;

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
        //mapObject.shareValues(filler);
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

    }
}





class MObjectFiller extends MapObject implements RemoveStringStorage, InfoModifier {
    public Characteristic<?> getValue(String name) {
        return values.get(name);
    }
    public void emptyFields() {
        values.clear();
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


