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
import Terrain.*;
import Factories.FactoryManager;
import PaintInstructions.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MOCreation extends JDialog {

    public MOCreation(FactoryManager manager, PaintForm paintForm) {
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
        FinishAction finishAction = new FinishAction(manager, form,this);
        field.addActionListener(new TransportCharacteristicName(mObjectName));
        JButton finish = new JButton();
        finish.addActionListener(finishAction);
        add(finish, BorderLayout.EAST);
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
        JPanel preview = new PaintInstructionPanel(paintForm, new Point(100,100));
        chooser.getSelectionModel().addChangeListener(new ChooseColor(paintForm, preview));
        add(preview, BorderLayout.CENTER);
        add(chooser, BorderLayout.SOUTH);
        JComboBox<String> comboBox = new JComboBox<>();
        String string = comboBox.getItemAt(comboBox.getSelectedIndex());
        //Preview and color chooser
        setVisible(true);
    }
}
class changeMObject implements ChangeListener {
    FactoryManager manager;
    PaintInstructionPanel preview;
    HashMap<String, PaintForm> forms;
    ChooseColor chooseColor;
    MObjectData data;
    JColorChooser colorChooser;
    public void stateChanged(ChangeEvent e) {
        JComboBox<String> comboBox = (JComboBox<String>)e.getSource();
        String name = comboBox.getItemAt(comboBox.getSelectedIndex());
        manager.changeFactory(name);
        preview.setInstruction(forms.get(name));
        data.empty();
        chooseColor.form = forms.get(name);
        forms.get(name).setColor(colorChooser.getColor());
    }
}
class FinishAction implements ActionListener {
    FactoryManager manager;
    Form form;
    Dialog dialog;
    public FinishAction(FactoryManager manager, Form form, Dialog dialog) {
        this.manager = manager;
        this.form = form;
        this.dialog = dialog;
    }
    public void actionPerformed(ActionEvent e) {
        Factory<?> current = manager.getCurrent();
        current.store(form);
        current.change(form.getName());
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

