package Factories;

import Data.*;
import Terrain.MapObject;

import java.awt.*;
import java.util.HashMap;

public abstract class Factory<T extends MapObject> {
    HashMap<String, T> mapObjects = new HashMap<>();
    T currentMapObject;
    String name;
    public Factory(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public abstract void put(Point point, MapData data);
    public abstract boolean check(Point point, MapData data);
    public void change(String name) {
        currentMapObject = mapObjects.get(name);
        System.out.println("Current MObject: " + currentMapObject);
    }
    public void changeCurrent(String name, Integer value) {
        currentMapObject.addValue(name, value);
    }
    public void store (Form form) {
        mapObjects.put(form.getName(), create(form));
    }
    public String[] getMObjectNames() {
        String[] names = new String[mapObjects.size()];
        mapObjects.keySet().toArray(names);
        return names;
    }
    protected abstract T create(Form form);

}
