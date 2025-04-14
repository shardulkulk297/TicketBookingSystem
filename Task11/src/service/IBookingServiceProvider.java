package service;

import bean.Booking;
import bean.Customer;
import bean.Event;

import java.util.List;

public interface IBookingServiceProvider {

    public void calculate_booking_cost(Event event, int num_tickets, Booking booking);
    public Booking book_tickets(Event event, int num_tickets, List<Customer> customers);
    public void cancel_tickets(int booking_id);
    public void get_booking_details(int booking_id);



}
