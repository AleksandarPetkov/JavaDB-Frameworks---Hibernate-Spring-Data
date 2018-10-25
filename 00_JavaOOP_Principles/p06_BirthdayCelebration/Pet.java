package L00_JavaOOP_Principles.p06_BirthdayCelebration;

public class Pet extends BaseCitizen {
    private String birhDay;

    public Pet(String name, String birhDay) {
        super(name);
        this.birhDay = birhDay;
    }

    public String getBirhDay() {
        return birhDay;
    }
}
