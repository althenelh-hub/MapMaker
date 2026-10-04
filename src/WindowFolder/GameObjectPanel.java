package WindowFolder;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import PaintInstructions.PaintChanger;
import PaintInstructions.Painter;
import Terrain.*;
import Factories.*;
import java.util.*;
public class GameObjectPanel extends JTabbedPane {
    DialogManager dialogManager;
    MObjectChanger changer;
    PaintChanger paintChanger;
    public GameObjectPanel(MObjectChanger changer, PaintChanger paintChanger, DialogManager dialogManager) {
        this.dialogManager = dialogManager;
        this.changer = changer;
        this.paintChanger = paintChanger;
        String[] factoryNames = changer.getFactoryNames();
        for(String s : factoryNames) {
            JPanel panel = new JPanel();
            addTab(s, panel);
            //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        }
        JComponentFiller filler = new JComponentFiller();
        filler.fillButtons(changer, getCurrentTitle(), getCurrentPanel(), paintChanger);
        filler.addButton(this);
        addChangeListener(new DisplayCreationButton(this));
        addChangeListener(new DisplayMObjects(changer, paintChanger));
    }
    public void updateButtons(String createdName) {
        if(getTitleAt(getSelectedIndex()).equals(createdName)) {

            JComponentFiller filler = new JComponentFiller();
            JComponent component = getCurrentPanel();
            component.removeAll();
            filler.fillButtons(changer, getCurrentTitle(),component, paintChanger);
            filler.addButton(this);
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

class JComponentFiller {
    public void fillButtons(MObjectChanger changer, String factoryName, JComponent target, PaintChanger paintChanger) {
        String[] names = changer.getMObjectNames(factoryName);
        target.removeAll();
        for(String s : names) {
            JButton button = new JButton(s);
            SetMObject setter = new SetMObject(changer, factoryName, s, paintChanger);
            button.addActionListener(setter);
            target.add(button);
        }

    }
    public void addButton(GameObjectPanel panel) {
        JButton button = new JButton("Add");
        button.addActionListener(new MObjectCreationAction(panel));
        JComponent component = panel.getCurrentPanel();
        System.out.println("Component: " + component);
        component.add(button);
        for(Component c: component.getComponents()) {
            System.out.println("Button: " + c);
        }
        component.revalidate();
        component.repaint();
    }
}
class DisplayFactionControl implements ChangeListener {
    GameObjectPanel panel;
    String unitsName;
    public DisplayFactionControl(GameObjectPanel panel, String unitsName, Factions factions) {
        this.panel = panel;
        this.unitsName= unitsName;
    }
    public void stateChanged(ChangeEvent e) {
        if(panel.getCurrentTitle().equals(unitsName)) {

        }
    }
}
class MObjectCreationAction implements ActionListener {
    GameObjectPanel panel;
    public MObjectCreationAction(GameObjectPanel panel) {
        this.panel = panel;
    }
    public void actionPerformed(ActionEvent e) {
         panel.dialogManager.activateCreation(panel);
    }
}
class DisplayCreationButton implements ChangeListener {
    GameObjectPanel panel;
    public DisplayCreationButton(GameObjectPanel panel) {
        this.panel = panel;
    }
    public void stateChanged(ChangeEvent e) {
        JComponentFiller filler = new JComponentFiller();
        filler.addButton(panel);
    }
}
class DisplayMObjects implements ChangeListener {
    MObjectChanger changer;
    PaintChanger painter;
    public DisplayMObjects(MObjectChanger changer, PaintChanger painter) {
        this.changer = changer;
        this.painter = painter;
    }
    public void stateChanged(ChangeEvent e) {
        JTabbedPane pane = (JTabbedPane)e.getSource();
        String title = pane.getTitleAt(pane.getSelectedIndex());
        JComponent panel = (JComponent) pane.getComponentAt(pane.getSelectedIndex());
        JComponentFiller filler = new JComponentFiller();
        filler.fillButtons(changer, title, panel, painter);
    }
}
class SetMObject implements ActionListener {
    MObjectChanger changer;
    String factoryName;
    String MObjectName;
    PaintChanger paintChanger;
    public SetMObject(MObjectChanger changer, String factoryName, String MObjectName, PaintChanger paintChanger) {
        this.changer = changer;
        this.factoryName = factoryName;
        this.MObjectName = MObjectName;
        this.paintChanger = paintChanger;
    }
    public void actionPerformed(ActionEvent e) {
        changer.changeFactory(factoryName, MObjectName);
        paintChanger.setCurrentForm(MObjectName);
    }
}


