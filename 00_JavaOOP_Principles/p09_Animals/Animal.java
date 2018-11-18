package L00_JavaOOP_Principles.p09_Animals;

public abstract class Animal {
    private String name;
    private int age;
    private String gender;

    Animal(String name, int age, String denger) {
        this.name = setName(name);
        this.age = setAge(age);
        this.gender = setGender(denger);
    }

    String setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Invalid input!");
        } else {
            return name;
        }
    }

    int setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Invalid input!");
        } else {
            return age;
        }
    }

    String setGender(String gender) {
        if (gender.isEmpty()) {
            throw new IllegalArgumentException("Invalid input!");
        } else if (gender.equals("Male")) {
            return gender;
        } else if (gender.equals("Female")) {
            return gender;
        } else {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    protected abstract String produceSound();

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    String getGenger() {
        return gender;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(System.lineSeparator())
                .append(String.format("%s %d %s", getName(), getAge(), getGenger())).append(System.lineSeparator())
                .append(produceSound());
        return sb.toString();
    }
}
