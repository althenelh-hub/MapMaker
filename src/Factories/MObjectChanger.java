package Factories;

public interface MObjectChanger {
    void changeFactory(String factoryName, String MObjectName);
    String[] getFactoryNames();
    String[] getMObjectNames(String factoryName);
}
