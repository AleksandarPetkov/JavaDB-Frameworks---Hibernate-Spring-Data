package L00_JavaOOP_Principles.p09_Animals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String currentAnimal = "";
        while (true) {
            String[] line = reader.readLine().split("\\s+");
            if (line[0].equals("Beast!")) {
                break;
            } else if (line[0].equals("Animal")) {
                System.out.println("Not implemented!");
                continue;
            } else if (line[0].isEmpty()) {
                System.out.println("Invalid input!");
                return;
            }

            if (line.length == 1) {
                currentAnimal = line[0];
            } else {
                switch (currentAnimal) {
                    case "Dog":
                        try {
                            Animal dog = new Dog(line[0], Integer.parseInt(line[1]), line[2]);
                            System.out.println(dog.toString());
                        } catch (IllegalArgumentException ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case "Cat":
                        try {
                            Animal cat = new Cat(line[0], Integer.parseInt(line[1]), line[2]);
                            System.out.println(cat.toString());
                        } catch (IllegalArgumentException ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case "Frog":
                        try {
                            Animal frog = new Frog(line[0], Integer.parseInt(line[1]), line[2]);
                            System.out.println(frog.toString());
                        } catch (IllegalArgumentException ex) {
                            System.out.println(ex.getMessage());
                        }

                        break;
                    case "Kitten":
                        try {
                            Animal kitten = new Kitten(line[0], Integer.parseInt(line[1]), line[2]);
                            System.out.println(kitten.toString());
                        } catch (IllegalArgumentException ex) {
                            System.out.println(ex.getMessage());
                        }

                        break;
                    case "Tomcat":
                        try {
                            Animal tomcat = new Tomcat(line[0], Integer.parseInt(line[1]), line[2]);
                            System.out.println(tomcat.toString());
                            break;
                        } catch (IllegalArgumentException ex) {
                            System.out.println(ex.getMessage());
                        }
                    default:
                        System.out.println("Invalid input!");
                        break;

                }
            }
        }

    }
}
