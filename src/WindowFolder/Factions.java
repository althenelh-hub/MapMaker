package WindowFolder;
import java.util.*;
public class Factions {
    ArrayList<String> factionNames;
    public Factions() {
        factionNames = new ArrayList<String>();
    }
    public String[] getFactionNames() {
        String[] result = new String[factionNames.size()];
        factionNames.toArray(result);
        return result;
    }
}
