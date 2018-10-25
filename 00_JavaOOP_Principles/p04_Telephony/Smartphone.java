package L00_JavaOOP_Principles.p04_Telephony;

public class Smartphone implements Callable, Browsable{
    @Override
    public void browseInWeb(String URL) {
        for (int i = 0; i < URL.length(); i++) {
            if (Character.isDigit(URL.charAt(i))) {
                System.out.println("Invalid URL!");
                return;
            }
        }
        System.out.printf("Browsing: %s!%n", URL);
    }

    @Override
    public void callPhone(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                System.out.println("Invalid number!");
                return;
            }
        }
        System.out.printf("Calling... %s%n", number);
    }
}
