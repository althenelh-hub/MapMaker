package WindowFolder;

import Data.Form;
import PaintInstructions.PaintFormFactory;
import java.util.*;
public class FormFactoryManager {
    protected HashMap<String, PaintFormFactory<?>> factories = new HashMap<>();
    public PaintFormFactory<?> getFactory(String name) {
        return factories.get(name);
    }

}
