package Entity;


import java.time.LocalDate;
import java.time.LocalTime;

public class Sport extends Event {
    private String sportName;
    private String teamsName;

    public Sport(){}

    public Sport(String event_name, LocalDate event_date, LocalTime event_time, Venue venue, int total_seats, double ticket_price, String event_type, String sportName, String teamsName){
        super(event_name, event_date, event_time, venue, total_seats, ticket_price, event_type);
        this.sportName = sportName;
        this.teamsName = teamsName;
    }

    public void setTeamsName(String teamsName) {
        this.teamsName = teamsName;
    }

    public String getTeamsName() {
        return teamsName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getSportName() {
        return sportName;
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
        System.out.println("SportName: "+ sportName);
        System.out.println("TeamsName: "+ teamsName);
    }

    @Override
    public String toString() {
        return "Sport{" +
                "event_name='" + getEvent_name() + '\'' +
                ", event_date=" + getEvent_date() +
                ", event_time=" + getEvent_time() +
                ", venue=" + getVenue() +
                ", total_seats=" + getTotal_seats() +
                ", available_seats=" + getAvailable_seats() +
                ", ticket_price=" + getTicket_price() +
                ", event_type='" + getEvent_type() + '\'' +
                ", sportName='" + sportName + '\'' +
                ", teamsName='" + teamsName + '\'' +
                '}';
    }

}
