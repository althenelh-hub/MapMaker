package WindowFolder;
import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.colorchooser.DefaultColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import Data.*;
import Factories.MObjectAdder;
import Terrain.*;
import PaintInstructions.*;
import java.util.*;

public class MOCreation extends JDialog {

    public MOCreation(MObjectAdder mObjectAdder, PaintAdder paintAdder, GameObjectPanel pane,
                      FormFactoryManager formFactoryManager, String currentFactoryName) {
        //Dialog settings
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(1000,800));
        setLayout(new BorderLayout());
        //Dialog settings
        //Name field and finishAction
        JLabel name = new JLabel("Name:");
        JTextField field = new JTextField();
        StringReceiver mObjectName = new StringReceiver();
        Form form = new Form(mObjectName);
        field.addActionListener(new TransportCharacteristicName(mObjectName));
        JPanel namePanel = new JPanel();
        namePanel.add(name);
        namePanel.add(field);
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        //Name field and finish action
        //characteristics field and adder
        JPanel panel = new JPanel();
        panel.add(namePanel);
        panel.setAlignmentY(TOP_ALIGNMENT);
        MOManagePanel managePanel = new MOManagePanel();
        panel.add(managePanel);
        JButton button = new JButton();
        MOCreationLink link = new MOCreationLink(managePanel, form);
        MObjectData data = new MObjectData(link);
        button.addActionListener(new AddField(data));
        panel.add(button);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel, BorderLayout.WEST);
        //characteristics field and adder
        //Preview and color chooser
        JColorChooser chooser = new JColorChooser();
        JPanel previewAndMode = new JPanel();
        FormManager formManager = formFactoryManager.createFormManager();
        MultiPaintPanel paintPanel = new MultiPaintPanel(formManager, new Point(100,100));
        paintPanel.instruction = formManager.getForm("Forest");
        ChooseColor chooseColor = new ChooseColor(paintPanel);
        MObjectBox box = new MObjectBox(mObjectAdder, paintPanel , link,  currentFactoryName);
        FinishAction finishAction = new FinishAction(mObjectAdder, form, paintAdder, formManager, pane, box.getHolder());
        JButton finish = new JButton();
        finish.addActionListener(finishAction);
        add(finish, BorderLayout.EAST);
        previewAndMode.setLayout(new BoxLayout(previewAndMode, BoxLayout.Y_AXIS));
        previewAndMode.add(box);

        previewAndMode.add(paintPanel);
        chooser.getSelectionModel().addChangeListener(chooseColor);
        add(previewAndMode, BorderLayout.CENTER);
        add(chooser, BorderLayout.SOUTH);
        //Preview and color chooser
        setVisible(true);
    }
}
interface Holder<T> {
    T getObject();
}
class MObjectBox extends JComboBox<String> implements Holder<String>{
    public MObjectBox(MObjectAdder mObjectAdder, MultiPaintPanel preview,
                      MOCreationLink link, String currentName) {
        addItemListener(new ChangePreview(preview));
        addItemListener(new EmptyData(link));
        setItems(mObjectAdder, currentName);
    }
    public void setItems(MObjectAdder adder, String currentName) { //currentName cause add button panel can be different from
        String[] names = adder.getFactoryNames();                  //current factory
        for(String s : names) {
            addItem(s);
        }
        setSelectedItem(currentName);
    }
    public String getObject() {
        return getItemAt(getSelectedIndex());
    }
    public Holder<String> getHolder() {
        return this;
    }
}
class ChangePreview implements ItemListener {
    MultiPaintPanel paintPanel;
    public ChangePreview(MultiPaintPanel paintPanel) {
        this.paintPanel = paintPanel;
    }
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
            paintPanel.changeForm((String)e.getItem());
            paintPanel.repaint();
        }
    }
}
class EmptyData implements ItemListener {
    MOCreationLink link;
    public EmptyData(MOCreationLink link) {
        this.link = link;
    }
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
            link.empty();
        }
    }
}
class FinishAction implements ActionListener {
    MObjectAdder mObjectAdder;
    Holder<String> factoryName;
    Form form;
    PaintAdder paintAdder;
    FormManager formManager;
    GameObjectPanel panel;
    public FinishAction(MObjectAdder mObjectAdder, Form form,
                        PaintAdder paintAdder, FormManager formManager, GameObjectPanel panel, Holder<String> holder) {
        this.mObjectAdder = mObjectAdder;
        this.form = form;
        this.paintAdder = paintAdder;
        this.formManager = formManager;
        this.panel = panel;
        factoryName = holder;
    }
    public void actionPerformed(ActionEvent e) {
        mObjectAdder.store(factoryName.getObject(), form);
        paintAdder.addForm(form.getName(), formManager.getForm(factoryName.getObject()));
        panel.updateButtons(factoryName.getObject());
        panel.dialogManager.release(DialogNames.MOCreation);


    }
}
class ChooseColor implements ChangeListener {
    Receiver<Color> paintPanel;
    public ChooseColor(Receiver<Color> paintPanel) {
        this.paintPanel = paintPanel;
    }
    public void stateChanged(ChangeEvent e) {
        ColorSelectionModel model = (DefaultColorSelectionModel)e.getSource();
        Color color = model.getSelectedColor();
        paintPanel.receive(color);
    }
}

class AddField implements ActionListener {
    MObjectData data;
    public AddField(MObjectData data) {
        this.data = data;
    }
    public void actionPerformed(ActionEvent e) {
        data.add();

    }
}
class MObjectData {
    MOCreationLink link;
    public MObjectData(MOCreationLink link) {
        this.link = link;
    }
    public void add() {
        JTextField label = new JTextField();
        JButton button = new JButton();
        NamedCharacteristic characteristic = new NamedCharacteristic(new IntegerCharacteristic());
        label.addActionListener(new TransportCharacteristicName(characteristic.getReceiver()));
        JComboBox<DisplayItem> comboBox = new JComboBox<>();
        comboBox.addItem(new DisplayItem("Integer", DisplayMethod.TextFieldInt));
        comboBox.addItem(new DisplayItem("Bool", DisplayMethod.CheckBox));
        comboBox.addItem(new DisplayItem("String", DisplayMethod.TextFieldString));
        Component[] components = new Component[]{label, characteristic.characteristic.display(), button,comboBox};
        MOField field = new MOField(components);
        comboBox.addActionListener(new ChangeCharacteristicDisplay(link));
        button.addActionListener(new RemoveAction(link));
        link.add(field, characteristic);
    }

}

class MOField {
    Component[] components;
    public MOField(Component[] components) {
        this.components = components;
    }
    public void addToPanel(JPanel panel) {
        for(Component component : components) {
            panel.add(component);
        }
    }
    public void removeFromPanel(JPanel panel) {
        for(Component component : components) {
            panel.remove(component);
        }
    }
    public int getLength() {
        return components.length;
    }
    public int getComponentIndex(Component component) {
        for(int i = 0;i < getLength();i++) {
            System.out.println("i component: " + components[i]);
            if(components[i].equals(component)) {
                return i;
            }
        }
        System.out.println("Searched component" + component);
        return -1;
    }
    public void replaceComponent(int index, Component component) {
        components[index] = component;
    }
    public void replaceComponent(Component oldComponent, Component nextComponent) {
        for(int i = 0;i< getLength();i++) {
            if(components[i].equals(oldComponent)) {
                components[i] = nextComponent;
            }
        }
    }
}
class ChangeCharacteristicDisplay implements ActionListener {
    int index;
    MOCreationLink link;
    public ChangeCharacteristicDisplay(MOCreationLink link) {
        this.index = link.getLength();
        this.link = link;
    }
    public void actionPerformed(ActionEvent e) {
        if(index < link.getLength()) {
            JComboBox<DisplayItem> comboBox = (JComboBox<DisplayItem>)e.getSource();
            DisplayItem item = comboBox.getItemAt(comboBox.getSelectedIndex());
            Characteristic<?> characteristic = new CharacteristicFactory().create(item.getDisplayMethod());
            JComponent component = characteristic.display();
            link.replace(component, index, characteristic, 1);
        }

    }
}
class TransportCharacteristicName implements ActionListener {
    StringReceiver receiver;
    public TransportCharacteristicName(StringReceiver characteristic) {
        this.receiver = characteristic;
    }
    public void actionPerformed(ActionEvent e) {
        String text = ((JTextField)e.getSource()).getText();
        InputChecker checker = new InputChecker();
        if(checker.checkPureLetters(text)) {
            System.out.println(text);
            System.out.println("Name is set");
            receiver.receiveString(text);
        }
    }
}
class RemoveAction implements ActionListener {
    int index;
    MOCreationLink link;
    public RemoveAction(MOCreationLink link) {
        this.link = link;
        this.index = link.getLength();

    }
    public void actionPerformed(ActionEvent e) {
        link.remove(index);
    }
}
class MOCreationLink {
    MOManagePanel panel;
    Form form;
    public MOCreationLink(MOManagePanel panel, Form form) {
        this.panel = panel;
        this.form = form;
    }
    public void empty() {
        panel.emptyAll();
        form.emptyForm();
    }
    public void remove(int index) {
        panel.remove(index);
        form.removeField(index);
    }
    public void replace(Component component, int index, Characteristic<?> characteristic, int componentIndex) {
        panel.replace(component, index, componentIndex);
        form.changeField(index, characteristic);
    }
    public int getLength() {
        return panel.getLength();
    }
    public void add(MOField field, NamedCharacteristic characteristic) {
        panel.addField(field);
        form.addField(characteristic);
        System.out.println(form);
    }
}
class MOManagePanel extends JPanel {
    ArrayList<MOField> fields;
    MOManagePanel() {
        fields = new ArrayList<>();
        setLayout(new GridLayout(0,4));
    }
    public void replace(Component component, int fieldIndex, int componentIndex) {
        MOField field = fields.get(fieldIndex);
        System.out.println(fieldIndex);
        int panelIndex = fieldIndex*field.getLength()+ componentIndex;
        remove(panelIndex);
        add(component, panelIndex);
        field.replaceComponent(componentIndex, component);
        revalidate();
        repaint();
    }
    public void emptyAll() {
        fields.clear();
        removeAll();
    }
    public void removeField(int index) {
        if(index < fields.size()) {
            MOField field = fields.get(index);
            fields.remove(index);
            field.removeFromPanel(this);
            GridLayout layout = (GridLayout) getLayout();
            layout.setRows(getLength());
            revalidate();
            repaint();
            System.out.println(getLength());
        }
    }
    public void addField(MOField field) {
        fields.add(field);
        field.addToPanel(this);
        GridLayout layout = (GridLayout) getLayout();
        layout.setRows(getLength());
        revalidate();
        repaint();
        System.out.println(getLength());
    }
    public int getLength() {
        return fields.size();
    }
}

