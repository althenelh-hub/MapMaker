package WindowFolder;

import PaintInstructions.PaintForm;

import javax.swing.*;
import java.awt.*;

public class MultiPaintPanel extends PaintPanel<PaintForm> implements Receiver<Color>{
    FormManager formManager;
    public MultiPaintPanel(FormManager formManager, Point size) {
        super(size);
        this.formManager = formManager;
    }
    public void changeForm(String name) {
        PaintForm form = formManager.getForm(name);
        form.setColor(instruction.getColor());
        instruction = form;
    }
    public void receive(Color color) {
        instruction.setColor(color);
        revalidate();
        repaint();
    }



}
