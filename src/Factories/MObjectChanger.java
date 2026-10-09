package Factories;

import Terrain.MapObject;

public interface MObjectChanger {
    void changeFactory(String factoryName, String MObjectName);
    String[] getFactoryNames();
    String[] getMObjectNames(String factoryName);
    MapObject getCurrentMapObject();
}
