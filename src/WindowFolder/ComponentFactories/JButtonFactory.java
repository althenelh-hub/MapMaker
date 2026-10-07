package WindowFolder.ComponentFactories;

import javax.swing.*;

public class JButtonFactory implements ComponentFactory<JButton>{
    @Override
    public JButton create() {
        return new JButton();
    }
}
