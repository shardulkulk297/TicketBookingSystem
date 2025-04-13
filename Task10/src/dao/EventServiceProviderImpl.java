package dao;

import Entity.*;
import exception.EventNotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;


public class EventServiceProviderImpl implements IEventServiceProvider {
    Scanner sc = new Scanner(System.in);

    @Override
    public Event create_event(String event_name, LocalDate event_date, LocalTime event_time, int total_seats, double ticket_price, String event_type, Venue venue){

        Event event = null;

        if(event_type.equalsIgnoreCase("Movie")){
            System.out.println("Enter Movie Genre: ");
            String genre = sc.nextLine();
            System.out.println("Enter Actor Name: ");
            String ActorName = sc.nextLine();
            System.out.println("Enter Actress Name: ");
            String ActressName = sc.nextLine();

            event = new Movie(event_name, event_date, event_time, venue, total_seats, ticket_price, event_type, genre, ActorName, ActressName);
        }
        else if(event_type.equalsIgnoreCase("Concert")){
            System.out.println("Enter Artist: ");
            String artist = sc.nextLine();
            System.out.println("Enter Concert Type");
            String concertType = sc.nextLine();

            event = new Concert(event_name, event_date, event_time, venue, total_seats, ticket_price, event_type, artist, concertType);
        }
        else if(event_type.equalsIgnoreCase("Sports"))
        {
            System.out.println("Enter Sport Name: ");
            String sportName = sc.nextLine();
            System.out.println("Enter Teams Name: ");
            String teamsName = sc.nextLine();

            event = new Sport(event_name, event_date, event_time, venue, total_seats, ticket_price, event_type, sportName, teamsName);

        } else {
            System.out.println("Invalid event type specified: " + event_type);
        }


        return event;
    }

    @Override
    public void getEventDetails(Event event) throws EventNotFoundException {

        if(event == null){
            throw new EventNotFoundException("Event Not found to display details.");
        }

        event.display_event_details();


    }

    @Override
    public int getAvailableNoOfTickets(Event event) throws EventNotFoundException{

        if(event == null){
            throw new EventNotFoundException("Event Not found to get available tickets.");
        }


        return event.getAvailable_seats();
    }

}