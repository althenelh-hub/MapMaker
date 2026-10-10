package WindowFolder.ComponentFactories;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ConfirmationField extends JTextField {
    boolean changed;
    String lastText;
    ArrayList<InputChecker> checkers;
    ArrayList<ActionStrategy> strategies;
    Color confirmColor;
    Color changedColor;
    public ConfirmationField(Color confirmColor, Color changedColor, String name) {
        changed = true;
        lastText = "*";
        setText(name);
        this.confirmColor = confirmColor;
        this.changedColor = changedColor;
        setBackground(changedColor);
        addCaretListener(new ChangedField());
        addActionListener(new ConfirmField());
    }
    public ConfirmationField(Color confirmColor, Color changedColor) {
        this(confirmColor,changedColor,"");
    }
    class ConfirmField implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean formatCorrect = true;
            for(InputChecker checker : checkers) {
                if(!checker.check(getText())) {
                    formatCorrect = false;
                }
            }
            if(formatCorrect) {
                changed = false;
                lastText = getText();
                setBackground(confirmColor);
                for(ActionStrategy strategy : strategies) {
                    strategy.realise();
                }
            }
        }
    }
    class ChangedField implements CaretListener {
        public void caretUpdate(CaretEvent e) {
            String text = getText();
            boolean changeConfirm = lastText.equals(text);
            if(!changeConfirm && !changed) {
                changed = true;
                setBackground(changedColor);
            }
            else if(changeConfirm && changed) {
                changed = false;
                setBackground(confirmColor);
            }
        }
    }
    public void addChecker(InputChecker checker) {
        checkers.add(checker);
    }
}
