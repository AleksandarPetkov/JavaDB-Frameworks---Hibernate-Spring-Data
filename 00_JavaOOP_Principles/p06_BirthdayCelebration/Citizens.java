package L00_JavaOOP_Principles.p06_BirthdayCelebration;

public class Citizens extends BaseCitizen {
    private int age;
    private String id;
    private String birthDay;

    public Citizens(String name, int age, String id, String birthDay) {
        super(name);
        this.age = age;
        this.id = id;
        this.birthDay = birthDay;
    }

    public String getBirthDay() {
        return birthDay;
    }
}
