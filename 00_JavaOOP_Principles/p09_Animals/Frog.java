package L00_JavaOOP_Principles.p09_Animals;

public class Frog extends Animal {

    Frog(String name, int age, String denger) {
        super(name, age, denger);
    }

    @Override
    protected String produceSound() {
        return "Frogggg";
    }
}
