package WindowFolder.ComponentFactories;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ConfirmationField extends JTextField {
    boolean changed;
    String lastText;
    ArrayList<InputChecker> checkers = new ArrayList<>();
    ArrayList<ActionStrategy> strategies = new ArrayList<>();
    Color confirmColor;
    Color changedColor;
    public ConfirmationField(Color confirmColor, Color changedColor, String name) {
        changed = true;
        lastText = name;
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
            String message = "";
            System.out.println("Confirm field");
            for(InputChecker checker : checkers) {
                CheckMessage checkResult = checker.check(getText());
                if(!checkResult.isValid) {
                    formatCorrect = false;
                    message = checkResult.getError();
                    break;
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
            else{
                System.out.println(message); //temporary dialog in future
            }
        }
    }
    class ChangedField implements CaretListener {
        public void caretUpdate(CaretEvent e) {
            String text = getText();
            System.out.println("Change field");
            boolean changeConfirm = lastText.equals(text);
            if(!changeConfirm && !changed) {
                changed = true;
                setBackground(changedColor);
            }
            else if(changeConfirm && changed && !text.isEmpty()) {
                changed = false;
                setBackground(confirmColor);
            }
        }
    }
    public void changeState(boolean state) {
        changed = state;
        if(changed) {
            setBackground(changedColor);
        }
        else {
            System.out.println("Background is set");
            setBackground(confirmColor);
        }
    }
    public void addChecker(InputChecker checker) {
        checkers.add(checker);
    }
    public void addCheckers(InputChecker[] checks) {
        checkers.addAll(Arrays.asList(checks));
    }
    public void addActionStrategy(ActionStrategy strategy) {
        strategies.add(strategy);
    }
    public void addActionStrategies(ActionStrategy[] strats) {
        strategies.addAll(Arrays.asList(strats));
    }
}
