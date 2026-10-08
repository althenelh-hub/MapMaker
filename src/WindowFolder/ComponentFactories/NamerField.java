package WindowFolder.ComponentFactories;

import Data.StringReceiver;
import WindowFolder.InputChecker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NamerField extends JTextField {
    public NamerField(Namable name) {
        addActionListener(new CharacteristicName(name));
    }
}
class CharacteristicName implements ActionListener {
    Namable receiver;
    public CharacteristicName(Namable characteristic) {
        this.receiver = characteristic;
    }
    public void actionPerformed(ActionEvent e) {
        String text = ((JTextField)e.getSource()).getText();
        InputChecker checker = new InputChecker();
        if(checker.checkPureLetters(text)) {
            System.out.println(text);
            System.out.println("Name is set");
            receiver.giveName(text);
        }
    }
}
