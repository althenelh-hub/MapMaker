package WindowFolder;

import Data.InfoModifier;
import WindowFolder.MObjectDisplay.MObjectData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddField implements ActionListener {
    MObjectData<InfoModifier> data;
    public AddField(MObjectData<InfoModifier> data) {
        this.data = data;
    }
    public void actionPerformed(ActionEvent e) {
        data.add(true);

    }
}
