package WindowFolder.Listeners;

import Data.InfoModifier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateFieldName implements ActionListener {
    NameChanger changer;
    public UpdateFieldName(String oldName, InfoModifier filler) {
        changer = new NameChanger(filler, oldName);
    }
    public void actionPerformed(ActionEvent e) {
        changer.changeName(e);
    }
}
