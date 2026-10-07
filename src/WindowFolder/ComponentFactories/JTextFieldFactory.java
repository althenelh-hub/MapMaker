package WindowFolder.ComponentFactories;

import javax.swing.*;

public class JTextFieldFactory implements ComponentFactory<JTextField>{
    @Override
    public JTextField create() {
        return new JTextField();
    }
}
