package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Event {
    //Encapsulation
    private String event_name;
    private LocalDate event_date;
    private LocalTime event_time;
    private Venue venue;
    private int available_seats;
    private int total_seats;
    private double ticket_price;
    private String event_type;


    public Event(){
        this.event_name = "";
        this.event_date = null;
        this.event_time = null;
        this.venue =null;
        this.total_seats = 0;
        this.available_seats = 0;
        this.ticket_price = 0.00;
        this.event_type = null;
    }
    //setting values
    public Event(String event_name, LocalDate event_date, LocalTime event_time, Venue venue, int total_seats, int available_seats, double ticket_price, String event_type){
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_time = event_time;
        this.venue = venue;
        this.total_seats = total_seats;
        this.available_seats = available_seats;
        this.ticket_price = ticket_price;
        this.event_type = event_type;
    }

    //getter functions
    public String getEvent_name(){

        return event_name;
    }
    public LocalDate getEvent_date(){

        return event_date;
    }
    public LocalTime getEvent_time(){

        return event_time;
    }
    public Venue getVenue(){

        return venue;
    }

    public int getTotal_seats(){
        return total_seats;
    }
    public int getAvailable_seats(){
        return available_seats;
    }

    public double getTicket_price(){
        return ticket_price;
    }
    public String getEventType(){
        return event_type;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public void setEvent_date(LocalDate event_date) {
        this.event_date = event_date;
    }

    public void setEvent_time(LocalTime event_time) {
        this.event_time = event_time;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public void setTotal_seats(int total_seats) {
        this.total_seats = total_seats;
    }
    public double calculate_total_revenue(){

        double total_revenue = ticket_price * (total_seats - available_seats);
        return total_revenue;
    }

    public int getBookedNoOfTickets(){
        return total_seats - available_seats;
    }

    public int book_tickets(int num_tickets){
        if(num_tickets > available_seats){
            System.out.println("We don't have enough seats");
        }
        if(num_tickets < 0){
            System.out.println("Number must be positive");
        }

        available_seats = available_seats - num_tickets;
        System.out.println("Booked successfully");
        return num_tickets;
    }



    public void cancel_booking(int num_tickets){
        if(num_tickets < 0){
            System.out.println("Number must be positive");
        }

        if(num_tickets > getBookedNoOfTickets()){
            System.out.println("Cannot cancel more than booked no of tickets");
        }
        available_seats = available_seats + num_tickets;
        System.out.println("Booking canceled");
    }
    public abstract void display_event_details();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event Name: ").append(event_name).append("\n");
        sb.append("Event Date: ").append(event_date).append("\n");
        sb.append("Event Time: ").append(event_time).append("\n");
        sb.append("Venue: ").append(venue).append("\n");
        sb.append("Total Seats: ").append(total_seats).append("\n");
        sb.append("Available Seats: ").append(available_seats).append("\n");
        sb.append("Ticket Price: ").append(ticket_price).append("\n");
        sb.append("Event Type: ").append(event_type).append("\n");
        sb.append("Total Revenue: ").append(calculate_total_revenue());
        return sb.toString();
    }

}
