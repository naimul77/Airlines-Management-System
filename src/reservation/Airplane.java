package reservation;

public class Airplane implements Maintainable {

    private String brand;
    private int model;
    private int totalSeats;
    private int crewUpperBound;
    private int crewLowerBound;

    public Airplane() {
        this("", 0);
    }

    public Airplane(String brand, int model) { this(brand, model, 0, 0, 0); }

    public Airplane(String airplane) {
        this(airplane.substring(0, airplane.length() - 4), Integer.parseInt(airplane.substring(airplane.length() - 3, airplane.length())), 0, 0, 0);
    }

    public Airplane(String airplane, int seats, int crewUpperBound, int crewLowerBound) {
        this.brand = airplane.substring(0, airplane.length() - 4);
        this.model = Integer.parseInt(airplane.substring(airplane.length() - 3, airplane.length()));
        this.totalSeats = seats;
        this.crewLowerBound = crewLowerBound;
        this.crewUpperBound = crewUpperBound;
    }

    public Airplane(String brand, int model, int seats, int crewUpperBound, int crewLowerBound) {
        this.brand = brand;
        this.model = model;
        this.totalSeats = seats;
        this.crewUpperBound = crewUpperBound;
        this.crewLowerBound = crewLowerBound;
    }

    public String getBrand() { return this.brand; }

    public void setBrand(String brand) { this.brand = brand; }

    public int getModel() { return this.model; }

    public void setModel(int model) { this.model = model; }

    public int getTotalSeats() { return this.totalSeats; }

    public void setTotalSeats(int seats) { this.totalSeats = seats; }

    public int getCrewUpperBound() { return this.crewUpperBound; }

    public void setCrewUpperBound(int crewUpperBound) { this.crewUpperBound = crewUpperBound; }

    public int getCrewLowerBound() { return this.crewLowerBound; }

    public void setCrewLowerBound(int crewLowerBound) { this.crewLowerBound = crewLowerBound; }

    @Override
    public String toString() {
        return "Airplane: " + this.brand + this.model +
                "\nNumber of Seats: " + this.totalSeats +
                "\nMinimum Crew Members Required: " + this.crewLowerBound +
                "\nMaximum crew members allowed on flight: " + this.crewUpperBound;
    }

    public void cleanup() {
        System.out.println("Airplane interior cleaning and exterior washinng methodology. ");
    }

    public void refuel() {
        System.out.println("Refuelling airplane tank. ");
    }
}

