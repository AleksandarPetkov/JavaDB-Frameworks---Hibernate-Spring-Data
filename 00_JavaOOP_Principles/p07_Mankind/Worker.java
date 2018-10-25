package L00_JavaOOP_Principles.p07_Mankind;

public class Worker extends Human {
    private double salary;
    private double hourPerDay;

    public Worker(String firstName, String secondName, double salary, double hourPerDay) {
        super(firstName, secondName);
        this.salary = setSalary(salary);
        this.hourPerDay = setHourPerDay(hourPerDay);
    }

    double setSalary(double salary) {
        if (salary < 10) {
            throw new IllegalArgumentException("Expected value mismatch!Argument: weekSalary");
        } else {
            return salary;
        }
    }

    double setHourPerDay(double hour) {
        if (hour < 1 || hour > 12) {
            throw new IllegalArgumentException("Expected value mismatch!Argument: workHoursPerDay");
        } else {
            return hour;
        }
    }

    @Override
    String setLastname(String lastName){
        char firstLetter = lastName.charAt(0);
        if (Character.isLowerCase(firstLetter)) {
            throw new IllegalArgumentException("Expected upper case letter!Argument: lastName");
        } else if (lastName.length() < 3) {
            throw new IllegalArgumentException("Expected length more than 3 symbols!Argument: lastName");
        }else {
            return lastName;
        }
    }


    double getSalaryPerHour() {
        return (this.salary / 7) / this.hourPerDay;
    }

    double getSalary() {
        return this.salary;
    }

    double getHourPerDay() {
        return this.hourPerDay;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("First Name: %s%n",getFirstName()))
                .append(String.format("Last Name: %s%n", getSecondName()))
                .append(String.format("Week Salary: %.2f%n",getSalary()))
                .append(String.format("Hours per day: %.2f%n",getHourPerDay()))
                .append(String.format("Salary per hour: %.2f%n",getSalaryPerHour()));
        return sb.toString();
    }
}
