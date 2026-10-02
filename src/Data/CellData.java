package Data;
import Terrain.*;
public class CellData {
    public String[] mapObjects;
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(String s : mapObjects) {
            builder.append(s).append("\n");
        }
        return builder.toString();
    }
}
