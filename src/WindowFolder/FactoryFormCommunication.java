package WindowFolder;

import Factories.Factory;
import Factories.FactoryManager;
import Factories.Filler;
import PaintInstructions.PaintForms.PaintFormFactory;

public class FactoryFormCommunication {
    FactoryFiller factoryFiller;
    FormFactoryFiller formFactoryFiller;
    class FormFactoryFiller extends FormFactoryManager implements Filler<PaintFormFactory<?>> {
        @Override
        public void add(String name, PaintFormFactory<?> object) {
            factories.put(name, object);
        }
    }
    class FactoryFiller extends FactoryManager implements Filler<Factory<?>> {
        public FactoryFiller() {
        }
        public void add(String name, Factory<?> factory) {
            factories.put(name, factory);
        }
    }

    public FormFactoryManager getFormFactoryManager() {
        return formFactoryFiller;
    }
    public FactoryManager getFactoryManager() {
        return factoryFiller;
    }

    public FactoryFormCommunication() {
        factoryFiller = new FactoryFiller();
        formFactoryFiller = new FormFactoryFiller();
    }
    public void addFactory(String name, Factory<?> factory, PaintFormFactory<?> paintFormFactory) {
        factoryFiller.add(name, factory);
        formFactoryFiller.add(name, paintFormFactory);
    }
}
