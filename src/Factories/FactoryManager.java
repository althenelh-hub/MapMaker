package Factories;

import Data.Form;
import Data.MapData;

import java.awt.*;
import java.util.Set;
import java.util.HashMap;

public class FactoryManager implements MObjectAdder, MObjectChanger, MObjectInjector{
    protected HashMap<String, Factory<?>> factories;
    protected Factory<?> currentFactory;
    public FactoryManager() {
        factories = new HashMap<>();
    }
    public void put(Point point, MapData mapData) {
        currentFactory.put(point, mapData);
    }
    public void changeFactory(String factoryName, String mObjectName) {
        currentFactory = factories.get(factoryName);
        currentFactory.change(mObjectName);
    }
    public void store(String name, Form form) {
        getFactory(name).store(form);
    }
    public String[] getMObjectNames(String factoryName) {
        Factory<?> factory = getFactory(factoryName);
        Set<String> names = factory.mapObjects.keySet();
        String[] result = new String[names.size()];
        names.toArray(result);
        return result;
    }
    public String[] getFactoryNames() {
        Set<String> set = factories.keySet();
        String[] result = new String[set.size()];
        set.toArray(result);
        return result;
    }
    public Factory<?> getFactory(String name) {
        return factories.get(name);
    }
    public String getCurrentName() {
        return currentFactory.getName();
    }
    public Factory<?> getCurrent() {
        return currentFactory;
    }
}
