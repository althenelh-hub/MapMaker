package Factories;

import java.util.HashMap;

public class FactoryManager {
    HashMap<String, Factory<?>> factories;
    Factory<?> currentFactory;
    public FactoryManager() {

    }
    public void changeFactory(String name) {
        currentFactory = factories.get(name);
    }
    public void changeCreation() {

    }
}
