package WindowFolder;

import Data.Form;
import Factories.Factory;
import Factories.FactoryManager;
import Factories.Filler;
import PaintInstructions.PaintForm;

import java.util.HashMap;

public class FactoryFormCommunication {
    FactoryFiller factoryFiller;
    FormFiller formFiller;
    class FactoryFiller extends FactoryManager implements Filler<Factory<?>> {
        public FactoryFiller() {
        }
        public void add(String name, Factory<?> factory) {
            factories.put(name, factory);
        }
    }
    class FormFiller extends FormManager implements Filler<PaintForm> {
        public void add(String name, PaintForm form) {
            forms.put(name, form);
        }
    }
    public FactoryManager getFactoryManager() {
        return factoryFiller;
    }
    public FormManager getFormManager() {
        return formFiller;
    }
    public FactoryFormCommunication() {
        factoryFiller = new FactoryFiller();
        formFiller = new FormFiller();
    }
    public void addFactory(String name, Factory<?> factory, PaintForm form) {
        factoryFiller.add(name, factory);
        formFiller.add(name, form);
    }
}
