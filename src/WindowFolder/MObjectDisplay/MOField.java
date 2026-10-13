package WindowFolder.MObjectDisplay;

import javax.swing.*;
import java.awt.*;

public class MOField {
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
