package WindowFolder;
import Factories.FactoryManager;
import PaintInstructions.Painter;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;
public class DialogManager {
    HashMap<DialogNames, JDialog> currentDialogs;
    FormFactoryManager formFactoryManager;
    FactoryManager factoryManager;
    Painter painter;
    public DialogManager(FormFactoryManager formFactoryManager, FactoryManager factoryManager, Painter painter) {
        currentDialogs = new HashMap<>();
        this.formFactoryManager = formFactoryManager;
        this.factoryManager = factoryManager;
        this.painter = painter;
    }
    public void release(DialogNames name) {
        if(currentDialogs.containsKey(name)) {
            currentDialogs.get(name).dispose();
        }
        currentDialogs.remove(name);
    }
    public void activateCreation(GameObjectPanel panel) {
        if(!currentDialogs.containsKey(DialogNames.MOCreation)) {
            JDialog dialog = new MOCreation(factoryManager, painter, panel, formFactoryManager, panel.getCurrentTitle());
            currentDialogs.put(DialogNames.MOCreation, dialog);
            dialog.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {

                }

                @Override
                public void windowClosing(WindowEvent e) {

                }

                @Override
                public void windowClosed(WindowEvent e) {
                    currentDialogs.remove(DialogNames.MOCreation);
                }

                @Override
                public void windowIconified(WindowEvent e) {

                }

                @Override
                public void windowDeiconified(WindowEvent e) {

                }

                @Override
                public void windowActivated(WindowEvent e) {

                }

                @Override
                public void windowDeactivated(WindowEvent e) {

                }
            });
        }
        else {
            JDialog dialog = currentDialogs.get(DialogNames.MOCreation);
            dialog.requestFocus();
        }
    }

}
