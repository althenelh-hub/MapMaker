package WindowFolder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatChecker {
    public boolean checkOnDigits(String text) {
        if (text.charAt(0) < 49 && text.length()>1 || text.charAt(0) > 58) {
            System.out.println("Wrong first");
            return false;
        }
        for(int i = 0;i < text.length();i++) {
            if(text.charAt(i) < 47 && text.charAt(i) > 58) {
                System.out.println("Wrong format");
                return false;
            }
        }
        return true;
    }
    public boolean checkPureLetters(String text) {
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher matcher = pattern.matcher(text);
        return !matcher.find();
    }
}
