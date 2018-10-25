package L00_JavaOOP_Principles.p01_defineAnInterfacePerson;

public class Citizen implements Person {
    private String name;
    private int age;

    protected Citizen(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public String getName(){
        return this.name;
    }

}
