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
import PaintInstructions.Painter;
import Terrain.*;
import Factories.FactoryManager;
import PaintInstructions.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MOCreation extends JDialog {

    public MOCreation(FactoryManager manager, FormManager formManager,Painter painter, GameObjectPanel pane,
                      FormFactoryManager formFactoryManager) {
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
        PaintInstructionPanel preview = new PaintInstructionPanel(formManager.getForm("Forest"), new Point(100,100));
        ChooseColor chooseColor = new ChooseColor(formManager.getForm("Forest"), preview);
        MObjectBox box = new MObjectBox(manager, preview, chooseColor, data, formManager, chooser);
        FinishAction finishAction = new FinishAction(manager, form,this, formFactoryManager,painter, formManager, pane, box.getHolder());
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
    public MObjectBox(FactoryManager manager, PaintInstructionPanel preview, ChooseColor chooseColor,
                      MObjectData data, FormManager formManager,JColorChooser colorChooser) {
        ChangeMObject changeMObject = new ChangeMObject(manager, preview, chooseColor, data, formManager, colorChooser);
        addItemListener(changeMObject);
        setItems(manager);
    }
    public void setItems(FactoryManager manager) {
        String[] names = manager.getFactoriesName();
        for(String s : names) {
            addItem(s);
        }
    }
    public String getObject() {
        return getItemAt(getSelectedIndex());
    }
    public Holder<String> getHolder() {
        return this;
    }
}
class ChangeMObject implements ItemListener {
    FactoryManager factoryManager;
    PaintInstructionPanel preview;
    FormManager formManager;
    ChooseColor chooseColor;
    MObjectData data;
    JColorChooser colorChooser;
    public ChangeMObject(FactoryManager manager, PaintInstructionPanel panel,
                         ChooseColor chooseColor, MObjectData data, FormManager formManager, JColorChooser colorChooser) {
        this.factoryManager = manager;
        preview = panel;
        this.chooseColor = chooseColor;
        this.data = data;
        this.formManager = formManager;
        this.colorChooser = colorChooser;
    }
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
            String name = (String)e.getItem();
            factoryManager.changeFactory(name);
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
    FactoryManager manager;
    Holder<String> holder;
    Form form;
    Dialog dialog;
    Painter painter;
    FormManager formManager;
    GameObjectPanel panel;
    FormFactoryManager formFactoryManager;
    public FinishAction(FactoryManager manager, Form form, Dialog dialog,FormFactoryManager formFactoryManager,
                        Painter painter, FormManager formManager, GameObjectPanel panel, Holder<String> holder) {
        this.manager = manager;
        this.form = form;
        this.dialog = dialog;
        this.painter = painter;
        this.formFactoryManager = formFactoryManager;
        this.formManager = formManager;
        this.panel = panel;
        this.holder = holder;
    }
    public void actionPerformed(ActionEvent e) {
        Factory<?> current = manager.getFactory(holder.getObject());
        current.store(form);
        current.change(form.getName());
        PaintFormFactory<?> paintFormFactory = formFactoryManager.getFactory(holder.getObject());
        painter.addForm(form.getName(), );
        panel.updateButtons(manager, formManager,painter);
        System.out.println(current.getName());
        dialog.dispose();

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
        JTextField value = new JTextField();
        JButton button = new JButton();
        Characteristic characteristic = new Characteristic();
        label.addActionListener(new TransportCharacteristicName(characteristic.getReceiver()));
        value.addActionListener(new CheckTF(characteristic));
        MOField field = new MOField(label, value, button);
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
    public JTextField value;
    JButton removeButton;
    public MOField(JTextField name, JTextField value, JButton button) {
        this.name = name;
        this.value = value;
        removeButton = button;
    }
    public void addToPanel(JPanel panel) {
        panel.add(name);
        panel.add(value);
        panel.add(removeButton);
    }
    public void removeFromPanel(JPanel panel) {
        panel.remove(name);
        panel.remove(value);
        panel.remove(removeButton);
    }
}
class CheckTF implements ActionListener {
    Characteristic characteristic;
    public CheckTF(Characteristic characteristic) {
        this.characteristic = characteristic;
    }
    public void actionPerformed(ActionEvent e) {
        String text = ((JTextField)e.getSource()).getText();
        InputChecker checker = new InputChecker();
        if(checker.checkOnDigits(text)) {
            characteristic.value = Integer.parseInt(text);
        }
        System.out.println("Value is set");
    }

}
class InputChecker {
    public boolean checkOnDigits(String text) {
        if (text.charAt(0) < 49 && text.length()>1 || text.charAt(0) > 58) {
            System.out.println("Wrong first");
            return false;
        }
        for(int i = 0;i < text.length();i++) {
            if(text.charAt(i) < 47 && text.charAt(i) > 58) {
                System.out.println("Wrong format");
                return false;
            }
        }
        return true;
    }
    public boolean checkPureLetters(String text) {
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher matcher = pattern.matcher(text);
        return !matcher.find();
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
        setLayout(new GridLayout(0,3));
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

