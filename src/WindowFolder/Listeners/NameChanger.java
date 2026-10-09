package WindowFolder.Listeners;

import Data.InfoModifier;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NameChanger {
    InfoModifier modifier;
    String oldName;
    public NameChanger(InfoModifier modifier, String oldName){
        this.modifier = modifier;
        this.oldName = oldName;
    }

    public void changeName(ActionEvent e) {
        JTextField field = (JTextField)e.getSource();
        String nextName = field.getText();
        modifier.changeCharacteristicName(oldName, nextName);
        oldName = nextName;

    }
}
