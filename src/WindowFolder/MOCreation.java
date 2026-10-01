package WindowFolder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import PaintInstructions.*;
import PaintInstructions.Painter;
import Terrain.MapObject;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MOCreation extends JDialog {
    JPanel preview;
    MapObject mapObject;
    HashMap<String, Integer> fieldValues;
    public MOCreation() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(1000,800));
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        MOManagePanel managePanel = new MOManagePanel();
        panel.add(managePanel);
        JButton button = new JButton();
        Form form = new Form();
        button.addActionListener(new AddField(managePanel, form));
        panel.add(button);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel, BorderLayout.WEST);
        JButton finish = new JButton();
        finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(form);
            }
        });
        add(finish, BorderLayout.EAST);
        setVisible(true);
    }
}
class ChooseColor implements ActionListener {
    JDialog dialog;
    Painter painter;
    public void actionPerformed(ActionEvent e) {
        dialog.dispose();

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
        label.addActionListener(new TransportCharacteristicName(characteristic));
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
        if (text.charAt(0) < 49 && text.length()>1 || text.charAt(0) > 58) {
            System.out.println("Wrong first");
            return;
        }
        for(int i = 0;i < text.length();i++) {
            if(text.charAt(i) < 47 && text.charAt(i) > 58) {
                System.out.println("Wrong format");
                return;
            }
        }
        System.out.println("Value is set");
        characteristic.value = Integer.parseInt(text);
    }

}
class TransportCharacteristicName implements ActionListener {
    Characteristic characteristic;
    public TransportCharacteristicName(Characteristic characteristic) {
        this.characteristic = characteristic;
    }
    public void actionPerformed(ActionEvent e) {
        String text = ((JTextField)e.getSource()).getText();
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher matcher = pattern.matcher(text);
        if(!matcher.find()) {
            System.out.println(text);
            System.out.println("Name is set");
            characteristic.name = text;
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
            mObject.addValue(c.name, c.value);
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
            builder.append(c + "\n");
        }
        return builder.toString();
    }
}
class Characteristic {
    String name;
    int value;
    public String toString() {
        return "Name: " + name + "Value: " + value;
    }
}

