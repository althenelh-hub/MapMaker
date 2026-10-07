package WindowFolder.ComponentFactories;

import javax.swing.*;

public class JCheckBoxFactory implements ComponentFactory<JCheckBox> {
    @Override
    public JCheckBox create() {
        return new JCheckBox();
    }
}
