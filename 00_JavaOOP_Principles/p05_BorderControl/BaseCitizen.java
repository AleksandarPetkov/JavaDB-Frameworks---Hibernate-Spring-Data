package L00_JavaOOP_Principles.p05_BorderControl;

public abstract class BaseCitizen implements BaseCitizens{
    private String id;
    private String name;

    BaseCitizen(String name, String id) {
        this.id = id;
        this.name = name;
    }

     public String getId() {
        return this.id;
    }

     public String getName() {
        return this.name;
    }
}
