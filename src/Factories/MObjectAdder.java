package Factories;

import Data.Characteristics.NamedStorage;
import Data.Form;

public interface MObjectAdder  {
    void store(String string, Form form);
    String[] getFactoryNames();
    boolean contains(String factoryName, String mObjectName);
}
