package L00_JavaOOP_Principles.p02_multipleImplementation;

public class Citizen implements Person, Birthable, Identifiable {
    private String name;
    private int age;
    private String id;
    private String birthdate;

    protected Citizen(String name, int age, String id, String birthdate) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthdate = birthdate;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public String getBirthdate() {
        return this.birthdate;
    }
}
