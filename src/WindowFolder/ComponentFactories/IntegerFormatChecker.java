package WindowFolder.ComponentFactories;

public class IntegerFormatChecker implements InputChecker{
    public CheckMessage check(String text) {
        CheckMessage message = new CheckMessage(true, "");
        if (text.charAt(0) < 49 && text.length()>1 || text.charAt(0) > 58) {
            System.out.println("Wrong first");
            message.setValidation(false);
            message.setError("Wrong first digit.");
            return message;
        }
        for(int i = 0;i < text.length();i++) {
            if(text.charAt(i) < 47 && text.charAt(i) > 58) {
                System.out.println("Wrong format");
                message.setValidation(false);
                message.setError("Wrong number format.");
                return message;
            }
        }
        return message;
    }
}
