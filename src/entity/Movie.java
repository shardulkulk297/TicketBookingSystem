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

    public Movie(String eventName, LocalDate eventDate, LocalTime eventTime, String venueName,
                 int totalSeats, int availableSeats, double ticketPrice, String eventType,
                 String genre, String actorName, String actressName) {
        super(eventName, eventDate, eventTime, venueName, totalSeats, availableSeats, ticketPrice, eventType);
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
        System.out.println("Venue_name" + getVenue_name());
        System.out.println("Genre: " +Genre);
        System.out.println("ActorName: " + ActorName);
        System.out.println("ActressName: "+ ActressName);
    }

    @Override
    public void display_event_details(){
        super.display_event_details();

        System.out.println("Genre: " +Genre);
        System.out.println("ActorName: " + ActorName);
        System.out.println("ActressName: "+ ActressName);


    }


}
