package WindowFolder.ComponentFactories;

import Data.DisplayItem;
import WindowFolder.DisplayMethod;

import javax.swing.*;

public class DisplayItemBoxFactory {

    public JComboBox<DisplayItem> create() {
        JComboBox<DisplayItem> comboBox = new JComboBox<>();
        comboBox.addItem(new DisplayItem("Integer", DisplayMethod.TextFieldInt));
        comboBox.addItem(new DisplayItem("Bool", DisplayMethod.CheckBox));
        comboBox.addItem(new DisplayItem("String", DisplayMethod.TextFieldString));
        return comboBox;
    }
}
