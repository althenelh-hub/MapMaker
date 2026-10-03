package Factories;

import Data.MapData;

import java.awt.*;
import java.util.HashMap;

public class FactorySwitch {
    protected HashMap<String, Factory<?>> factories;
    protected Factory<?> currentFactory;
    public void changeFactory(String name) {
        currentFactory = factories.get(name);
    }
    public void put(Point point, MapData data) {
        currentFactory.put(point, data);
    }

}
