package WindowFolder;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SizeController {
    JSlider slider;
    JTextField field;
    public SizeController(int startValue) {
        slider = new JSlider();
        slider.setValue(startValue);
        field = new JTextField(""+startValue);
        field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputChecker checker = new InputChecker();
                String text = field.getText();
                if(checker.checkOnDigits(text)) {
                    slider.setValue(Integer.parseInt(text));
                }
            }
        });
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                field.setText(""+slider.getValue());
            }
        });
    }
    public void addListeners(ActionListener action, ChangeListener change) {
        field.addActionListener(action);
        slider.addChangeListener(change);
    }
    public void addToComponent(JComponent component) {
        component.add(slider);
        component.add(field);

    }
}
