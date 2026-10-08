package WindowFolder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddField implements ActionListener {
    MObjectData data;
    public AddField(MObjectData data) {
        this.data = data;
    }
    public void actionPerformed(ActionEvent e) {
        data.add();

    }
}
