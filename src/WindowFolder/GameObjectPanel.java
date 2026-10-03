package WindowFolder;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import PaintInstructions.Painter;
import Terrain.*;
import Factories.*;
import java.util.*;
public class GameObjectPanel extends JTabbedPane {

    public GameObjectPanel(FactoryManager manager, Painter painter) {
        String[] factoryNames = manager.getFactoriesName();
        System.out.println(factoryNames.length);
        for(String s : factoryNames) {
            JPanel panel = new JPanel();
            addTab(s, panel);
        }
        System.out.println("Tab count: " + getTabCount());
        System.out.println("Selected" + getSelectedIndex());
        System.out.println("Component" + getComponentAt(0));
        manager.changeFactory(factoryNames[0]);
        Factory<?> factory = manager.getCurrent();
        JComponentFiller filler = new JComponentFiller();
        filler.fillButtons(factory, (JComponent)getComponentAt(0), painter);
        addChangeListener(new ChangeFactory(manager, painter));
    }
    public void updateButtons(Factory<?> factory, Painter painter) {
        if(getTitleAt(getSelectedIndex()).equals(factory.getName())) {
            JComponentFiller filler = new JComponentFiller();
            JComponent component = (JComponent)getComponentAt(0);
            component.removeAll();
            filler.fillButtons(factory, component, painter);
            component.revalidate();
            component.repaint();
        }
    }
}
class JComponentFiller {
    public void fillButtons(Factory<?> factory, JComponent target, Painter painter) {
        String[] names = factory.getMObjectNames();
        for(String s : names) {
            JButton button = new JButton(s);
            SetMObject setter = new SetMObject(factory, s, painter);
            button.addActionListener(setter);
            target.add(button);
        }
        JButton button = new JButton("Add");
    }
}
class MObjectCreationAction implements ActionListener {
    FactoryManager factoryManager;
    FormManager formManager;
    Painter painter;
    GameObjectPanel panel;
    public void actionPerformed(ActionEvent e) {
         new MOCreation(factoryManager, formManager, painter, panel);
    }
}
class ChangeFactory implements ChangeListener {
    FactoryManager manager;
    Painter painter;
    ChangeFactory(FactoryManager manager, Painter painter) {
        this.manager = manager;
        this.painter = painter;
    }
    public void stateChanged(ChangeEvent e) {
        JTabbedPane pane = (JTabbedPane)e.getSource();
        JComponent component = (JComponent)pane.getComponentAt(pane.getSelectedIndex());
        String title = pane.getTitleAt(pane.getSelectedIndex());
        Factory<?> current = manager.getCurrent();
        manager.changeFactory(title);
        Factory<?> factory = manager.getCurrent();
        JComponentFiller filler = new JComponentFiller();
        filler.fillButtons(factory, component, painter);
    }
}
class SetMObject implements ActionListener {
    Factory<?> factory;
    String name;
    Painter painter;
    public SetMObject(Factory<?> factory, String name, Painter painter) {
        this.factory = factory;
        this.name = name;
        this.painter = painter;
    }
    public void actionPerformed(ActionEvent e) {
        factory.change(name);
        painter.setCurrentForm(name);
    }
}


