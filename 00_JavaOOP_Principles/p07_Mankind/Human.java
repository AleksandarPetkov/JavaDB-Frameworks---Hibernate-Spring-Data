package L00_JavaOOP_Principles.p07_Mankind;

public abstract class Human {
    private String firstName;
    private String secondName;

    public Human(String firstName, String secondName) {
        this.firstName = setFirstName(firstName);
        this.secondName = setLastname(secondName);
    }

    String setFirstName(String firstName) {
        char firstLetter = firstName.charAt(0);
        if (Character.isLowerCase(firstLetter)) {
            throw new IllegalArgumentException("Expected upper case letter!Argument: firstName");
        } else if (firstName.length() < 3) {
            throw new IllegalArgumentException("Expected length at least 4 symbols!Argument: firstName");
        } else {
            return firstName;
        }
    }

    String setLastname(String lastName) {
        char firstLetter = lastName.charAt(0);
        if (Character.isLowerCase(firstLetter)) {
            throw new IllegalArgumentException("Expected upper case letter!Argument: lastName");
        } else if (lastName.length() < 3) {
            throw new IllegalArgumentException("Expected length at least 3 symbols!Argument: lastName");
        } else {
            return lastName;
        }
    }

    String getFirstName() {
        return this.firstName;
    }

    String getSecondName() {
        return this.secondName;
    }
}
