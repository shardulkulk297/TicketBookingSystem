package Entity;

import java.time.LocalDate;
import java.time.LocalTime;

class Booking
{
    private Event event;
    private double total_cost;

    public Booking() { }

    public Booking(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public double calculate_booking_cost(int num_tickets) {
        total_cost = num_tickets * event.getTicket_price();
        return total_cost;
    }

    public boolean book_tickets(int num_tickets) {
        return event.book_tickets(num_tickets);
    }

    public boolean cancel_booking(int num_tickets) {
        return event.cancel_booking(num_tickets);
    }

    public int getAvailableNoOfTickets() {
        return event.getAvailable_seats();
    }

    public void getEventDetails() {
        event.display_event_details();
    }

    public static void main(String[] args) {
        Event sampleEvent = new Event("Rock Concert", LocalDate.of(2025, 5, 20), LocalTime.of(19, 30), "Stadium Arena", 500, 1000.0, EventType.Concert);
        Venue sampleVenue = new Venue("Stadium Arena", "123 Main St, Cityville");
        Customer sampleCustomer = new Customer("Alice Johnson", "alice@example.com", "1234567890");
        Booking bookingSystem = new Booking(sampleEvent);

        sampleEvent.display_event_details();
        sampleVenue.display_venue_details();
        sampleCustomer.display_customer_details();

        int ticketsToBook = 50;
        if(bookingSystem.book_tickets(ticketsToBook)) {
            System.out.println("Booked " + ticketsToBook + " tickets.");
        } else {
            System.out.println("Booking failed. Not enough tickets available.");
        }

        System.out.println("Available tickets: " + bookingSystem.getAvailableNoOfTickets());
        System.out.println("Booking cost: " + bookingSystem.calculate_booking_cost(ticketsToBook));
        System.out.println("Total revenue: " + sampleEvent.calculate_total_revenue());

        int ticketsToCancel = 10;
        if(bookingSystem.cancel_booking(ticketsToCancel)) {
            System.out.println("Cancelled " + ticketsToCancel + " tickets.");
        } else {
            System.out.println("Cancellation failed.");
        }

        System.out.println("Available tickets after cancellation: " + bookingSystem.getAvailableNoOfTickets());
        bookingSystem.getEventDetails();
    }
}