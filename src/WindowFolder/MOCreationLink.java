package WindowFolder;

import Data.Characteristics.Characteristic;
import Data.InfoModifier;
import WindowFolder.MObjectDisplay.MOField;
import WindowFolder.MObjectDisplay.MOManagePanel;

import java.awt.*;

public class MOCreationLink {
    MOManagePanel panel;
    InfoModifier form;
    public MOCreationLink(MOManagePanel panel, InfoModifier form) {
        this.panel = panel;
        this.form = form;
    }
    public void empty() {
        panel.emptyAll();
        form.emptyFields();
    }
    public void removeField(int index, String name) {
        panel.removeField(index);
        form.removeField(name);
    }
    public void replace(Component component, int index, Characteristic<?> characteristic, int componentIndex, String name) {
        panel.replace(component, index, componentIndex);
        form.changeValue(name, characteristic);
    }
    public int getLength() {
        return panel.getLength();
    }
    public void add(MOField field, Characteristic<?> characteristic, String name) {
        panel.addField(field);
        form.addValue(name, characteristic);
        System.out.println(form);
    }
}
