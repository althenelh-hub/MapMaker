package WindowFolder;

import Data.Form;
import PaintInstructions.PaintForm;
import PaintInstructions.PaintFormFactory;

import java.awt.*;
import java.util.*;
public class FormFactoryManager {
    protected HashMap<String, PaintFormFactory<?>> factories = new HashMap<>();
    public PaintFormFactory<?> getFactory(String name) {
        return factories.get(name);
    }
    class TransferFormManager extends FormManager {
        public void add(String name, PaintForm paintForm) {
            forms.put(name, paintForm);
        }
    }
    public FormManager createFormManager() {
        TransferFormManager formManager = new TransferFormManager();
        for(String s : factories.keySet()) {
            formManager.add(s, factories.get(s).createForm(Color.WHITE));
        }
        return formManager;
    }

}
