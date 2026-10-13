package Listeners;

import WindowFolder.MapGrid;

import java.awt.*;
import java.awt.event.*;

public class SizeListener implements ComponentListener {
    MapGrid grid;
    public SizeListener(MapGrid grid) {
        this.grid = grid;
    }
    public void componentHidden(ComponentEvent event) {

    }
    public void componentShown(ComponentEvent event) {

    }
    public void componentMoved(ComponentEvent event) {

    }
    public void componentResized(ComponentEvent event) {
        Rectangle parent = event.getComponent().getBounds();
        System.out.println("Window size: " + event.getComponent().getParent().getBounds());
        grid.reshape(parent);
    }
}
