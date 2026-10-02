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

    public GameObjectPanel(MOCreation creation) {
        JPanel panel = new JPanel();
        MObjectContainer tileContainer = new MObjectContainer();
        addChangeListener(new changeFactory());
    }
    public void test() {

    }
}
class changeFactory implements ChangeListener {
    FactoryManager manager;
    String name;
    public void stateChanged(ChangeEvent e) {
        manager.changeFactory(name);
    }
}
class MObjectContainer extends JPanel {
    ArrayList<String> names;
    public MObjectContainer() {
        names = new ArrayList<>();
    }
    public void showButtons(CoordinateListener listener) {
        for(String s : names) {
            JButton button = new JButton(s);
            //button.addActionListener(new MObjectSetter(listener, s));
            add(button);
        }
    }
}
class MObjectSetter implements ActionListener {
    Factory<?> factory;
    String name;
    public MObjectSetter(Factory<?> factory, String name) {
        this.factory = factory;
        this.name = name;
    }
    public void actionPerformed(ActionEvent e) {
        factory.change(name);
    }
}
