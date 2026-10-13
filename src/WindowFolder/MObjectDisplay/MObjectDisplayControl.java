package WindowFolder.MObjectDisplay;
import Terrain.MapObject;

import javax.swing.*;
import java.util.ArrayList;

public class MObjectDisplayControl extends JScrollPane {
    JPanel mainPanel;
    public MObjectDisplayControl(JPanel panel) {
        super(panel);
        mainPanel = panel;
    }
    ArrayList<MObjectShow> panels = new ArrayList<>();
    public void displayMObjects(MapObject[] mObjects) {
        if(panels.size() < mObjects.length) {
            MObjectShow mObjectShow = new MObjectShow();
            mainPanel.add(mObjectShow.mainPanel);
            panels.add(mObjectShow);
        }
        else if(panels.size() > mObjects.length) {
            panels.remove(panels.size()-1);
            mainPanel.remove(panels.size()-1);
        }
        for(int i = 0;i < panels.size();i++) {
            panels.get(i).show(mObjects[i]);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
