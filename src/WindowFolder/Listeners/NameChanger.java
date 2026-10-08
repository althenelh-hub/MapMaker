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
        if(modifier.isModifiable()) {
            JTextField field = new JTextField();
            String nextName = field.getText();
            modifier.changeCharacteristicName(oldName, nextName);
            oldName = nextName;
        }
        else {

        }
    }
}
