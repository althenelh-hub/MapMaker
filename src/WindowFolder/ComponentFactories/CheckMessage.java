package WindowFolder.ComponentFactories;

public class CheckMessage {
    boolean isValid;
    String inputError;
    public CheckMessage(boolean isValid, String inputError) {
        this.isValid = isValid;
        this.inputError = inputError;
    }
    public void setValidation(boolean validation) {
        isValid = validation;
    }
    public void setError(String error) {
        this.inputError = error;
    }

    public boolean isValid() {
        return isValid;
    }
    public String getError() {
        return inputError;
    }
}
