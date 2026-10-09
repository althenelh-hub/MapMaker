package WindowFolder.Listeners;

import Data.Characteristic;
import Data.CharacteristicFactory;
import Data.DisplayItem;
import Data.InfoModifier;
import WindowFolder.MOManagePanel;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ChangeView implements ItemListener {
    InfoModifier filler;
    String valueName;
    MOManagePanel panel;
    int index;
    public ChangeView(InfoModifier filler, String valueName, MOManagePanel panel, int index) {
        this.filler = filler;
        this.valueName = valueName;
        this.panel = panel;
        this.index = index;
    }
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
            JComboBox<DisplayItem> source = (JComboBox<DisplayItem>)e.getSource();
            DisplayItem item = source.getItemAt(source.getSelectedIndex());
            Characteristic<?> characteristic = new CharacteristicFactory().create(item.getDisplayMethod());
            filler.changeValue(valueName, characteristic);
            panel.replace(characteristic.display(), index, 1);
        }
    }
}
