package WindowFolder.ComponentFactories;

import Data.InfoModifier;

import javax.swing.*;

public class UpdateNameAction implements ActionStrategy{
    InfoModifier modifier;
    String oldName;
    JTextField field;
    public UpdateNameAction(InfoModifier modifier, String oldName, JTextField field) {
        this.modifier = modifier;
        this.oldName = oldName;
        this.field = field;
    }
    public void realise() {
        modifier.changeCharacteristicName(oldName, field.getText());
    }
}
