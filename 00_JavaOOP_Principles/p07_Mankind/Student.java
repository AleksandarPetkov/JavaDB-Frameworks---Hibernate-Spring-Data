package L00_JavaOOP_Principles.p07_Mankind;

class Student extends Human {
    private String facultyNumber;

    Student(String firstName, String secondName, String facultyNumber) {
        super(firstName, secondName);
        this.facultyNumber = setFacultyNumber(facultyNumber);
    }

    String setFacultyNumber(String facultyNumber) {
        if (facultyNumber.length() < 5 || facultyNumber.length() > 10) {
            throw new IllegalArgumentException("Invalid faculty number!");
        } else {
            return facultyNumber;
        }
    }

    String getFacultyNumber() {
        return this.facultyNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("First Name: %s%n", getFirstName()))
                .append(String.format("Last Name: %s%n", getSecondName()))
                .append(String.format("Faculty number: %s%n", getFacultyNumber()));
        return sb.toString();
    }
}
