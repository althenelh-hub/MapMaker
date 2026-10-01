package WindowFolder;
import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.colorchooser.DefaultColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

import Factories.Factory;
import Factories.ForestFactory;
import PaintInstructions.*;
import PaintInstructions.Painter;
import Terrain.MapObject;
import Terrain.Terrain;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MOCreation extends JDialog {
    public MOCreation(ForestFactory factory) {
        //Dialog settings
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(1000,800));
        setLayout(new BorderLayout());
        //Dialog settings
        //Name field and finishAction
        JLabel name = new JLabel("Name:");
        JTextField field = new JTextField();
        Form form = new Form();
        FinishAction finishAction = new FinishAction(factory, form, "", this);
        field.addActionListener(new TransportCharacteristicName(finishAction.name));
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
        button.addActionListener(new AddField(managePanel, form));
        panel.add(button);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel, BorderLayout.WEST);
        //characteristics field and adder
        //Preview and color chooser
        JColorChooser chooser = new JColorChooser();
        PaintForm paintForm = new PaintTile();
        JPanel preview = new PaintInstructionPanel(paintForm, new Point(100,100));
        chooser.getSelectionModel().addChangeListener(new ChooseColor(paintForm, preview));
        add(preview, BorderLayout.CENTER);
        add(chooser, BorderLayout.SOUTH);
        //Preview and color chooser
        setVisible(true);
    }
}
class FinishAction implements ActionListener{
    ForestFactory factory;
    Form form;
    StringReceiver name;
    Dialog dialog;
    public FinishAction(ForestFactory factory, Form form, String string, Dialog dialog) {
        this.factory = factory;
        this.form = form;
        this.dialog = dialog;
        name = new StringReceiver();
        name.receiveString(string);
    }
    public void actionPerformed(ActionEvent e) {
        Terrain terrain = new Terrain();
        form.fillMapObject(terrain);
        factory.create(name.text, terrain);
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

class StringReceiver {
    String text;
    public void receiveString(String text) {
        this.text = text;
    }
}
class AddField implements ActionListener {
    MOManagePanel panel;
    Form form;
    public AddField(MOManagePanel panel, Form form) {
        this.panel = panel;
        this.form = form;
    }
    public void actionPerformed(ActionEvent e) {
        JTextField label = new JTextField();
        JTextField value = new JTextField();
        JButton button = new JButton();
        button.addActionListener(new RemoveAction(panel, form));
        MOField field = new MOField(label, value, button);
        panel.addField(field);
        Characteristic characteristic = new Characteristic();
        value.addActionListener(new CheckTF(characteristic));
        label.addActionListener(new TransportCharacteristicName(characteristic.name));
        form.addField(characteristic);
        System.out.println(((JButton)e.getSource()).getParent());

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
        if(!matcher.find()) {
            return true;
        }
        else {
            return false;
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
    MOManagePanel panel;
    int index;
    Form form;
    public RemoveAction(MOManagePanel panel, Form form) {
        this.panel = panel;
        this.index = panel.getLength();
        this.form = form;
    }
    public void actionPerformed(ActionEvent e) {
        panel.removeField(index);
        form.removeField(index);
    }
}
class MOManagePanel extends JPanel {
    ArrayList<MOField> fields;
    MOManagePanel() {
        fields = new ArrayList<>();
        setLayout(new GridLayout(0,3));
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
class Form {
    ArrayList<Characteristic> fields;
    public Form() {
        fields = new ArrayList<>();
    }
    public void fillMapObject(MapObject mObject) {
        for(Characteristic c : fields) {
            mObject.addValue(c.name.text, c.value);
        }
    }
    public void addField(Characteristic characteristic) {
        fields.add(characteristic);
    }
    public void removeField(int index) {
        fields.remove(index);
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Characteristic c : fields) {
            builder.append(c).append("\n");
        }
        return builder.toString();
    }
}
class Characteristic {
    StringReceiver name;
    int value;
    public String toString() {
        return "Name: " + name + "Value: " + value;
    }
}

