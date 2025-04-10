package main;

import entity.Customer;
import entity.Event;
import entity.Venue;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public abstract class BookingSystem {


    public abstract Event create_event(String event_name, LocalDate event_date, LocalTime event_time, int total_seats, double ticket_price, String event_type, Venue venue);


    public abstract void display_event_details(Event event);


    public abstract double book_tickets(Event event, int num_tickets, List<Customer> customers);


    public abstract void cancel_tickets(Event event, int num_tickets );


    public abstract int getAvailableNoOfTickets(Event event);

    public abstract List<Event> getEventDetails();
}