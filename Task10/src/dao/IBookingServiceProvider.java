package dao;

import Entity.Booking;
import Entity.Customer;
import Entity.Event;
import exception.EventNotFoundException;
import exception.InvalidBookingException;

import java.util.Map;

public interface IBookingServiceProvider {

    void calculate_booking_cost(Event event, int num_tickets, Booking booking) throws EventNotFoundException;
    Booking book_tickets(Event event, int num_tickets, Map<String, Customer> customers) throws EventNotFoundException; // Updated signature
    void cancel_tickets(int booking_id) throws InvalidBookingException;
    void get_booking_details(int booking_id) throws InvalidBookingException;



}