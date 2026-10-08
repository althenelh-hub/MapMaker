package WindowFolder;

import Data.Characteristic;
import Data.InfoStorage;
import Data.NamedCharacteristic;
import WindowFolder.ComponentFactories.RemoveIndexStorage;

import java.awt.*;

public class MOCreationLink implements RemoveIndexStorage {
    MOManagePanel panel;
    InfoStorage form;
    public MOCreationLink(MOManagePanel panel, InfoStorage form) {
        this.panel = panel;
        this.form = form;
    }
    public void empty() {
        panel.emptyAll();
        form.emptyForm();
    }
    public void removeField(int index) {
        panel.removeField(index);
        form.removeField(index);
    }
    public void replace(Component component, int index, Characteristic<?> characteristic, int componentIndex) {
        panel.replace(component, index, componentIndex);
        form.changeField(index, characteristic);
    }
    public int getLength() {
        return panel.getLength();
    }
    public void add(MOField field, NamedCharacteristic characteristic) {
        panel.addField(field);
        form.addField(characteristic);
        System.out.println(form);
    }
}
