package WindowFolder.ComponentFactories;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveButton extends JButton {
    public RemoveButton(RemoveStorage storage, int index) {
        addActionListener(new Remove(storage, index));
    }
}
class Remove implements ActionListener {
    RemoveStorage storage;
    int index;
    public Remove(RemoveStorage storage, int index) {
        this.storage = storage;
        this.index = index;
    }
    public void actionPerformed(ActionEvent e) {
        storage.remove(index);
    }
}