package WindowFolder;

import PaintInstructions.PaintForm;

import java.util.HashMap;

public class FormManager {
    HashMap<String, PaintForm> forms = new HashMap<>();
    public void addForm(String name, PaintForm form) {
        forms.put(name, form);
    }
}
