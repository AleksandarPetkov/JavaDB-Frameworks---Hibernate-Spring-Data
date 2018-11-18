package L00_JavaOOP_Principles.p03_Ferrari;

public class Ferrari implements Car {
    private static final String MOST_POPULAR_MODEL = "488-Spider";

    private String model;
    private String driverName;

    Ferrari(String driverName) {
        this.model = MOST_POPULAR_MODEL;
        this.driverName = driverName;
    }

    public String getModel() {
        return this.model;
    }

    public String getDriverName() {
        return this.driverName;
    }


    @Override
    public String useBrakes() {
        return "Brakes!";
    }

    @Override
    public String pushTheGasPedal() {
        return "Zadu6avam sA!";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getModel()).append("/")
                .append(useBrakes()).append("/")
                .append(pushTheGasPedal()).append("/")
                .append(getDriverName());
        return sb.toString();
    }
}
