package WindowFolder.Listeners;

import WindowFolder.Receiver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateValue implements ActionListener {
    Receiver<String> receiver;
    public UpdateValue(Receiver<String> receiver) {
        this.receiver = receiver;
    }
    public void actionPerformed(ActionEvent e) {
        JTextField field = (JTextField)e.getSource();
        receiver.receive(field.getText());
    }
}
