package Factories;

import java.util.HashMap;

public class FactoryManager {
    HashMap<String, Factory<?>> factories;
    Factory<?> currentFactory;
    public FactoryManager() {
        factories = new HashMap<>();
    }
    public void addFactory(String name, Factory<?> factory) {
        factories.put(name, factory);
    }
    public void changeFactory(String name) {
        currentFactory = factories.get(name);
    }
    public void changeCreation() {

    }
    public Factory<?> getCurrent() {
        return currentFactory;
    }
}
