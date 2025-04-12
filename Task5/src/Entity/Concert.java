package Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event {
    private String artist;
    private String concertType;

    public Concert(){}

    public Concert(String event_name, LocalDate event_date, LocalTime event_time, String venue_name, int total_seats, double ticket_price, String event_type , String artist, String concertType){
        super(event_name, event_date, event_time, venue_name, total_seats, ticket_price, event_type);
        this.artist = artist;
        this.concertType = concertType;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setConcertType(String concertType) {
        this.concertType = concertType;
    }

    public String getConcertType() {
        return concertType;
    }

    public void display_concert_details(){
        super.display_event_details();
        System.out.println("Artist: " + artist);
        System.out.println("ConcertType: " + concertType);
    }
}
