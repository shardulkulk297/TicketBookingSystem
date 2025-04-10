package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movie extends Event {

    private String Genre;
    private String ActorName;
    private String ActressName;

    public Movie() {
        super();
    }

    public Movie(String eventName, LocalDate eventDate, LocalTime eventTime, Venue venue,
                 int totalSeats, int availableSeats, double ticketPrice, String eventType,
                 String genre, String actorName, String actressName) {
        super(eventName, eventDate, eventTime, venue, totalSeats, availableSeats, ticketPrice, eventType);
        this.Genre = genre;
        this.ActorName = actorName;
        this.ActressName = actressName;
    }


    public void setGenre(String genre) {
        Genre = genre;
    }

    public void setActorName(String actorName) {
        ActorName = actorName;
    }

    public void setActressName(String actressName) {
        ActressName = actressName;
    }

    public String getGenre() {
        return Genre;
    }

    public String getActorName() {
        return ActorName;
    }

    public String getActressName() {
        return ActressName;
    }
    public void movie_details(){
        System.out.println("Venue_name" + getVenue());
        System.out.println("Genre: " +Genre);
        System.out.println("ActorName: " + ActorName);
        System.out.println("ActressName: "+ ActressName);
    }

    @Override
    public void display_event_details(){
        System.out.println("Event Name: " + getEvent_name());
        System.out.println("Event Date: " + getEvent_date());
        System.out.println("Event Time: " + getEvent_time());
        System.out.println("Venue: " + getVenue());
        System.out.println("Total Seats: " + getTotal_seats());
        System.out.println("Available Seats: " + getAvailable_seats());
        System.out.println("Ticket Price: " + getTicket_price());
        System.out.println("Event Type: " + getEventType());

        System.out.println("Genre: " +Genre);
        System.out.println("ActorName: " + ActorName);
        System.out.println("ActressName: "+ ActressName);


    }


}
