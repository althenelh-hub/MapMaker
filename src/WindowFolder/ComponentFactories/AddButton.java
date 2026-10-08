package WindowFolder.ComponentFactories;

import WindowFolder.AddField;
import WindowFolder.MObjectData;

import javax.swing.*;

public class AddButton extends JButton {
    public AddButton(MObjectData data) {
        addActionListener(new AddField(data));
    }
}
