package PaintInstructions;

public abstract class LayeredInstruction implements PaintInstruction{
    int layer;
    public int getLayer() {
        return layer;
    }
}
