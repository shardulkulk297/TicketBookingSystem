package repository;

import bean.Booking;
import bean.Customer;
import bean.Event;
import bean.Venue;
import exception.EventNotFoundException;
import exception.InvalidBookingException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IBookingSystemRepository {

    public Event create_event(String event_name, LocalDate event_date, LocalTime event_time, int total_seats, double ticket_price, String event_type, Venue venue) throws EventNotFoundException;
    public void getEventDetails(Event event) throws EventNotFoundException;
    public int getAvailableNoOfTickets(Event event) throws EventNotFoundException;
    public void calculate_booking_cost(Event event, int num_tickets, Booking booking) throws EventNotFoundException;
    public Booking book_tickets(Event event, int num_tickets, List<Customer> customers) throws EventNotFoundException;
    public void cancel_tickets(int booking_id) throws InvalidBookingException;
    public void get_booking_details(int booking_id) throws InvalidBookingException;


}
