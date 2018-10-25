package L00_JavaOOP_Principles.p06_BirthdayCelebration;

public abstract class BaseCitizen implements BaseCitizens {
    private String name;

    public BaseCitizen(String name) {
        this.name = name;
    }

     public String getName() {
        return this.name;
    }
}
