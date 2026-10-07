package WindowFolder.ComponentFactories;

import java.awt.*;

public interface ComponentFactory<T extends Component> {
    T create();
}
