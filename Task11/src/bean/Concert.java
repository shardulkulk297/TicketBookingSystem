package bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event {
    private String artist;
    private String concertType;

    public Concert(){}

    public Concert(String event_name, LocalDate event_date, LocalTime event_time, Venue venue, int total_seats, double ticket_price, String event_type , String artist, String concertType){
        super(event_name, event_date, event_time, venue, total_seats, ticket_price, event_type);
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

    @Override
    public void display_event_details(){
        System.out.println("Event Name: " + getEvent_name());
        System.out.println("Event Date: " + getEvent_date());
        System.out.println("Event Time: " + getEvent_time());
        System.out.println("Venue Name: " + getVenue());
        System.out.println("Total Seats: " + getTotal_seats());
        System.out.println("Available Seats: " + getAvailable_seats());
        System.out.println("Ticket Price: " + getTicket_price());
        System.out.println("Event Type: " + getEvent_type());
        System.out.println("Artist: " + artist);
        System.out.println("ConcertType: " + concertType);
    }

    @Override
    public String toString() {
        return "Concert{" +
                "event_name='" + getEvent_name() + '\'' +
                ", event_date=" + getEvent_date() +
                ", event_time=" + getEvent_time() +
                ", venue=" + getVenue() +
                ", total_seats=" + getTotal_seats() +
                ", available_seats=" + getAvailable_seats() +
                ", ticket_price=" + getTicket_price() +
                ", event_type='" + getEvent_type() + '\'' +
                ", artist='" + artist + '\'' +
                ", concertType='" + concertType + '\'' +
                '}';
    }

}
