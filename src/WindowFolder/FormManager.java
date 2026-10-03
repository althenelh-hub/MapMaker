package WindowFolder;

import PaintInstructions.PaintForm;

import java.util.HashMap;

public class FormManager {
    protected HashMap<String, PaintForm> forms = new HashMap<>();
    public PaintForm getForm(String name) {
        return forms.get(name);
    }
}
