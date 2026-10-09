package WindowFolder.Listeners;

import WindowFolder.ChangeValue;
import WindowFolder.Receiver;

public class UpdatableRemoveFromString implements Receiver<String> {
    RemoveFromString removeString;
    public UpdatableRemoveFromString(RemoveFromString removeString) {
        this.removeString = removeString;
    }
    public void receive(String s) {
        removeString.name = s;
    }
    public RemoveFromString getRemoveString() {
        return removeString;
    }
}
