package PaintInstructions;

public abstract class LayeredInstruction implements PaintInstructionV2 {
    int layer;
    public int getLayer() {
        return layer;
    }
}
