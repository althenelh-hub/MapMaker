package WindowFolder.ComponentFactories;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameFormatChecker implements InputChecker {
    public CheckMessage check(String text) {
        CheckMessage message = new CheckMessage(true, "");
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()) {
            message.setValidation(false);
            message.setError("Wrong name format");
        }
        return message;
    }
}
