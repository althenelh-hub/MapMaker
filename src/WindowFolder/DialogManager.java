package WindowFolder;
import Factories.FactoryManager;
import PaintInstructions.Painter;

import javax.swing.*;
import java.util.*;
public class DialogManager {
    HashMap<DialogNames, JDialog> currentDialogs;
    FormFactoryManager formFactoryManager;
    public DialogManager(FormFactoryManager formFactoryManager) {
        currentDialogs = new HashMap<>();
        this.formFactoryManager = formFactoryManager;
    }
    public void activateCreation(FactoryManager factoryManager, FormManager formManager, Painter painter, GameObjectPanel panel) {
        if(!currentDialogs.containsKey(DialogNames.MOCreation)) {
            new MOCreation(factoryManager, formManager, painter, panel, formFactoryManager);
        }
        else {
            JDialog dialog = currentDialogs.get(DialogNames.MOCreation);
            dialog.requestFocus();
        }
    }
    public void start() {

    }
}
