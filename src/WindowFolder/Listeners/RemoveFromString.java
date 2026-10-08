package WindowFolder.Listeners;

import WindowFolder.ComponentFactories.RemoveStringStorage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveFromString implements ActionListener {
    RemoveStringStorage storage;
    String name;
    public RemoveFromString(RemoveStringStorage storage, String name) {
        this.storage = storage;
        this.name = name;
    }
    public void actionPerformed(ActionEvent e) {
        storage.remove(name);
    }
}
