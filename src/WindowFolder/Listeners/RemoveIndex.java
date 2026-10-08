package WindowFolder.Listeners;

import WindowFolder.ComponentFactories.RemoveIndexStorage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveIndex implements ActionListener {
    RemoveIndexStorage storage;
    int index;
    public RemoveIndex(RemoveIndexStorage storage, int index) {
        this.storage = storage;
        this.index = index;
    }
    public void actionPerformed(ActionEvent e) {
        storage.removeField(index);
    }
}
