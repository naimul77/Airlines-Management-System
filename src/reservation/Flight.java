package reservation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import staffing.*;

public class Flight {
    private Airplane airplane;
    private String flightNum;
    private Date departureTime;
    private Date arrivalTime;
    private String departure;
    private String arrival;
    private int capacity;
    private int seats;
    private ArrayList<Passenger> passengers;
    private ArrayList<Reservation> reservations;
    private ArrayList<Employee> flightCrew;
    private int indicator;

    public Flight() {
        this("", new Date(), new Date(), "", "", 0, 0);
    }

    public Flight(String flightNum, String departure, String arrival, int capacity, int seats) {
        this(flightNum, new Date(), new Date(), departure, arrival, capacity, seats);
    }

    public Flight(String flightNum, Date departureTime, Date arrivalTime, String departure, String arrival, int capacity, int seats) {
        this.flightNum = flightNum;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departure = departure;
        this.arrival = arrival;
        this.capacity = capacity;
        this.seats = seats;
        this.passengers = new ArrayList<Passenger>();
        this.reservations = new ArrayList<Reservation>();
        this.flightCrew = new ArrayList<Employee>();
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /* Current Use: Automation Implementation */
    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    /* Current Use: Automation */
    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    /* Current Use: Automation */
    public void add(Passenger passenger, Reservation reservation) {
        this.reservations.add(reservation);
        this.passengers.add(passenger);
        seats++;
    }

    /* Main Method calls this function to create a specific flight for passengers as demanded */
    public void initialize() {
        // Declare Input Variable
        Scanner input = new Scanner(System.in);

        /* Enter flight number */
        System.out.print("Enter the flight# ");
        this.flightNum = input.nextLine();
        /* Enter flight capacity */
        System.out.print("Enter flight seats capacity: ");
        this.capacity = input.nextInt();

        /* Clear Input Buffer */
        input.nextLine();

        /* Enter departure information */
        System.out.println("\nDeparture Information - ");
        System.out.print("Enter departure airport: ");
        this.departure = input.nextLine();
        System.out.print("Enter the date of departure (MM-DD-YYYY): ");
        String depart = input.nextLine();
        System.out.print("Enter the time of departure (HH:MM): " );
        depart += input.nextLine();
        this.departureTime = new Date(Integer.parseInt(depart.substring(0,2)), Integer.parseInt(depart.substring(3,5)), Integer.parseInt(depart.substring(6,10)), Integer.parseInt(depart.substring(10,12)), Integer.parseInt(depart.substring(13,15)) );

        /* Enter arrival information */
        System.out.println("\nArrival Information - ");
        System.out.print("Enter the arrival airport: ");
        this.arrival = input.nextLine();
        System.out.print("Enter the date of arrival (MM-DD-YYYY): ");
        String arrive = input.nextLine();
        System.out.print("Enter the time for arrival (HH:MM): " );
        arrive += input.nextLine();
        this.arrivalTime = new Date(Integer.parseInt(arrive.substring(0,2)), Integer.parseInt(arrive.substring(3,5)), Integer.parseInt(arrive.substring(6,10)), Integer.parseInt(arrive.substring(10,12)), Integer.parseInt(arrive.substring(13,15)) );

        System.out.println("\nThank you for creating Flight#" + this.flightNum);
    }

    /* Prints out flight details after initializaiton is complete from main method */
    @Override
    public String toString() {
        return "\nFlight#" + this.flightNum + "\nDeparture: " + departure + " at " + departureTime + "\nArrival: " + arrival + " at " + arrivalTime;
    }

    public String printSeats() { return "\nThere are " + this.seats + " seats booked for this flight."; }

    public void printPassengers() {
        System.out.println();
        for(Passenger passenger: this.passengers)
            System.out.println(passenger + "\n");
    }

    public String printCurrentCapacity() { return "There are " + (this.capacity - this.seats) + " seats currently available for the flight."; }

    /* Main Method makes call to this function to reserve seats for passengers */
    public boolean reserve(Passenger passenger) {
        if(this.seats < this.capacity) {

            /* Add the passenger booked for the flight */
            passengers.add(passenger);

            /* Create a flight reservation for the passenger */
            Reservation reservation = new Reservation(passenger, this);

            /* Declare and Initialize an input variable for taking user inputs */
            Scanner input = new Scanner(System.in);

            /* Enter the seat number for the passenger seat including row, aisle, and class */
            System.out.print("Enter the seat#(33D, 12A): ");
            String seat = input.nextLine();
            reservation.setSeat(seat.charAt(2));
            reservation.setSeatRow(Integer.parseInt(seat.substring(0,2)));
            System.out.print("Enter the seat class: ");
            seat = input.nextLine();
            reservation.setSeatClass(seat);

            /* Enter the price of the ticket */
            System.out.print("Enter the ticket price: ");
            double price = input.nextDouble();
            reservation.setPrice(price);

            /* Set the Paid Status for the Flight Ticket */
            System.out.print("Is the ticket already paid for? (Y/N): ");
            char paid = input.next().charAt(0);
            reservation.setPaymentStatus(paid == 'Y');

            /* Set the current date and time as Reservation DateTime */
            Date currentDate = java.util.Calendar.getInstance().getTime();
            reservation.setReservationDate(currentDate);

            reservations.add(reservation);
            this.seats++;
            return true;
        }

        System.out.println("Sorry! The flight is completely booked for reservations. ");
        return false;
    }

    /* Makes any 1 out of 2 branching method calls
       to search for passenger by
       PassengerID or Passenger Name
    */
    public String findPassenger(boolean search, String variable) {
        return "\nSearching for a passenger with " +
                (search ? "ID#" : "name ") +
                variable + "......\nThe passenger is" +
                (search ? findPassengerByID(variable) : findPassengerByName(variable) ) +
                " booked for this flight. ";
    }

    /* Only called by Base Method: findPassenger */
    public String findPassengerByName(String name) {

        int i = 0;
        for(Passenger passenger: this.passengers) {
            if (passenger.getFullName().equals(name) || passenger.getFirstName().equals(name)) {
                this.indicator = i;
                return "";
            }
            i++;
        }

        return " not";
    }

    /* Only called by Base Method: findPassenger() */
    public String findPassengerByID(String id) {

        int i = 0;
        for(Passenger passenger: this.passengers) {
            if (passenger.getPassengerID() == Integer.parseInt(id)) {
                this.indicator = i;
                return "";
            }
            i++;
        }

        return " not";
    }

    /*  Makes any 1 of 3 branching calls
        to the cancellation methods by either
        PassengerID, ReservationID, or Passenger Name
    */
    public short cancelReservation(short choice, String variable) {
        return (choice == 2 ? cancelByPassengerName(variable) : (choice == 1 ? cancelByPassengerID(variable) : cancelByReservationID(variable) ) );
    }

    /* Only called by Base Method: cancelReservation() */
    public short cancelByPassengerID(String id) {

        if(id.length() != 6)
            return 1;

        int passengerID = 0;

        try {
            passengerID = Integer.parseInt(id);
        } catch(NumberFormatException nfe) {
            return 2;
        } finally {
            this.seats--;
            for( Passenger passenger: this.passengers)
                if (passengerID == passenger.getPassengerID())
                    for(Reservation reservation: reservations) /* Cascading Elimination: Remove corresponding flight reservation for the passenger */
                        if (reservation.getPassenger().getPassengerID() == passengerID || reservation.getPassenger().getPassengerID() == passengerID)
                            return (short)(this.reservations.remove(reservation) && this.passengers.remove(passenger) ? 0: 3);
        }

        return increaseSeats();     //Please Refer to Method Description
    }

    /* Only called by Base Method: cancelReservation() */
    public short cancelByPassengerName(String name) {

        for(char alpha: name.toCharArray())
            if((alpha < 'a' || alpha > 'z') && (alpha < 'A' || alpha > 'Z'))
                return 1;

        this.seats--;
        for (Passenger passenger : this.passengers)
            if (passenger.getFullName().contains(name))
                for(Reservation reservation: reservations) /* Cascading Elimination: Remove corresponding flight reservation for the passenger */
                    if(reservation.getPassenger().getFullName().contains(name))
                        return (short)(this.reservations.remove(reservation) && this.passengers.remove(passenger) ? 0 : 3);

        return increaseSeats();     //Please Refer to Method Description
    }

    /* Only called by Base Method: cancelReservation() */
    public short cancelByReservationID(String reservationID) {

        if(reservationID.length() != 11)
            return 1;

        int i = 0;
        for(char alpha = reservationID.charAt(i); i < reservationID.length(); alpha = reservationID.charAt(i++))
            if((i > 5 && i < 8) && (alpha < '0' || alpha > '9') || (alpha < 'a' || alpha > 'z') && (alpha < 'A' || alpha > 'Z'))
                return 1;

        /* Initial assumption of seats increase from a single reservation cancellation */
        this.seats--;
        for (Reservation reservation: this.reservations)
            if (reservationID.equals(reservation.getReservationID()) )
                for(Passenger passenger: this.passengers) /* Cascading Elimination: Remove the corresponding passenger booked for the specific flight reservation */
                    if(passenger.getPassengerID() == reservation.getPassenger().getPassengerID())
                        return (short)(this.passengers.remove(passenger) && this.reservations.remove(reservation) ? 0: 3);

        return increaseSeats();     //Please Refer to Method Description
    }

    /* In case of failure to remove passenger and reservation from list, decrease the number of seats to original state */
    public short increaseSeats() {
        this.seats++;
        System.out.println("Sorry! The passenger is not booked for a reservation for Flight#" + this.flightNum + "\nTher are still " + (capacity - seats) + " available seats. ");
        return 0;
    }

    /* Check if the seat class is above Economy Class for change eligibility */
    public boolean checkChangeability(String variable, short choice) {

        switch(choice) {
            case 1:
                for(Passenger passenger: passengers)
                    if(passenger.getPassengerID() == Integer.parseInt(variable))
                        return true;

                break;

            case 2:
                for(Passenger passenger: passengers)
                    if(passenger.getFullName().contains(variable))
                        return true;

                break;

            case 3:
                for(Reservation reservation: reservations)
                    if(reservation.getReservationID().equals(variable))
                        return true;

                break;

            default:
                System.out.println("Sorry! There is an error in your choice of input!");
        }

        return false;
    }

    public short changeSeat(String variable, String seat, short choice) {

        short error = 2;
        for(Reservation reservation: reservations)
            if(choice == 2 ? reservation.getPassenger().getFullName().contains(variable) : (choice == 1 ? reservation.getPassenger().getPassengerID() == Integer.parseInt(variable) : reservation.getReservationID() == variable) )
                error = reservation.setNewSeat(seat);

        switch(error) {
            case 0:
                System.out.println("Seat change successful for " + (choice == 2 ? "" : "Passenger with "+ (choice == 3 ? "Reservation" : "Passenger") + "ID # " ) + variable + " on Flight#" + this.flightNum);
                break;

            case 1:
                System.out.println("Sorry! You cannot change your seat to a different seat class. You have to upgrade your ticket for that! ");
                break;

            case 2:
                System.out.println("You have selected the same seat for the flight. Are you sure you want to proceed? (Y/N)");

                Scanner input = new Scanner(System.in);
                char option = input.next().charAt(0);

                if(option == 'Y')
                    System.out.println("Thank you!");
                else if(option == 'N')
                    System.out.println("Please try again!");
                else
                    System.out.println("Sorry! You have selected an incorrect option.");

                break;

            default:
                System.out.println("Sorry! The error detected is unidentified. ");
        }

        return error;
    }

    public void upgradeSeat(String variable, String seatClass, short choice) {

        boolean changed = false;
        for(Reservation reservation: reservations)
            if (choice == 2 ? reservation.getPassenger().getFullName().contains(variable) : (choice == 1 ? reservation.getPassenger().getPassengerID() == Integer.parseInt(variable) : reservation.getReservationID() == variable))
                changed = reservation.setNewSeatClass(seatClass);

        System.out.println();

        if(changed)
            System.out.println("Seat Upgrade successful for " + (choice == 2 ? "" : "Passenger with "+ (choice == 3 ? "Reservation" : "Passenger") + "ID # " ) + variable + " on Flight#" + this.flightNum);
        else
            System.out.println("Sorry! Economy class is the most affordable option we have at our airlines at the moment. ");
    }

    public String getItinerary(String reservationID) {
        for(Reservation reservation: reservations)
            if(reservation.getReservationID().equals(reservationID))
                return toString() + reservation;

        return "Not Processed. ";
    }

    public String printDepartureTime() {
        return new SimpleDateFormat("mm/dd/yyyy hh:mm:ss z").format(getDepartureTime());
    }

    public String printArrivalTime() {
        return new SimpleDateFormat("mm/dd/yyyy hh:mm:ss z").format(getArrivalTime());
    }
}

