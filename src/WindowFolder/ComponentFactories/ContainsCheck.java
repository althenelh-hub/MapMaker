package WindowFolder.ComponentFactories;

import Data.Characteristics.NamedStorage;
import Data.InfoModifier;

public class ContainsCheck implements InputChecker {
    NamedStorage modifier;
    public ContainsCheck(NamedStorage modifier) {
        this.modifier = modifier;
    }
    public CheckMessage check(String value) {
        CheckMessage message = new CheckMessage(true, "");
        if(modifier.contains(value)) {
            message.setValidation(false);
            message.setError("Name: " + value +" is already exists.");
        }
        return message;
    }
}
