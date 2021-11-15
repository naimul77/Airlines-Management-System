package staffing;

public class Employee {
    private int employeeID;
    private String employeeName;
    private String employeeTitle;
    private int yearsOfService;
    private double salary;

    public Employee() {
        this(0, "", "", 0, 0.0);
    }

    public Employee(int employeeID, String employeeName, String employeeTitle, int yearsOfService, double salary) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeeTitle = employeeTitle;
        this.yearsOfService = yearsOfService;
        this.salary = salary;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeTitle() {
        return employeeTitle;
    }

    public void setEmployeeTitle(String employeeTitle) {
        this.employeeTitle = employeeTitle;
    }

    public int getYearsOfService() {
        return yearsOfService;
    }

    public void setYearsOfService(int yearsOfService) {
        this.yearsOfService = yearsOfService;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return " EmployeeID: " + this.employeeID + "\n Employee Name: " + this.employeeName +
                "\n Title: " + this.employeeTitle + "\n Years of Service: " + this.yearsOfService + "\n Salary: " + this.salary;
    }
}

