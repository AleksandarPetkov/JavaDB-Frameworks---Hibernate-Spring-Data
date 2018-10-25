package L00_JavaOOP_Principles.p08_Vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(" ");
        double fuel = Double.parseDouble(tokens[1]);
        double consumption = Double.parseDouble(tokens[2]);
        Vehicle car = new Car(fuel, consumption);

        tokens = reader.readLine().split(" ");
        fuel = Double.parseDouble(tokens[1]);
        consumption = Double.parseDouble(tokens[2]);
        Vehicle truck = new Truck(fuel, consumption);

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            tokens = reader.readLine().split(" ");
            if (tokens.length == 3) {
                if (tokens[0].equals("Drive")) {
                    double distance = Double.parseDouble(tokens[2]);
                    if (tokens[1].equals("Car")) {
                        car.drive(distance);
                    } else if (tokens[1].equals("Truck")) {
                        truck.drive(distance);
                    }
                } else if (tokens[0].equals("Refuel")) {
                    double quantity = Double.parseDouble(tokens[2]);
                    if (tokens[1].equals("Car")) {
                        car.refuel(quantity);
                    } else if (tokens[1].equals("Truck")) {
                        truck.refuel(quantity);
                    }
                }
            }
        }
        System.out.println(car);
        System.out.println(truck);
    }
}
