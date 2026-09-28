package PaintInstructions;

import java.awt.*;

public class PaintUnit implements PaintInstruction {
    Color gameClassColor;;
    Color FactionColor;
    Rectangle size;
    public void fulfill(Graphics g) {
        g.setColor(gameClassColor);
        g.fillOval(size.x - 2, size.y-2, size.width - 2, size.height-2);
        g.setColor(FactionColor);
        g.drawOval(size.x,size.y, size.width, size.height);
    }
}
