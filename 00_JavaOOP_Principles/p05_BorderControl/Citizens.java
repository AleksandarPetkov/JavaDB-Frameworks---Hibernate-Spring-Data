package L00_JavaOOP_Principles.p05_BorderControl;

public class Citizens extends BaseCitizen {
    private int age;

    Citizens(String name, int age, String id) {
        super(name, id);
        this.age = age;
    }
}
