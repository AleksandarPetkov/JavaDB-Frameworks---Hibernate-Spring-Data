package L00_JavaOOP_Principles.p06_BirthdayCelebration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        BaseCitizens citizen = null;
        List<String> listBirthday = new ArrayList<>();
        while (true){
            int counter = 0;

            String[] tokens = reader.readLine().split("\\s+");
            if (tokens[0].equals("End")){
                break;
            }

            switch (tokens[0]){
                case "Citizen":
                    citizen = new Citizens(tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]);
                    listBirthday.add(tokens[4]);
                    break;
                case "Pet":
                    citizen = new Pet(tokens[1], tokens[2]);
                    listBirthday.add(tokens[2]);
                    break;
            }
        }

        String checkID = reader.readLine();
        for (String date : listBirthday) {
            if ( date.endsWith(checkID)){
                System.out.println(date);
            }
        }
    }
}
