package PaintInstructions;

public abstract class LayeredInstruction implements PaintInstructionV2 {
    int layer;
    public LayeredInstruction(int layer) {
        this.layer = layer;
    }
    public int getLayer() {
        return layer;
    }
}
