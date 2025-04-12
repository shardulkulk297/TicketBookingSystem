package Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movie extends Event {

    private String Genre;
    private String ActorName;
    private String ActressName;

    public Movie(){}

    public Movie(String event_name, LocalDate event_date, LocalTime event_time, String venue_name, int total_seats, double ticket_price, String event_type, String Genre, String ActorName, String ActressName){
        super(event_name, event_date, event_time, venue_name, total_seats, ticket_price, event_type);
        this.Genre = Genre;
        this.ActorName = ActorName;
        this.ActressName = ActressName;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getActorName() {
        return ActorName;
    }

    public void setActorName(String actorName) {
        ActorName = actorName;
    }

    public String getActressName() {
        return ActressName;
    }

    public void setActressName(String actressName) {
        ActressName = actressName;
    }

    @Override
    public void display_event_details(){
        System.out.println("Event Name: " + getEvent_name());
        System.out.println("Event Date: " + getEvent_date());
        System.out.println("Event Time: " + getEvent_time());
        System.out.println("Venue Name: " + getVenue_name());
        System.out.println("Total Seats: " + getTotal_seats());
        System.out.println("Available Seats: " + getAvailable_seats());
        System.out.println("Ticket Price: " + getTicket_price());
        System.out.println("Event Type: " + getEvent_type());
        System.out.println("Genre: " + Genre);
        System.out.println("ActorName: " + ActorName);
        System.out.println("ActressName: " + ActressName);
    }
}
