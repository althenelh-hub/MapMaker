package WindowFolder;
import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.colorchooser.DefaultColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import Data.*;
import Factories.Factory;
import Factories.MObjectAdder;
import PaintInstructions.Painter;
import Terrain.*;
import Factories.FactoryManager;
import PaintInstructions.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        MObjectData data = new MObjectData(managePanel, form);
        button.addActionListener(new AddField(data));
        panel.add(button);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel, BorderLayout.WEST);
        //characteristics field and adder
        //Preview and color chooser
        JColorChooser chooser = new JColorChooser();
        JPanel previewAndMode = new JPanel();
        FormManager formManager = formFactoryManager.createFormManager();
        PaintInstructionPanel preview = new PaintInstructionPanel(formManager.getForm("Forest"), new Point(100,100));
        ChooseColor chooseColor = new ChooseColor(formManager.getForm("Forest"), preview);
        MObjectBox box = new MObjectBox(mObjectAdder, preview, chooseColor, data, formManager, chooser, currentFactoryName);
        FinishAction finishAction = new FinishAction(mObjectAdder, form, paintAdder, formManager, pane, box.getHolder());
        JButton finish = new JButton();
        finish.addActionListener(finishAction);
        add(finish, BorderLayout.EAST);
        previewAndMode.setLayout(new BoxLayout(previewAndMode, BoxLayout.Y_AXIS));
        previewAndMode.add(box);

        previewAndMode.add(preview);
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
    public MObjectBox(MObjectAdder mObjectAdder, PaintInstructionPanel preview, ChooseColor chooseColor,
                      MObjectData data, FormManager formManager,JColorChooser colorChooser, String currentName) {
        ChangeMObject changeMObject = new ChangeMObject(preview, chooseColor, data, formManager, colorChooser);
        addItemListener(changeMObject);
        setItems(mObjectAdder, currentName);
    }
    public void setItems(MObjectAdder adder, String currentName) {
        String[] names = adder.getFactoryNames();
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
class ChangeMObject implements ItemListener {
    PaintInstructionPanel preview;
    FormManager formManager;
    ChooseColor chooseColor;
    MObjectData data;
    JColorChooser colorChooser;
    public ChangeMObject(PaintInstructionPanel panel,
                         ChooseColor chooseColor, MObjectData data, FormManager formManager, JColorChooser colorChooser) {
        preview = panel;
        this.chooseColor = chooseColor;
        this.data = data;
        this.formManager = formManager;
        this.colorChooser = colorChooser;
    }
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
            String name = (String)e.getItem();
            PaintForm form = formManager.getForm(name);
            preview.setInstruction(form);
            data.empty();
            chooseColor.form = form;
            form.setColor(colorChooser.getColor());
            preview.repaint();
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
    PaintForm form;
    JPanel preview;
    public ChooseColor(PaintForm form, JPanel panel) {
        this.form = form;
        this.preview = panel;
    }
    public void stateChanged(ChangeEvent e) {
        ColorSelectionModel model = (DefaultColorSelectionModel)e.getSource();
        Color color = model.getSelectedColor();
        form.setColor(color);
        preview.revalidate();
        preview.repaint();
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
    MOManagePanel panel;
    Form form;
    public MObjectData(MOManagePanel panel, Form form) {
        this.panel = panel;
        this.form = form;
    }
    public void remove(int index) {
        panel.removeField(index);
        form.removeField(index);
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
        MOField field = new MOField(label, characteristic.characteristic.display(), button, comboBox);
        comboBox.addActionListener(new ChangeCharacteristicDisplay(panel.getLength(),panel, field, form));
        button.addActionListener(new RemoveAction(this));
        panel.addField(field);
        form.addField(characteristic);
    }
    public void empty() {
        panel.emptyAll();
        form.emptyForm();
    }
    public int getLength() {
        return panel.getLength();
    }
}

class MOField {
    public JTextField name;
    public JComponent value;
    JComboBox<DisplayItem> comboBox;
    JButton removeButton;
    public MOField(JTextField name, JComponent value, JButton button, JComboBox<DisplayItem> comboBox) {
        this.name = name;
        this.value = value;
        removeButton = button;
        this.comboBox = comboBox;
    }
    public void addToPanel(JPanel panel) {
        panel.add(name);
        panel.add(value);
        panel.add(removeButton);
        panel.add(comboBox);
    }
    public void removeFromPanel(JPanel panel) {
        panel.remove(name);
        panel.remove(value);
        panel.remove(removeButton);
        panel.remove(comboBox);
    }
}
class ChangeCharacteristicDisplay implements ActionListener {
    int index;
    JPanel panel;
    MOField field;
    Form form;
    public ChangeCharacteristicDisplay(int index, JPanel panel, MOField field, Form form) {
        this.index = index;
        this.panel = panel;
        this.field = field;
        this.form = form;
    }
    public void actionPerformed(ActionEvent e) {
        if(index < panel.getComponentCount()) {
            panel.remove(index*4+1);
            JComboBox<DisplayItem> comboBox = (JComboBox<DisplayItem>)e.getSource();
            DisplayItem item = comboBox.getItemAt(comboBox.getSelectedIndex());
            Characteristic<?> characteristic = new CharacteristicFactory().create(item.getDisplayMethod());
            JComponent component = characteristic.display();
            panel.add(component, index*4+1);
            form.changeField(index, characteristic);
            field.value = component;
            panel.revalidate();
            panel.repaint();
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
    MObjectData data;
    public RemoveAction(MObjectData data) {
        this.data = data;
        this.index = data.getLength();

    }
    public void actionPerformed(ActionEvent e) {
        data.remove(index);
    }
}
class MOManagePanel extends JPanel {
    ArrayList<MOField> fields;
    MOManagePanel() {
        fields = new ArrayList<>();
        setLayout(new GridLayout(0,4));
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

