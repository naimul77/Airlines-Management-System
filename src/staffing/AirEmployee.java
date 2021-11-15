package staffing;

public class AirEmployee extends Employee {
    private boolean localBase;
    private String base;
    private double flightHours;
    private double wage;

    public AirEmployee() {
        this(true, "", 0, 0.0);
    }

    public AirEmployee(boolean localBase, String base, double flightHours, double wage) {
        this.localBase = localBase;
        this.base = base;
        this.flightHours = flightHours;
        this.wage = wage;
    }

    public boolean isLocalBase() {
        return localBase;
    }

    public void setLocalBase(boolean localBase) {
        this.localBase = localBase;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public double getFlightHours() {
        return flightHours;
    }

    public void setFlightHours(double flightHours) {
        this.flightHours = flightHours;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    @Override
    public String toString() {
        return "Air Crew Member -\n" + super.toString() + "\n " + (localBase ? "Local": "Foreign") + " Base: " + this.base + "\n Total flight hours: " + this.flightHours + "\n Hourly Wage: " + this.wage;
    }
}

