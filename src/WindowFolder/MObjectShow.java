package WindowFolder;

import Terrain.MapObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MObjectShow extends MObjectData<MObjectFiller> {
    JPanel mainPanel;
    JPanel buttonPanel;
    public MObjectShow() {
        super(new MObjectFiller(), new MOManagePanel());
        mainPanel = new JPanel();
        mainPanel.add(panel);
        buttonPanel = new JPanel();
        mainPanel.add(buttonPanel);
    }
    public void show(MapObject mObject) {
        buttonPanel.removeAll();
        panel.emptyAll();
        mObject.shareValues(modifier);
        for(String s : modifier.getKeySet()) {
            createField(modifier.getValue(s), s);
        }
        for(Component c : panel.getComponents()) {
            c.setEnabled(false);
        }
        JButton allowChange = new JButton("Change");
        JButton confirmChange = new JButton("Confirm");
        JButton restoreValues = new JButton("Restore");
        confirmChange.setEnabled(false);
        restoreValues.setEnabled(false);
        RestoreValues restore = new RestoreValues(this);
        restoreValues.addActionListener(restore);
        restoreValues.addActionListener(new EnableComponent(allowChange, true));
        restoreValues.addActionListener(new EnableFields(panel, false));
        restoreValues.addActionListener(new EnableComponent(confirmChange, false));
        restoreValues.addActionListener(new EnableComponent(restoreValues, false));

        ChangeableUpdateMapObject transferToRestore = new ChangeableUpdateMapObject(restore, mObject);
        allowChange.addActionListener(transferToRestore);
        allowChange.addActionListener(new EnableFields(panel, true));
        allowChange.addActionListener(new EnableComponent(restoreValues, true));
        allowChange.addActionListener(new EnableComponent(confirmChange, true));
        allowChange.addActionListener(new EnableComponent(allowChange, false));

        UpdateMapObject transferToAllow = new UpdateMapObject(transferToRestore, modifier);
        confirmChange.addActionListener(transferToAllow);
        confirmChange.addActionListener(new EnableComponent(allowChange, true));
        confirmChange.addActionListener(new EnableComponent(restoreValues, false));
        confirmChange.addActionListener(new EnableFields(panel, false));
        confirmChange.addActionListener(new ConfirmChange(modifier, mObject));
        confirmChange.addActionListener(new EnableComponent(confirmChange, false));
        buttonPanel.add(allowChange);
        buttonPanel.add(confirmChange);
        buttonPanel.add(restoreValues);
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
        filler.shareValues(mObject);
    }
}
class ChangeableUpdateMapObject extends UpdateMapObject implements Receiver<MapObject>{
    public ChangeableUpdateMapObject(Receiver<MapObject> receiver, MapObject mObject) {
        super(receiver, mObject);
    }
    public void receive(MapObject mObject) {
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
        panel.show(oldObject);
    }
}


