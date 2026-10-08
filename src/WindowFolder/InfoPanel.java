package WindowFolder;

import Data.Form;
import Data.InfoStorage;
import Data.NamedCharacteristic;
import Data.StringReceiver;
import Terrain.MapObject;
import WindowFolder.ComponentFactories.AddButton;

import javax.swing.*;

public class InfoPanel extends JPanel {
    public void displayMObject(MapObject mapObject) {
        Form form = new Form(new StringReceiver(mapObject.name));
        mapObject.shareValues(form);
        MOManagePanel panel = new MOManagePanel();

        MOCreationLink link = new MOCreationLink(panel, form);
        MObjectData data = new MObjectData(link);
        add(panel);
        add(new AddButton(data));
    }
}

