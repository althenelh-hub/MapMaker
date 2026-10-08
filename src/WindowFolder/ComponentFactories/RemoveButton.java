package WindowFolder.ComponentFactories;
import WindowFolder.Listeners.RemoveIndex;

import javax.swing.*;

public class RemoveButton extends JButton {
    public RemoveButton(RemoveIndexStorage storage, int index) {
        addActionListener(new RemoveIndex(storage, index));
    }
}
