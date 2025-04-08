package main;

import entity.Event;

import java.time.LocalDate;
import java.time.LocalTime;

public class TicketBookingSystem {
    private Event event;
    TicketBookingSystem(){
        event = new Event();
    }

    public Event create_event(String event_name, LocalDate event_date, LocalTime event_time, int total_seats, double ticket_price, String event_type, String venue_name){
        event.setEvent_name(event_name);
        event.setEvent_date(event_date);
        event.setEvent_time(event_time);
        event.setEvent_type(event_type);
        event.setTicket_price(ticket_price);
        event.setTotal_seats(total_seats);
        event.setVenue_name(venue_name);

        return event;
    }

    public void display_event_details(Event event){
        event.display_event_details();
    }

    public void book_tickets(Event event, int num_tickets){
        if(event.getAvailable_seats() >= num_tickets){
            int books = event.book_tickets(num_tickets);
            double totalCost = num_tickets * event.getTicket_price();
            System.out.println("Booking successful");

        }

        else{
            if(event.getTotal_seats() == 0)
            {
                System.out.println("Event Sold OUT");
            }
            else{
                System.out.println("Cannot book tickets only " + event.getAvailable_seats() + "Remaining");

            }

        }
    }

    public void cancel_tickets(Event event, int num_tickets){
        event.cancel_booking(num_tickets);
    }

}
