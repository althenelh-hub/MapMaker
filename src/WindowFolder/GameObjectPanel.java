package WindowFolder;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Listeners.CoordinateListener;
import Terrain.*;
import Factories.*;
import java.util.*;
public class GameObjectPanel extends JTabbedPane {

    public GameObjectPanel() {

    }
}
class changeFactory implements ChangeListener {
    FactoryManager manager;
    String name;
    public void stateChanged(ChangeEvent e) {
        manager.changeFactory(name);
    }
}


