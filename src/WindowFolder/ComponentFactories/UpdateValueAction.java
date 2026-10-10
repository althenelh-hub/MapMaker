package WindowFolder.ComponentFactories;

import WindowFolder.Receiver;

import javax.swing.*;

public class UpdateValueAction implements ActionStrategy {
    Receiver<String> receiver;
    JTextField field;
    public UpdateValueAction(Receiver<String> receiver, JTextField field) {
        this.receiver = receiver;
        this.field = field;
    }
    public void realise() {
        receiver.receive(field.getText());
    }
}
