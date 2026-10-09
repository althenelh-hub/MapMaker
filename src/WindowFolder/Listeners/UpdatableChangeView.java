package WindowFolder.Listeners;

import WindowFolder.Receiver;

public class UpdatableChangeView implements Receiver<String> {
    ChangeView view;
    public UpdatableChangeView(ChangeView view) {
        this.view = view;
    }
    public void receive(String value) {
        view.valueName = value;
    }
    public ChangeView getChangeView() {
        return view;
    }
}
