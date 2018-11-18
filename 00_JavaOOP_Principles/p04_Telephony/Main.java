package L00_JavaOOP_Principles.p04_Telephony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Smartphone phone = new Smartphone();
        String[] numbers = reader.readLine().split(" ");
        String[] URLs = reader.readLine().split(" ");

        for (String number : numbers) {
            phone.callPhone(number);
        }
        for (String url : URLs) {
            phone.browseInWeb(url);
        }
    }
}

