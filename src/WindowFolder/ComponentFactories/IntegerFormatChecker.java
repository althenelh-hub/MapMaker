package WindowFolder.ComponentFactories;

public class IntegerFormatChecker implements InputChecker{
    public boolean check(String text) {
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
}
