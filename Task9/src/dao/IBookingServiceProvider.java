package dao;

import Entity.Booking;
import Entity.Customer;
import Entity.Event;
import exception.EventNotFoundException;
import exception.InvalidBookingException;

import java.util.List;

public interface IBookingServiceProvider {

    public void calculate_booking_cost(Event event, int num_tickets, Booking booking)throws EventNotFoundException;
    public Booking book_tickets(Event event, int num_tickets, List<Customer> customers) throws EventNotFoundException;
    public void cancel_tickets(int booking_id) throws InvalidBookingException;
    public void get_booking_details(int booking_id) throws InvalidBookingException;



}
