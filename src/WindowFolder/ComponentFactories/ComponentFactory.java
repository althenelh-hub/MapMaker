package WindowFolder.ComponentFactories;

import javax.swing.*;

public interface ComponentFactory<T extends JComponent> {
    T create(String name);
}
