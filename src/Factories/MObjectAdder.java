package Factories;

import Data.Form;

public interface MObjectAdder {
    void store(String string, Form form);
    String[] getFactoryNames();
    String getCurrentName();
}
