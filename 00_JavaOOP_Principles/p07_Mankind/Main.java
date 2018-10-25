package L00_JavaOOP_Principles.p07_Mankind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String[] studentsInfo = reader.readLine().split("\\s+");
            Human student = new Student(studentsInfo[0], studentsInfo[1], studentsInfo[2]);
            System.out.println(student.toString());
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            return;
        }


        try {
            String[] workerInfo = reader.readLine().split("\\s+");
            Human worker = new Worker(workerInfo[0], workerInfo[1],
                    Double.parseDouble(workerInfo[2]), Double.parseDouble(workerInfo[3]));
            System.out.println(worker.toString());
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            return;
        }

    }
}
