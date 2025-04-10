package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event {

    private String artist;
    private String type;

    public Concert(){
        super();
    }

    public Concert(String eventName, LocalDate eventDate, LocalTime eventTime, Venue venue,
                   int totalSeats, int availableSeats, double ticketPrice, String eventType, String artist, String type){

        super(eventName, eventDate, eventTime, venue, totalSeats, availableSeats, ticketPrice, eventType);
        this.artist = artist;
        this.type = type;

    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArtist() {
        return artist;
    }

    public String getType() {
        return type;
    }

    public void concert_details(){
        System.out.println("Venue Name: " + getVenue());
        System.out.println("Artist Name:" +artist );
        System.out.println("ConcertType: " + type);
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
        System.out.println("Artist: " + artist);
        System.out.println("ConcertType: " + type);
    }
}
