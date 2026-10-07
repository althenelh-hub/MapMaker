package WindowFolder.ComponentFactories;

import Data.DisplayItem;

import javax.swing.*;

public class DisplayComboFactory implements ComponentFactory<JComboBox<DisplayItem>>{
    @Override
    public JComboBox<DisplayItem> create() {
        return new JComboBox<>();
    }
}
