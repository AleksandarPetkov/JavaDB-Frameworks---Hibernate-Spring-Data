package L00_JavaOOP_Principles.p05_BorderControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        BaseCitizens citizen = null;
        List<String> list = new ArrayList<>();
        while (true){
            int counter = 0;

            String[] tokens = reader.readLine().split("\\s+");
            if (tokens[0].equals("End")){
                break;
            }

            if (tokens.length == 3){
                citizen = new Citizens(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
                list.add(tokens[2]);
            } else {
                citizen = new Robots(tokens[0], tokens[1]);
                list.add(tokens[1]);
            }
        }

        String checkID = reader.readLine();
        for (String id : list) {
            if ( id.endsWith(checkID)){
                System.out.println(id);
            }
        }
    }
}
