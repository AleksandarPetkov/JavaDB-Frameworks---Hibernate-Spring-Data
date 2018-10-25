package L00_JavaOOP_Principles.p09_Animals;

public class Tomcat extends Animal {

    Tomcat(String name, int age, String denger) {
        super(name, age, denger);
    }

    @Override
    protected String produceSound() {
        return "Give me one million b***h";
    }
}
