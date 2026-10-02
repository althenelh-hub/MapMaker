package WindowFolder;

import Factories.Factory;
import Factories.FactoryManager;
import PaintInstructions.PaintForm;

import java.util.HashMap;

public class FactoryFormCommunication {
    FactoryManager factoryManager;
    FormManager formManager;
    public FactoryFormCommunication(FactoryManager factoryManager, FormManager formManager) {
        this.factoryManager = factoryManager;
        this.formManager = formManager;
    }
    public void addFactory(String name, Factory<?> factory, PaintForm form) {
        factoryManager.addFactory(name, factory);
        formManager.addForm(name, form);
    }
}
