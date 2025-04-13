package dao;

import Entity.Booking;
import Entity.Customer;
import Entity.Event;
import exception.EventNotFoundException;
import exception.InvalidBookingException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class BookingSystemServiceProviderImpl implements IBookingServiceProvider{

    private Map<Integer, Event> events = new HashMap<>();
    private Map<Integer, Booking> bookings = new HashMap<>();


    @Override
    public void calculate_booking_cost(Event event, int num_tickets, Booking booking) throws EventNotFoundException {

        if(event == null){
            throw new EventNotFoundException("Event Not Found for cost calculation");
        }

        if (booking == null) {
            System.out.println("Booking object is null in cost calculation.");
            return;
        }


        double totalCost = event.getTicket_price() * num_tickets;
        booking.setTotal_cost(totalCost);
        booking.setNum_tickets(num_tickets);
    }


    @Override
    public Booking book_tickets(Event event, int num_tickets, Map<String, Customer> customers) throws EventNotFoundException{ // Accepts Map

        if(event == null){
            throw new EventNotFoundException("Event Not Found for booking");
        }

        events.putIfAbsent(event.getEventId(), event);


        Booking booking = null;


        if (customers == null || customers.size() != num_tickets) {
            System.out.println("Customer count (" + (customers == null ? 0 : customers.size()) + ") does not match number of tickets (" + num_tickets + ").");
            return null;
        }

        if(event.getAvailable_seats() < num_tickets)
        {
            System.out.println("Not enough tickets available for event: " + event.getEvent_name());
            return booking;
        }
        else{
            double totalCost = num_tickets * event.getTicket_price();
            booking = new Booking(
                    event,
                    customers,
                    num_tickets,
                    totalCost,
                    LocalDate.now()
            );
            event.setAvailable_seats(event.getAvailable_seats() - num_tickets);
            bookings.put(booking.getBookingId(), booking); // Use put for Map
            System.out.println("Booking Successful, your booking Id is: " + booking.getBookingId());
        }
        return booking;
    }


    @Override
    public void cancel_tickets(int booking_id) throws InvalidBookingException {

        if(!bookings.containsKey(booking_id)){
            System.out.println("Booking ID " + booking_id + " NOT FOUND for cancellation.");
            throw new InvalidBookingException("Booking Id " + booking_id + " not FOUND");
        }

        Booking booking = bookings.get(booking_id);


        if(booking != null){
            Event e = booking.getEvent();
            if (e != null) {
                e.setAvailable_seats(e.getAvailable_seats() + booking.getNum_tickets());
                System.out.println("Canceled Successfully for Booking ID: " + booking_id);
                bookings.remove(booking_id);
            } else {
                System.out.println("Error: Event associated with booking " + booking_id + " is null.");
                bookings.remove(booking_id);
            }

        }
        else{

            System.out.println("Booking NOT FOUND (Error in logic if reached).");
            throw new InvalidBookingException("Booking Id not FOUND (internal error)");
        }
    }


    @Override
    public void get_booking_details(int booking_id) throws InvalidBookingException{

        if(!bookings.containsKey(booking_id)){
            System.out.println("Booking ID " + booking_id + " NOT FOUND for details.");
            throw new InvalidBookingException("Booking Id " + booking_id + " not FOUND");
        }

        Booking booking = bookings.get(booking_id);

        if(booking != null){
            booking.display_booking_details();
        }
        else{
            System.out.println("BOOKING NOT FOUND (Error in logic if reached).");
            throw new InvalidBookingException("Booking Id not FOUND (internal error)");
        }

    }



    public Event findEventByName(String name) {
        for (Event event : events.values()) {
            if (event.getEvent_name().equalsIgnoreCase(name)) {
                return event;
            }
        }
        return null;
    }


    public Map<Integer, Event> getAllEvents() {
        return events;
    }

    public Map<Integer, Booking> getAllBookings() {
        return bookings;
    }
}