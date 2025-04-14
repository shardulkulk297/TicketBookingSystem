package Entity;

import Entity.Customer;
import Entity.Event;

import java.time.LocalDate;
import java.util.List;

public class Booking {
    private static int nextBookingId = 1;
    private List<Customer> customers;
    private int bookingId;
    private Event event;
    private int num_tickets;
    private double total_cost;
    private LocalDate booking_date;

    public Booking(){

    }
    public Booking(Event event, List<Customer> customers, int num_tickets, double total_cost, LocalDate booking_date){
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

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public static void setNextBookingId(int nextBookingId) {

        Booking.nextBookingId = nextBookingId;
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

    public void display_booking_details(){

        if(customers.isEmpty()){
            System.out.println("No bookings");
        }
        else{
            System.out.println("Customers: ");
            for(Customer c: customers) {
                System.out.println(c);
            }
        }

        if(event == null){
            System.out.println("No events");
        }
        else{
            System.out.println(event);
        }

        System.out.println("Num Tickets: " + num_tickets);
        System.out.println("TotalCOst: " + total_cost);
        System.out.println("BookingDate: "+ booking_date);

    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if(customers == null || customers.isEmpty()){
            sb.append("No bookings\n");
        } else {
            sb.append("Customers:\n");
            for(Customer c : customers){
                sb.append(c.toString()).append("\n");
            }
        }
        if(event == null){
            sb.append("No events\n");
        } else {
            sb.append(event.toString()).append("\n");
        }
        sb.append("Num Tickets: ").append(num_tickets).append("\n");
        sb.append("Total Cost: ").append(total_cost).append("\n");
        sb.append("BookingDate: ").append(booking_date);
        return sb.toString();
    }










}
