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
    public GameObjectPanel(FactoryManager manager, Painter painter, DialogManager dialogManager) {
        this.dialogManager = dialogManager;
        String[] factoryNames = manager.getFactoriesName();
        for(String s : factoryNames) {
            JPanel panel = new JPanel();
            addTab(s, panel);
        }
        Managers managers = new Managers(manager, painter, dialogManager, this);
        managers.fillCurrentComponent();
        addChangeListener(new ChangeFactory(managers));
    }
    public void updateButtons(FactoryManager factoryManager, Painter painter) {
        Factory<?> factory = factoryManager.getFactory(getCurrentTitle());
        if(getTitleAt(getSelectedIndex()).equals(factory.getName())) {

            JComponentFiller filler = new JComponentFiller();
            JComponent component = getCurrentPanel();
            component.removeAll();
            filler.fillButtons(factoryManager, getCurrentTitle(),component, painter);
            filler.addButton(dialogManager, this, factoryManager, painter);
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
    Painter painter;
    DialogManager dialogManager;
    GameObjectPanel panel;
    public Managers(FactoryManager factoryManager, Painter painter, DialogManager dialogManager,
                    GameObjectPanel panel) {
        this.factoryManager = factoryManager;

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
        filler.fillButtons(factoryManager, panel.getCurrentTitle(),panel.getCurrentPanel(), painter);
        filler.addButton(dialogManager, panel, factoryManager, painter);
    }
    public void activateCreation() {
        dialogManager.activateCreation(factoryManager, painter, panel);
    }
}
class JComponentFiller {
    public void fillButtons(FactoryManager factoryManager, String factoryName, JComponent target, Painter painter) {
        String[] names = factoryManager.getFactory(factoryName).getMObjectNames();
        target.removeAll();
        for(String s : names) {
            JButton button = new JButton(s);
            SetMObject setter = new SetMObject(factoryManager, factoryName, s, painter);
            button.addActionListener(setter);
            target.add(button);
        }
    }
    public void addButton(DialogManager dialogManager, GameObjectPanel gameObjectPanel, FactoryManager
                          factoryManager, Painter painter) {
        JButton button = new JButton("Add");
        button.addActionListener(new MObjectCreationAction(dialogManager, gameObjectPanel,factoryManager,
                painter));
        JComponent component = gameObjectPanel.getCurrentPanel();
        component.add(button);
    }
}
class MObjectCreationAction implements ActionListener {
    DialogManager manager;
    FactoryManager factoryManager;
    Painter painter;
    GameObjectPanel panel;
    public MObjectCreationAction(DialogManager manager, GameObjectPanel panel, FactoryManager factoryManager,
                                 Painter painter) {
        this.manager = manager;
        this.panel = panel;
        this.factoryManager = factoryManager;
        this.painter = painter;
    }
    public void actionPerformed(ActionEvent e) {
         manager.activateCreation(factoryManager, painter, panel);
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
    FactoryManager factoryManager;
    String factoryName;
    String MObjectName;
    Painter painter;
    public SetMObject(FactoryManager factoryManager, String factoryName, String MObjectName, Painter painter) {
        this.factoryManager = factoryManager;
        this.factoryName = factoryName;
        this.MObjectName = MObjectName;
        this.painter = painter;
    }
    public void actionPerformed(ActionEvent e) {
        factoryManager.changeFactory(factoryName);
        Factory<?> factory = factoryManager.getFactory(factoryName);
        factory.change(MObjectName);
        painter.setCurrentForm(MObjectName);
    }
}


