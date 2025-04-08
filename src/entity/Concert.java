package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event {

    private String artist;
    private String type;

    public Concert(){
        super();
    }

    public Concert(String eventName, LocalDate eventDate, LocalTime eventTime, String venueName,
                   int totalSeats, int availableSeats, double ticketPrice, String eventType, String artist, String type){

        super(eventName, eventDate, eventTime, venueName, totalSeats, availableSeats, ticketPrice, eventType);
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
        System.out.println("Venue Name: " + getVenue_name());
        System.out.println("Artist Name:" +artist );
        System.out.println("ConcertType: " + type);
    }

    @Override
    public void display_event_details(){
        super.display_event_details();
        System.out.println("Artist: " + artist);
        System.out.println("ConcertType: " + type);
    }
}
