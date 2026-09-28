package PaintInstructions;
import java.util.*;
public class PaintQueue {
    ArrayList<PaintInstruction> paintQueue;
    PaintInstruction baseInstruction;
    public PaintQueue(PaintInstruction baseInstruction) {
        this.baseInstruction = baseInstruction;
        paintQueue = new ArrayList<>();
    }
    public void put(PaintInstruction paintInstruction) {
        paintQueue.add(paintInstruction);
    }
    public PaintInstruction pop() {
        if(paintQueue.isEmpty()) {
            return baseInstruction;
        }
        PaintInstruction result = paintQueue.get(0);
        paintQueue.remove(0);
        return result;
    }
    public PaintInstruction[] emptyQueue() {
        if(paintQueue.isEmpty()) {
            PaintInstruction[] result = new PaintInstruction[1];
            result[0] = baseInstruction;
            return result;
        }
        PaintInstruction[] result = new PaintInstruction[paintQueue.size()];
        paintQueue.toArray(result);
        paintQueue.clear();
        //System.out.println(result.size());
        return result;
    }
    public void put(ArrayList<PaintInstruction> instructions) {
        paintQueue.addAll(instructions);
    }
    public void put(PaintInstruction[] instructions) {
        paintQueue.addAll(Arrays.asList(instructions));
    }
    public void setBaseInstruction(PaintInstruction instruction) {
        baseInstruction = instruction;
    }
    public int size() {
        return paintQueue.size();
    }
}
