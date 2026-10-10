package WindowFolder.Listeners;

import WindowFolder.ComponentFactories.Namable;
import WindowFolder.FormatChecker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacteristicName implements ActionListener {
    Namable receiver;
    public CharacteristicName(Namable characteristic) {
        this.receiver = characteristic;
    }
    public void actionPerformed(ActionEvent e) {
        String text = ((JTextField)e.getSource()).getText();
        FormatChecker checker = new FormatChecker();
        if(checker.checkPureLetters(text)) {
            System.out.println(text);
            System.out.println("Name is set");
            receiver.giveName(text);
        }
    }
}
