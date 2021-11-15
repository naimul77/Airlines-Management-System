package reservation;

import java.util.Date;
import java.util.Random;

public class Reservation {
    private String reservationID;
    private Passenger passenger;
    private Flight flight;
    private int seatRow;
    private char seat;
    private String seatClass;
    private double price;
    private boolean paymentStatus;
    private Date reservationDate;

    public Reservation() {
        this("", null, null, 0, ' ', "", 0.0, false, java.util.Calendar.getInstance().getTime());
    }

    public Reservation(Passenger passenger, Flight flight) {
        /* To be Implemented: Perform Caesar's Cypher to retrieve ReservationID */
        this.reservationID = Integer.toString(passenger.getPassengerID()) + flight.getFlightNum();
        this.passenger = passenger;
        this.flight = flight;
    }

    public Reservation(String reservationID, Passenger passenger, Flight flight, int seatRow, char seat, String seatClass, double price, boolean paymentStatus, Date date) {
        this.reservationID = reservationID;
        this.passenger = passenger;
        this.flight = flight;
        this.seatRow = seatRow;
        this.seat = seat;
        this.seatClass = seatClass;
        this.price = price;
        this.paymentStatus = paymentStatus;
    }

    public String getReservationID() {
        return this.reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public Passenger getPassenger() {
        return this.passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public char getSeat() {
        return seat;
    }

    public void setSeat(char seat) {
        this.seat = seat;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPaid() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getReservationDate() {
        return this.reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public short setNewSeat(String seat) {
        if((Integer.toString(seatRow) + this.seat).equals(seat)) {
            System.out.println("Sorry! You have selected the same seat again!");
            return 2;
        }

        // Run the Seat Class Distinction Test to Validate Selected Seat Change
        //checkClassDistinction(seat);

        this.seat = seat.charAt(2);
        this.seatRow = Integer.parseInt(seat.substring(0,2));

        return 0;
    }

    public boolean checkClassDistinction(String seat) {
        if(seat == "Economy")
            return true;

        return false;
    }

    public boolean setNewSeatClass(String seatClass) {
        if(this.seatClass == seatClass)
            return false;

        Random random = new Random();
        double change = 5.0 + (double)random.nextInt(27) / 100.0;

        price *= ( (double) change / 100.0 + 1.18);

        this.seatClass = seatClass;

        switch(seatClass) {
            case "Main":
                this.seatRow = 59 + random.nextInt(30);
                break;

            case "Preferred Seat":
                this.seatRow = 18 + random.nextInt(17);
                change += 1.11;
                break;

            case "Comfort":
                this.seatRow = 46 + random.nextInt(12);
                change += 1.32;
                break;

            case "Business":
                this.seatRow = 9 + random.nextInt(8);
                change *= 1.58;
                break;

            case "First Class":
                this.seatRow = random.nextInt(9);
                change *= 1.71;
                break;

            default:
                System.out.println("Sorry! There is no such seat class as you have specified as " + seatClass + ".\nPlease try again.\n");
        }

        price *= change;
        return true;
    }

    public String customString() {
        return "Reservation ID: " + this.reservationID +
                "\nReservation Date: " + this.reservationDate +
                "\nPassenger Name: " + this.passenger.getFullName() + this.flight +
                "\nSeat# " + this.seatRow + this.seat + "\tClass: " + this.seatClass +
                "\nPrice: " + this.price + "\nPayment: " + (this.paymentStatus ? "Complete" : "Pending");
    }

    @Override
    public String toString() {
        return "\nReservation ID: " + this.reservationID +
                "\nReservation Date: " + this.reservationDate + "\n" + this.passenger +
                "\nSeat# " + this.seatRow + this.seat + "\tClass: " + this.seatClass +
                "\nPrice: " + this.price + "\nPayment: " + (this.paymentStatus ? "Complete" : "Pending");
    }
}

