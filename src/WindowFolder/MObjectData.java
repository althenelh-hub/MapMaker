package WindowFolder;

import Data.*;
import Data.Characteristics.Characteristic;
import Data.Characteristics.CharacteristicFactory;
import WindowFolder.ComponentFactories.*;
import WindowFolder.Listeners.RemoveFromString;
import WindowFolder.Listeners.*;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;

public class MObjectData<T extends InfoModifier> {
    T modifier;
    MOManagePanel panel;
    public MObjectData(T modifier, MOManagePanel panel) {
        this.modifier = modifier;
        this.panel = panel;
    }
    public void add() {
        Characteristic<?> characteristic = new CharacteristicFactory().create(DisplayMethod.TextFieldInt);
        System.out.println(characteristic);
        createField(characteristic, "");
    }
    public void createField(Characteristic<?> characteristic, String characteristicName) {
        ConfirmationField label = new ConfirmationField(Color.WHITE, Color.ORANGE, characteristicName);
        label.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {

            }
        });
        ChangeView changeView = new ChangeView(modifier, label.getText(), panel, panel.getLength());
        UpdatableChangeView view = new UpdatableChangeView(changeView);
        RemoveFromString removeString = new RemoveFromString(modifier, label.getText());
        UpdatableRemoveFromString removeFromString = new UpdatableRemoveFromString(removeString);

        label.addActionStrategies(new ActionStrategy[]{new UpdateNameAction(modifier, label.getText(), label),
                new UpdateValueAction(removeFromString, label), new UpdateValueAction(view, label)});
        label.addCheckers(new InputChecker[]{new NameFormatChecker(), new ContainsCheck(modifier)});
        JButton button = new JButton();
        button.addActionListener(removeString);
        button.addActionListener(new RemoveIndex(panel, panel.getLength()));
        JComboBox<DisplayItem> comboBox = new JComboBox<>();
        comboBox.addItem(new DisplayItem("Integer", DisplayMethod.TextFieldInt));
        comboBox.addItem(new DisplayItem("Bool", DisplayMethod.CheckBox));
        comboBox.addItem(new DisplayItem("String", DisplayMethod.TextFieldString));
        comboBox.setSelectedItem(comboBox.getItemAt(characteristic.getItemIndex()));
        Component[] components = new Component[]{label, characteristic.display(), button,comboBox};
        MOField field = new MOField(components);
        comboBox.addItemListener(changeView);
        panel.addField(field);
        modifier.addValue(label.getText(), characteristic);
    }

}

