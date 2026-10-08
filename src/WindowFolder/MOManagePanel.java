package WindowFolder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MOManagePanel extends JPanel {
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
