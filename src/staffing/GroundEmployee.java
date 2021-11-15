package staffing;

public class GroundEmployee extends Employee {
    private String department;

    public GroundEmployee() {
        this("");
    }

    public GroundEmployee(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Ground Employee -\n Department: " + this.department + "\n" + super.toString();
    }
}
