package WindowFolder.ComponentFactories;

public class ConfirmationFieldFactory implements ComponentFactory<ConfirmationField> {

    @Override
    public ConfirmationField create(String name) {
        return new ConfirmationField(ColorManager.getColor("Confirm"),
                ColorManager.getColor("Change"), name);
    }
}
