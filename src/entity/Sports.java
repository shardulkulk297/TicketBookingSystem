package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sports extends Event {
    private String sportName;
    private String teamsName;

    public Sports(){
        super();
    }

    public Sports(String eventName, LocalDate eventDate, LocalTime eventTime, Venue venue,
                  int totalSeats, int availableSeats, double ticketPrice, String eventType, String sportName, String teamsName){
        super(eventName, eventDate, eventTime, venue, totalSeats, availableSeats, ticketPrice, eventType);

        this.sportName = sportName;
        this.teamsName = teamsName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public void setTeamsName(String teamsName) {
        this.teamsName = teamsName;
    }

    public String getSportName() {
        return sportName;
    }

    public String getTeamsName() {
        return teamsName;
    }

    public void display_sport_details(){
        System.out.println("Venue_name: " + getVenue());
        System.out.println("SportName: " + sportName);
        System.out.println("TeamsName: " + teamsName);
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
        System.out.println("SportName: " + sportName);
        System.out.println("TeamsName: " + teamsName);
    }
}
