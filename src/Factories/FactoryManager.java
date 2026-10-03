package Factories;

import Data.Form;

import java.util.Set;
import java.util.HashMap;

public class FactoryManager extends FactorySwitch{
    public FactoryManager() {
        factories = new HashMap<>();
    }
    public void store(Form form) {
        currentFactory.store(form);
    }
    public String[] getFactoriesName() {
        Set<String> set = factories.keySet();
        String[] result = new String[set.size()];
        set.toArray(result);
        return result;
    }
    public FactorySwitch getFactorySwitch() {
        return this;
    }
    public Factory<?> getFactory(String name) {
        return factories.get(name);
    }
    public Factory<?> getCurrent() {
        return currentFactory;
    }
}
