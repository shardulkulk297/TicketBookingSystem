package Entity;


import java.time.LocalDate;
import java.time.LocalTime;

public class Sports extends Event {
    private String sportName;
    private String teamsName;

    public Sports(){}

    public Sports(String event_name, LocalDate event_date, LocalTime event_time, String venue_name, int total_seats, double ticket_price, String event_type, String sportName, String teamsName){
        super(event_name, event_date, event_time, venue_name, total_seats, ticket_price, event_type);
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

    public void display_sport_details(){
        super.display_event_details();
        System.out.println("SportName: "+ sportName);
        System.out.println("TeamsName: "+ teamsName);
    }
}
