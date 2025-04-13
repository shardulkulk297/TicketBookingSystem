package Entity;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;


public class Booking {
    private static int nextBookingId = 1;
    private int bookingId;
    private Event event;
    private Map<String, Customer> customers;
    private int num_tickets;
    private double total_cost;
    private LocalDate booking_date;

    public Booking(){
        this.bookingId = nextBookingId++;
        this.customers = new HashMap<>();
    }
    public Booking(Event event, Map<String, Customer> customers, int num_tickets, double total_cost, LocalDate booking_date){
        this.bookingId = nextBookingId++;
        this.event = event;
        this.customers = customers;
        this.num_tickets = num_tickets;
        this.total_cost = total_cost;
        this.booking_date = booking_date;
    }


    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }


    public void setCustomers(Map<String, Customer> customers) {
        this.customers = customers;
    }


    public static void setNextBookingId(int nextBookingId) {
        Booking.nextBookingId = nextBookingId;
    }

    public int getBookingId() {
        return bookingId;
    }


    public void setBooking_date(
            LocalDate booking_date) {
        this.booking_date = booking_date;
    }


    public void setEvent(Event event) {
        this.event = event;
    }


    public void setNum_tickets(int num_tickets) {
        this.num_tickets = num_tickets;
    }


    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public Event getEvent() {
        return event;
    }

    public int getNum_tickets() {
        return num_tickets;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public LocalDate getBooking_date() {
        return booking_date;
    }

    public Map<String, Customer> getCustomers() {
        return customers;
    }


    public void display_booking_details(){

        if(customers == null || customers.isEmpty()){
            System.out.println("No customers for this booking.");
        }
        else{
            System.out.println("Customers: ");
            for(Customer c: customers.values()) {
                System.out.println(c);
            }
        }

        if(event == null){
            System.out.println("No event associated with this booking.");
        }
        else{
            System.out.println(event);
        }

        System.out.println("Num Tickets: " + num_tickets);
        System.out.println("Total Cost: " + total_cost);
        System.out.println("Booking Date: "+ booking_date);

    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Booking ID: ").append(bookingId).append("\n");
        if(customers == null || customers.isEmpty()){
            sb.append("No Customers\n");
        } else {
            sb.append("Customers:\n");
            for(Customer c : customers.values()){
                sb.append(c.toString()).append("\n");
            }
        }
        if(event == null){
            sb.append("No Event\n");
        } else {
            sb.append(event.toString()).append("\n");
        }
        sb.append("Num Tickets: ").append(num_tickets).append("\n");
        sb.append("Total Cost: ").append(total_cost).append("\n");
        sb.append("Booking Date: ").append(booking_date);
        return sb.toString();
    }
}