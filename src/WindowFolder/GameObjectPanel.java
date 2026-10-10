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
    Keeper<String> factionLink;
    MObjectDisplayControl control;
    public GameObjectPanel(MObjectChanger changer, PaintChanger paintChanger, DialogManager dialogManager,
                           Keeper<String> factionLink, MObjectDisplayControl control) {
        this.dialogManager = dialogManager;
        this.changer = changer;
        this.paintChanger = paintChanger;
        this.factionLink = factionLink;
        this.control = control;
        String[] factoryNames = changer.getFactoryNames();
        for(String s : factoryNames) {
            JPanel panel = new JPanel();
            addTab(s, panel);
            //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        }
        JComponentFiller filler = new JComponentFiller();
        filler.fillButtons(changer, getCurrentTitle(), getCurrentPanel(), paintChanger, factionLink, control);
        filler.addButton(this);
        addChangeListener(new DisplayCreationButton(this));
        addChangeListener(new DisplayMObjects(changer, paintChanger, factionLink, control));
    }
    public void updateButtons(String createdName) {
        if(getTitleAt(getSelectedIndex()).equals(createdName)) {

            JComponentFiller filler = new JComponentFiller();
            JComponent component = getCurrentPanel();
            component.removeAll();
            filler.fillButtons(changer, getCurrentTitle(),component, paintChanger, factionLink, control);
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
    public void fillButtons(MObjectChanger changer, String factoryName, JComponent target, PaintChanger paintChanger,
                            Keeper<String> factionName, MObjectDisplayControl mObjectShow) {
        String[] names = changer.getMObjectNames(factoryName);
        target.removeAll();
        for(String s : names) {
            JButton button = new JButton(s);
            SetMObject setter = new SetMObject(changer, factoryName, s, paintChanger, mObjectShow);
            button.addActionListener(setter);
            target.add(button);
        }
        if(factoryName.equals("Unit")) {
            target.add(new FactionControl(factionName));
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
class FactionControl extends JPanel {
    JCheckBox[] factions;
    int currentFaction;
    Keeper<String> faction;
    public FactionControl(Keeper<String> factionLink) {
        faction = factionLink;
        JLabel first = new JLabel("Ally");
        JLabel second = new JLabel("Enemy");
        factions = new JCheckBox[]{new JCheckBox("Ally", false), new JCheckBox("Enemy", true)};
        currentFaction = 1;
        for(int i = 0;i < factions.length;i++) {
            factions[i].addActionListener(new ChangeFaction(i));
        }
        add(first);
        add(factions[0]);
        add(second);
        add(factions[1]);
    }
    public void changeFaction(int index) {
        factions[currentFaction].setSelected(false);
        factions[index].setSelected(true);
        faction.setValue(factions[index].getText());
        currentFaction = index;
    }
    class ChangeFaction implements ActionListener {
        int index;
        public ChangeFaction(int index) {
            this.index = index;
        }
        public void actionPerformed(ActionEvent e) {
            changeFaction(index);
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
    Keeper<String> factionLink;
    MObjectDisplayControl mObjectShow;
    public DisplayMObjects(MObjectChanger changer, PaintChanger painter, Keeper<String> factionLink,
                           MObjectDisplayControl mObjectShow) {
        this.changer = changer;
        this.painter = painter;
        this.factionLink = factionLink;
        this.mObjectShow = mObjectShow;
    }
    public void stateChanged(ChangeEvent e) {
        JTabbedPane pane = (JTabbedPane)e.getSource();
        String title = pane.getTitleAt(pane.getSelectedIndex());
        JComponent panel = (JComponent) pane.getComponentAt(pane.getSelectedIndex());
        JComponentFiller filler = new JComponentFiller();
        filler.fillButtons(changer, title, panel, painter, factionLink, mObjectShow);
    }
}
class SetMObject implements ActionListener {
    MObjectChanger changer;
    String factoryName;
    String MObjectName;
    PaintChanger paintChanger;
    MObjectDisplayControl mObjectShow;
    public SetMObject(MObjectChanger changer, String factoryName, String MObjectName, PaintChanger paintChanger,
                      MObjectDisplayControl mObjectShow) {
        this.changer = changer;
        this.factoryName = factoryName;
        this.MObjectName = MObjectName;
        this.paintChanger = paintChanger;
        this.mObjectShow = mObjectShow;
    }
    public void actionPerformed(ActionEvent e) {
        changer.changeFactory(factoryName, MObjectName);
        mObjectShow.displayMObjects(new MapObject[]{changer.getCurrentMapObject()});
        paintChanger.setCurrentForm(MObjectName);
        mObjectShow.revalidate();
        mObjectShow.repaint();
    }
}


