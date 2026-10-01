package WindowFolder;
import java.util.regex.*;
public class Test {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher matcher = pattern.matcher("!ffsdfsd");
        if(!matcher.find()) {
            System.out.println("matches");
        }
    }
}
