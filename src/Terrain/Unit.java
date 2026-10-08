package Terrain;

public class Unit extends MapObject {
    String faction;
    public void shareValues(Unit unit) {
        for(String s : values.keySet()) {
            unit.addValue(s, values.get(s));
        }
        unit.name = name;
    }
    public void setFaction(String faction) {
        this.faction = faction;
    }
    public String getFaction() {
        return faction;
    }
}
