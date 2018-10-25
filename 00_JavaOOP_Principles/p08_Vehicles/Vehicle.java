package L00_JavaOOP_Principles.p08_Vehicles;

import java.text.DecimalFormat;

public abstract class Vehicle {
    double fuelQuantity;
    double fuelConsumption;

    protected Vehicle(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
    }

    protected void drive(double kilometers) {
        double neededFuel = kilometers * this.fuelConsumption;
        if (neededFuel > this.fuelQuantity) {
            System.out.printf("%s needs refueling%n", getClass().getSimpleName());
            return;
        }
        DecimalFormat df = new DecimalFormat("#.#############");
        System.out.printf("%s travelled %s km%n", getClass().getSimpleName(), df.format(kilometers));
        this.fuelQuantity -= neededFuel;
    }

    protected void refuel(double fuel) {
        if (fuel >= 0) {
            this.fuelQuantity += fuel;
        }
    }

    protected double getFuelQuantity() {
        return this.fuelQuantity;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    protected double getFuelConsumption() {
        return fuelConsumption;
    }

    protected void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", getClass().getSimpleName(), this.fuelQuantity);
    }
}
