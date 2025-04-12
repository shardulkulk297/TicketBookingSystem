package Main;

import Entity.Event;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class BookingSystem {

    public abstract Event create_event(String event_name, LocalDate event_date, LocalTime event_time, int total_seats, double ticket_price, String event_type, String venue_name);
    public abstract void display_event_details(Event event);
    public abstract double book_tickets(Event event, int num_tickets);
    public abstract void cancel_tickets(Event event, int num_tickets);


}
