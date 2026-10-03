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
    DialogManager dialogManager;
    public GameObjectPanel(FactoryManager manager, Painter painter, DialogManager dialogManager, FormManager formManager) {
        this.dialogManager = dialogManager;
        String[] factoryNames = manager.getFactoriesName();
        System.out.println(factoryNames.length);
        for(String s : factoryNames) {
            JPanel panel = new JPanel();
            addTab(s, panel);
        }
        Managers managers = new Managers(manager, formManager, painter, dialogManager, this);
        managers.fillCurrentComponent();
        manager.changeFactory(factoryNames[0]);
        Factory<?> factory = manager.getCurrent();
        addChangeListener(new ChangeFactory(managers));
    }
    public void updateButtons(FactoryManager factoryManager, FormManager formManager, Painter painter) {
        Factory<?> factory = factoryManager.getCurrent();
        if(getTitleAt(getSelectedIndex()).equals(factory.getName())) {

            JComponentFiller filler = new JComponentFiller();
            JComponent component = (JComponent)getComponentAt(0);
            component.removeAll();
            filler.fillButtons(factory, component, painter);
            filler.addButton(dialogManager, this, factoryManager, formManager, painter);
            component.revalidate();
            component.repaint();
        }
    }
    String getCurrentTitle() {
        return getTitleAt(getSelectedIndex());
    }
    JComponent getCurrentPanel() {
        return (JComponent)getComponentAt(getSelectedIndex());
    }
}
class Managers {
    FactoryManager factoryManager;
    FormManager formManager;
    Painter painter;
    DialogManager dialogManager;
    GameObjectPanel panel;
    public Managers(FactoryManager factoryManager, FormManager formManager, Painter painter, DialogManager dialogManager,
                    GameObjectPanel panel) {
        this.factoryManager = factoryManager;
        this.formManager = formManager;
        this.painter = painter;
        this.dialogManager = dialogManager;
        this.panel = panel;
    }
    public void updatePanel() {
        panel.revalidate();
        panel.repaint();
    }
    public void fillCurrentComponent() {
        JComponentFiller filler = new JComponentFiller();
        for(String s : factoryManager.getCurrent().getMObjectNames()) {
            System.out.println(s);
        }
        filler.fillButtons(factoryManager.getFactory(panel.getCurrentTitle()), panel.getCurrentPanel(), painter);
        filler.addButton(dialogManager, panel, factoryManager, formManager, painter);
    }
    public void activateCreation() {
        dialogManager.activateCreation(factoryManager, formManager, painter, panel);
    }
}
class JComponentFiller {
    public void fillButtons(Factory<?> factory, JComponent target, Painter painter) {
        String[] names = factory.getMObjectNames();
        target.removeAll();
        for(String s : names) {
            JButton button = new JButton(s);
            SetMObject setter = new SetMObject(factory, s, painter);
            button.addActionListener(setter);
            target.add(button);
        }
    }
    public void addButton(DialogManager dialogManager, GameObjectPanel gameObjectPanel, FactoryManager
                          factoryManager, FormManager formManager, Painter painter) {
        JButton button = new JButton("Add");
        button.addActionListener(new MObjectCreationAction(dialogManager, gameObjectPanel,factoryManager, formManager,
                painter));
        JComponent component = gameObjectPanel.getCurrentPanel();
        component.add(button);
    }
}
class MObjectCreationAction implements ActionListener {
    DialogManager manager;
    FactoryManager factoryManager;
    FormManager formManager;
    Painter painter;
    GameObjectPanel panel;
    public MObjectCreationAction(DialogManager manager, GameObjectPanel panel, FactoryManager factoryManager,
                                 FormManager formManager, Painter painter) {
        this.manager = manager;
        this.panel = panel;
        this.factoryManager = factoryManager;
        this.formManager = formManager;
        this.painter = painter;
    }
    public void actionPerformed(ActionEvent e) {
         manager.activateCreation(factoryManager, formManager, painter, panel);
    }
}
class ChangeFactory implements ChangeListener {
    Managers manager;
    ChangeFactory(Managers manager) {
        this.manager = manager;

    }
    public void stateChanged(ChangeEvent e) {
        manager.fillCurrentComponent();
        manager.updatePanel();
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


