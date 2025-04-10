package dao;

import entity.Booking;
import entity.Customer;
import entity.Event;
import exception.InvalidBookingIDException;

import java.util.List;

public interface IBookingSystemServiceProvider {

    public void calculate_booking_cost(int num_tickets);
    public double book_tickets(Event event, int num_tickets, List<Customer> customers);
    public void cancel_booking(int booking_id) throws InvalidBookingIDException;
    public Booking get_booking_details(int booking_id) throws InvalidBookingIDException;

}
