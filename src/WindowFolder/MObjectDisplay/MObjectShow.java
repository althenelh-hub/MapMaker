package WindowFolder.MObjectDisplay;

import Data.Characteristics.Characteristic;
import Data.InfoModifier;
import Terrain.MapObject;
import WindowFolder.ComponentFactories.RemoveStringStorage;
import WindowFolder.Receiver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class MObjectShow extends MObjectData<MObjectFiller> {
    JPanel mainPanel;
    JPanel buttonPanel;
    public MObjectShow() {
        super(new MObjectFiller(), new MOManagePanel());
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(panel);
        buttonPanel = new JPanel();
        mainPanel.add(buttonPanel);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainPanel.setPreferredSize(new Dimension(screenSize.width/15, screenSize.height/15));
        mainPanel.setMaximumSize(new Dimension(300,100));
        mainPanel.setAlignmentY(Component.TOP_ALIGNMENT);
    }
    public void show(MapObject mObject) {
        buttonPanel.removeAll();
        panel.emptyAll();
        mObject.shareValues(modifier);
        for(String s : modifier.getKeySet()) {
            createField(modifier.getValue(s), s, false);
        }
        for(Component c : panel.getComponents()) {
            c.setEnabled(false);
        }
        JButton allowChange = new JButton("Change");
        JButton confirmChange = new JButton("Confirm");
        JButton restoreValues = new JButton("Restore");
        JButton addButton = new JButton("Add");
        addButton.setEnabled(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add(false);
            }
        });
        confirmChange.setEnabled(false);
        restoreValues.setEnabled(false);
        RestoreValues restore = new RestoreValues(this);
        restoreValues.addActionListener(restore);
        restoreValues.addActionListener(new EnableComponent(allowChange, true));
        restoreValues.addActionListener(new EnableFields(panel, false));
        restoreValues.addActionListener(new EnableComponent(confirmChange, false));
        restoreValues.addActionListener(new EnableComponent(restoreValues, false));
        restoreValues.addActionListener(new EnableComponent(addButton, false));

        ChangeableUpdateMapObject transferToRestore = new ChangeableUpdateMapObject(restore, mObject);
        allowChange.addActionListener(transferToRestore);
        allowChange.addActionListener(new EnableFields(panel, true));
        allowChange.addActionListener(new EnableComponent(restoreValues, true));
        allowChange.addActionListener(new EnableComponent(confirmChange, true));
        allowChange.addActionListener(new EnableComponent(allowChange, false));
        allowChange.addActionListener(new EnableComponent(addButton, true));

        UpdateMapObject transferToAllow = new UpdateMapObject(transferToRestore, modifier);
        //confirmChange.addActionListener(transferToAllow);
        confirmChange.addActionListener(new EnableComponent(allowChange, true));
        confirmChange.addActionListener(new EnableComponent(restoreValues, false));
        confirmChange.addActionListener(new EnableFields(panel, false));
        confirmChange.addActionListener(new ConfirmChange(modifier, mObject));
        confirmChange.addActionListener(new EnableComponent(confirmChange, false));
        confirmChange.addActionListener(new EnableComponent(addButton, false));
        buttonPanel.add(allowChange);
        buttonPanel.add(confirmChange);
        buttonPanel.add(restoreValues);
        buttonPanel.add(addButton);
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }

}
class ConfirmChange implements ActionListener {
    MapObject mObject;
    MObjectFiller filler;
    public ConfirmChange(MObjectFiller filler, MapObject mObject){
        this.filler = filler;
        this.mObject = mObject;
    }
    public void actionPerformed(ActionEvent e) {
        System.out.println("Filler: " + filler);
        filler.shareValues(mObject);
    }
}
class ChangeableUpdateMapObject extends UpdateMapObject implements Receiver<MapObject> {
    public ChangeableUpdateMapObject(Receiver<MapObject> receiver, MapObject mObject) {
        super(receiver, mObject);
    }
    public void receive(MapObject mObject) {
        System.out.println("Inside changeable: " + mObject);
        this.mObject = mObject;
    }
}
class EnableFields implements ActionListener {
    MOManagePanel panel;
    boolean value;
    public EnableFields(MOManagePanel panel, boolean value) {
        this.panel = panel;
        this.value = value;
    }
    public void actionPerformed(ActionEvent e) {
        for(Component c : panel.getComponents()) {
            c.setEnabled(value);
        }
    }
}
class EnableComponent implements ActionListener {
    Component component;
    boolean value;
    public EnableComponent(Component component, boolean value) {
        this.component = component;
        this.value = value;
    }
    public void actionPerformed(ActionEvent e) {
        component.setEnabled(value);
    }
}
class UpdateMapObject implements ActionListener {
    Receiver<MapObject> receiver;
    MapObject mObject;
    public UpdateMapObject(Receiver<MapObject> receiver, MapObject mObject) {
        this.receiver = receiver;
        this.mObject = mObject;
    }
    public void actionPerformed(ActionEvent e) {
        receiver.receive(mObject);
    }
}
class RestoreValues implements ActionListener, Receiver<MapObject> {
    MObjectShow panel;
    MapObject oldObject;
    public RestoreValues(MObjectShow panel) {
        this.panel = panel;
    }
    public void receive(MapObject value) {
        oldObject = value;
    }
    public void actionPerformed(ActionEvent e) {
        System.out.println(oldObject);
        panel.show(oldObject);
        panel.mainPanel.revalidate();
        panel.mainPanel.repaint();
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
    public boolean contains(String key) {
        return values.containsKey(key);
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


