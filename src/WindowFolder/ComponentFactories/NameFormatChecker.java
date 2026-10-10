package WindowFolder.ComponentFactories;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameFormatChecker implements InputChecker {
    public boolean check(String text) {
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher matcher = pattern.matcher(text);
        return !matcher.find();
    }
}
