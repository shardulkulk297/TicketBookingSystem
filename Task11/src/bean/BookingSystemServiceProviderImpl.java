package bean;

import service.IBookingServiceProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingSystemServiceProviderImpl implements IBookingServiceProvider {
     List<Event> events = new ArrayList<>();
     List<Booking> bookings = new ArrayList<>();

    @Override
    public void calculate_booking_cost(Event event, int num_tickets, Booking booking){
        double totalCost = event.getTicket_price() * num_tickets;
        booking.setTotal_cost(totalCost);
        booking.setNum_tickets(num_tickets);
    }
    @Override
    public Booking book_tickets(Event event, int num_tickets, List<Customer> customers){

        double totalCost = 0.00;
        Booking booking = null;
        events.add(event);


        if(event == null){
            System.out.println("NO EVENT SELECTED");
            return booking;
        }
        if (customers == null || customers.size() != num_tickets) {
            System.out.println("Customer count does not match number of tickets.");
            return null;
        }

        if(event.getAvailable_seats() < num_tickets)
        {
            System.out.println("Not enough tickets");
            return booking;
        }
        else{
            totalCost = num_tickets * event.getTicket_price();
            booking = new Booking(
                    event,
                    customers,
                    num_tickets,
                    totalCost,
                    LocalDate.now()
            );
            event.setAvailable_seats(event.getAvailable_seats() - num_tickets);
            bookings.add(booking);
            System.out.println("Booking Successful, your booking Id is: " + booking.getBookingId());
        }
        return booking;
    }
    @Override
    public void cancel_tickets(int booking_id){

        Booking booking = null;

        for(Booking b: bookings){
            if(b.getBookingId()==booking_id){
                booking = b;
                break;
            }
        }

        if(booking != null){
            Event e = booking.getEvent();
            e.setAvailable_seats(e.getAvailable_seats() + booking.getNum_tickets());
            System.out.println("Canceled Successfully");
            bookings.remove(booking);
        }
        else{
            System.out.println("Booking NOT FOUND");
        }



    }
    @Override
    public void get_booking_details(int booking_id){

        Booking booking = null;

        for(Booking b: bookings){
            if(b.getBookingId() == booking_id){
                booking = b;
                break;
            }

        }

        if(booking != null){
            booking.display_booking_details();
        }
        else{
            System.out.println("BOOKING NOT FOUND");
        }

    }
}
