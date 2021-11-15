package reservation;

import java.util.ArrayList;
import java.util.Scanner;
import staffing.*;

public class Airline {

    private static final Airplane[] airplanes = {
            new Airplane("Boeing-777", 300,47, 56),
            new Airplane("Boeing-747", 287, 38, 43),
            new Airplane("Airbus-390", 303, 44, 61)
    };

    private String airlineID;
    private String airlineName;
    private ArrayList<Flight> flights;
    private ArrayList<Employee> employees;

    public Airline() {
        this("", "");
    }

    public Airline(String airlineID, String airlineName) {
        this.airlineID = airlineID;
        this.airlineName = airlineName;
        flights = new ArrayList<Flight>();
        employees = new ArrayList<Employee>();
    }

    public String getAirlineID() {
        return airlineID;
    }

    public void setAirlineID(String airlineID) {
        this.airlineID = airlineID;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void add(Flight flight) {
        this.flights.add(flight);
    }

    public void add(Employee employee) {
        this.employees.add(employee);
    }

    public void crewUp() {
        Scanner input = new Scanner(System.in);

        System.out.println("How many employees are there? ");
        int numOfEmployees = input.nextInt();

        int employeeID, yearsOfService = 0; String employeeName, employeeTitle = ""; double salary = 0.0;
        for(int i = 0; i < numOfEmployees; i++) {
            System.out.println("Enter Employee-" + (i+1) + " Details: ");
            System.out.print("Enter employee ID: ");
            employeeID = input.nextInt();

            /* Clear Input Buffer */
            input.nextLine();

            System.out.print("Enter employee name: ");
            employeeName = input.nextLine();

            System.out.println("Enter employee title: ");
            employeeTitle = input.nextLine();

            System.out.println("Enter the total years of service by employee: ");
            yearsOfService = input.nextInt();

            System.out.println("Enter the salary: ");
            salary = input.nextDouble();

            add(new Employee(employeeID, employeeName, employeeTitle, yearsOfService, salary));
        }
    }
}
