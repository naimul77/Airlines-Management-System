package reservation;

import java.util.Scanner;

public class Passenger {
    private int passengerID;
    private String firstName;

    private String lastName;
    private int age;
    private char gender;

    public Passenger() {
        this(-1, "", "", 0, ' ');
    }

    public Passenger(int passengerID, String firstName, String lastName, int age, char gender) {
        this.passengerID = passengerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    public int getPassengerID() {
        return this.passengerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String toString() {
        return "Passenger ID: " + this.passengerID +
                "\nPassenger Name: " + this.firstName + " " + this.lastName +
                "\nAge: " + this.age +
                "\nGender: " + this.gender;
    }

    public void initialize() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the passenger ID: ");
        this.passengerID = input.nextInt();
        input.nextLine();
        System.out.print("Enter the first name: ");
        this.firstName = input.nextLine();
        System.out.print("Enter the last name: ");
        this.lastName = input.nextLine();
        System.out.print("Enter the age: ");
        this.age = input.nextInt();
        input.nextLine();
        System.out.print("Enter the gender (M/F): ");
        this.gender = input.next().charAt(0);
    }
}
