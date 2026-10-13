package WindowFolder.MObjectDisplay;

import Data.*;
import Data.Characteristics.Characteristic;
import Data.Characteristics.CharacteristicFactory;
import WindowFolder.ComponentFactories.*;
import WindowFolder.DisplayMethod;
import WindowFolder.Listeners.RemoveFromString;
import WindowFolder.Listeners.*;

import javax.swing.*;
import java.awt.*;

public class MObjectData<T extends InfoModifier> {
    T modifier;
    MOManagePanel panel;
    public MObjectData(T modifier, MOManagePanel panel) {
        this.modifier = modifier;
        this.panel = panel;
    }
    public void add(boolean fieldState) {
        Characteristic<?> characteristic = new CharacteristicFactory().create(DisplayMethod.TextFieldInt);
        createField(characteristic, "", fieldState);
    }
    public void createField(Characteristic<?> characteristic, String characteristicName, boolean fieldState) {
        ConfirmationField label = new ConfirmationFieldFactory().create(characteristicName);
        label.changeState(fieldState);
        ChangeView changeView = new ChangeView(modifier, label.getText(), panel, panel.getLength());
        UpdatableChangeView view = new UpdatableChangeView(changeView);
        RemoveFromString removeString = new RemoveFromString(modifier, label.getText());
        UpdatableRemoveFromString removeFromString = new UpdatableRemoveFromString(removeString);

        label.addActionStrategies(new ActionStrategy[]{new UpdateNameAction(modifier, label.getText(), label),
                new UpdateValueAction(removeFromString, label), new UpdateValueAction(view, label)});
        label.addCheckers(new InputChecker[]{new NameFormatChecker(), new ContainsCheck(modifier)});
        JButton button = new JButtonFactory().create();
        button.addActionListener(removeString);
        button.addActionListener(new RemoveIndex(panel, panel.getLength()));
        JComboBox<DisplayItem> comboBox = new DisplayItemBoxFactory().create();
        comboBox.setSelectedItem(comboBox.getItemAt(characteristic.getItemIndex()));
        Component[] components = new Component[]{label, characteristic.display(), button,comboBox};
        MOField field = new MOField(components);
        comboBox.addItemListener(changeView);
        panel.addField(field);
        modifier.addValue(label.getText(), characteristic);
    }

}

