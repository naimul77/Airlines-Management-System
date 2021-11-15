package main;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;
import reservation.*;
import staffing.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print(java.util.Calendar.getInstance().getTime() + "\nWelcome to the Airline Reservation System!\n\nWould you like to automate data entry? (Y/N): ");
        char automate = input.next().toUpperCase().charAt(0);

        Airline airline = new Airline("AA", "American Airlines");
        Flight flight = null;

        if(automate == 'Y') {   /* Automation */

            /* Adding Flight-1 */
            flight = new Flight("EK271", new Date(), new Date(), "ORL", "SJC", 108, 0);

            Passenger[] passengers = new Passenger[3];
            Reservation[] reservations = new Reservation[3];

            passengers[0] = new Passenger(113072, "Naimul", "Aziz", 26, 'M');
            reservations[0] = new Reservation("113072EK271", passengers[0], flight, 38, 'D', "Economy", 168.32, true, new Date());

            passengers[1] = new Passenger(113681, "William", "Shakespeare", 25, 'M');
            reservations[1] = new Reservation("113681EK271", passengers[1], flight, 34, 'C', "Main", 203.41, true, new Date());

            passengers[2] = new Passenger(110004, "Some", "One", 40, 'F');
            reservations[2] = new Reservation("110004EK271", passengers[2], flight, 11, 'A', "Business", 462.03, false, new Date());

            for (int i = 0; i < 3; i++)
                flight.add(passengers[i], reservations[i]);

            airline.add(flight);

            /* Add Flight-2 */
            flight = new Flight("EK742", new Date(), new Date(), "MIA", "SFO", 131, 0);

            passengers = new Passenger[3];
            reservations = new Reservation[3];

            passengers[0] = new Passenger(104127, "Who", "Ever", 35, 'F');
            reservations[0] = new Reservation("104127EK742", passengers[0], flight, 26, 'B', "Main", 171.49, true, new Date());

            passengers[1] = new Passenger(104636, "Any", "One", 21, 'M');
            reservations[1] = new Reservation("104681EK742", passengers[1], flight, 18, 'B', "Business", 399.90, true, new Date());

            passengers[2] = new Passenger(104004, "Every", "One", 46, 'M');
            reservations[2] = new Reservation("104004EK742", passengers[2], flight, 18, 'A', "Preferred Main", 262.41, true, new Date());

            for (int i = 0; i < 3; i++)
                flight.add(passengers[i], reservations[i]);

            airline.add(flight);

            airline.add(new Employee(44172, "Big Mac", "Flight Attendant", 7, 77000.80));
            airline.add(new Employee( 40041, "Auto-Pilot", "Co-Pilot", 10, 128000.50));
            airline.add(new Employee(43312, "Mr. Airplane", "Flight Dispatcher", 8, 89000.00));

        } else {    /* Manual Data Entry */

            System.out.println("\nEnter Flight Details -");
            flight = new Flight();
            flight.initialize();

            System.out.print("\nHow many passengers do you want to reserve tickets for? ");
            int passengers = input.nextInt();

            /* Enter All Passengers Information */
            System.out.println("\nEnter All Passenger Information");
            Passenger passenger;
            for (int count = 1; count <= passengers; count++) {
                passenger = new Passenger();
                System.out.println("\nPassenger#" + count);
                passenger.initialize();
                flight.reserve(passenger);
            }

            airline.crewUp();
        }

        System.out.println("\n\nWhat would you like to do? ");
        System.out.println("   (1) Search for a passenger\n   (2) Cancel a reservation\n   (3) Modify a reservation\n   (4) Display all flight passengers\n   (5) Generate Itinerary");
        System.out.print("Enter your choice: ");
        short choice = input.nextShort();

        /* Clear Input Buffer */
        input.nextLine();

        String variable = "";
        switch(choice) {
            case 1:
                System.out.println("\nSearch by: (1) Passenger ID (2) Passenger Name ");
                System.out.print("Select search variable (1/2): ");
                while(true) {
                    try {
                        choice = input.nextShort();
                    } catch(InputMismatchException imex) {
                        System.out.println("\nSorry! Your choice of selection is invalid. ");
                        System.out.println("Please try again. Select a number from [1, 2]");
                        System.out.println("Select search variable (1/2): ");

                        break;
                    }
                }
                /* Clear Input Buffer */
                input.nextLine();

                System.out.print("\nEnter passenger " + (choice == 1 ? "ID" : "name") + ": ");
                variable = input.nextLine();

                System.out.println(flight.findPassenger(choice == 1, variable));

                break;

            case 2:
                System.out.println("\nCancel reservation by: \n   (1) Passenger ID \n   (2) Passenger Name \n   (3) Reservation ID ");
                System.out.print("Enter your choice: ");
                while(true) {
                    try {
                        choice = input.nextShort();
                    } catch(InputMismatchException imex) {
                        System.out.println("\nSorry! Your choice of input is invalid.");
                        System.out.println("Please try again. Select a number from [1, 2, 3]");
                        System.out.println("Enter your choice: ");

                        break;
                    }
                }
                /* Clear Input Buffer */
                input.nextLine();

                System.out.print("\nEnter the " + (choice == 3 ? "Reservation" : "Passenger") + " " + (choice == 2 ? "Name" : "ID") + ": ");
                variable = input.nextLine();

//                if(flight.cancelReservation(choice, variable))
//                    System.out.println("\n" + (choice == 2 ? "" : "Passenger with "+ (choice == 3 ? "Reservation" : "Passenger") + "ID # " ) + variable + " has successfully cancelled their reservation from Flight#" + flight.getFlightNum());

                for(short error = flight.cancelReservation(choice, variable); error != 0; error = flight.cancelReservation(choice, variable)) {
                    System.out.println("\nSorry! Your input for " + (choice == 3 ? "Reservation" : "Passenger") + " " + (choice == 2 ? "Name" : "ID") + " is invalid. ");

                    if (error == 3) {
                        System.out.println("Internal Programmatic Error! issue with identification in array list of objects. ");
                        System.out.println("Please fix the issue in the code and try running the program again. ");
                        System.exit(0);
                    }
                    else {
                        switch (choice) {
                            case 1:
                                switch(error) {
                                    case 1:
                                        System.out.println("Your passenger ID cannot contain more than 6 digits. ");
                                        break;

                                    case 2:
                                        System.out.println("Your passenger ID can only contain numeric characters from [0,9].");
                                        break;

                                    default: System.out.println("Unknown error detection.");
                                }
                                System.out.println("Please try again.\nEnter a 6-digit passenger ID: ");
                                break;

                            case 2:
                                System.out.println("Sorry! Your name contain only contain english alphabets from a-z OR A-Z");
                                System.out.println("Please enter a name with alphabets only: ");

                                break;

                            case 3:
                                System.out.printf("Please enter a 11-character reservation identification key which is a 6-digit number followed by 2 alphabets and a 3-digit code: ");

                                break;

                            default:
                                System.out.println("Sorry! The choice is invalid for any further execution. Unexpected Error.");
                        }
                    }
                }

                break;

            case 3:
                System.out.println("\nSearch by: (1) Passenger ID (2) Passenger Name (3) Reservation ID");
                System.out.print("Enter your choice: ");
                choice = input.nextShort();

                /* Clear the Input Buffer */
                input.nextLine();

                System.out.print("\nEnter the " + (choice == 3 ? "Reservation" : "Passenger") + " " + (choice == 2 ? "Name" : "ID") + ": ");
                variable = input.nextLine();

                System.out.print("\nSelect one of the options: ");
                System.out.println("\n   (1) Change Seats\n   (2) Upgrade Seats\n   (3) Change Flight");
                System.out.print("Enter your choice: ");
                short modification = input.nextShort();

                /* Clear the Input Buffer */
                input.nextLine();

                System.out.println();

                switch(modification) {
                    case 1:
                        if (flight.checkChangeability(variable, choice)) {
                            System.out.print("Enter the new seat (33A, 14C): ");
                            String seat = input.nextLine();
                            flight.changeSeat(variable, seat, choice);
                        } else {
                            System.out.println("Sorry! There is no reservation booked under the " + (choice == 3 ? "Reservation" : "Passenger") + " " + (choice == 2 ? "Name" : "ID") + ": " + variable);
                        }
                        break;

                    case 2:
                        System.out.println("Which Seat Class would you like to upgrade to?");
                        System.out.println("() Main () Preferred Seat () Comfort () Business () First Class");
                        System.out.print("Enter your choice: ");
                        String seatClass = input.nextLine();

                        if(seatClass.equals("Economy"))
                            System.out.println("Sorry! Economy class seats are the most affordable options available onboard. ");
                        else
                            flight.upgradeSeat(variable, seatClass, choice);

                        break;

                    case 3:
                        System.out.println("Airline Services require you to contact directly to change flights due to high flight demand during Halloween season. ");
                        break;

                    default: System.out.println("Sorry, Your input of choice selection is invalid.\nPlease try again.");
                }

                break;

            case 4:
                System.out.println(flight);
                System.out.println(flight.printSeats());
                flight.printPassengers();
                flight.printCurrentCapacity();

                break;

            case 5:

                System.out.print("\nEnter the reservationID# ");
                variable = input.nextLine();

                System.out.println(flight.getItinerary(variable));

                break;

            default:
                System.out.println("Sorry! There is an error in your choice of input.\nPlease try again");
        }

        System.out.println("\nThank you for running this program!");
    }
}
