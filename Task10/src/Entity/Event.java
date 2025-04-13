package Entity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


public abstract class Event {
    private static int nextEventId = 1;
    private int eventId;
    private String event_name;
    private LocalDate event_date;
    private LocalTime event_time;
    private Venue venue;
    private int total_seats;
    private int available_seats;
    private double ticket_price;
    private String event_type;


    public Event() {
        this.eventId = nextEventId++;
    }

    public Event(String event_name, LocalDate event_date, LocalTime event_time, Venue venue, int total_seats, double ticket_price, String event_type) {
        this.eventId = nextEventId++;
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_time = event_time;
        this.venue = venue;
        this.total_seats = total_seats;
        this.available_seats = total_seats;
        this.ticket_price = ticket_price;
        this.event_type = event_type;
    }

    public int getEventId() {
        return eventId;
    }


    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public LocalDate getEvent_date() {
        return event_date;
    }

    public void setEvent_date(LocalDate event_date) {
        this.event_date = event_date;
    }

    public LocalTime getEvent_time() {
        return event_time;
    }

    public void setEvent_time(LocalTime event_time) {
        this.event_time = event_time;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public int getTotal_seats() {
        return total_seats;
    }

    public void setTotal_seats(int total_seats) {
        this.total_seats = total_seats;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }



    public double calculate_total_revenue() {
        int ticketsSold = total_seats - available_seats;
        return ticketsSold * ticket_price;
    }

    public int getBookedNoOfTickets() {
        return total_seats - available_seats;
    }

    public boolean book_tickets(int num_tickets) {
        if(num_tickets <= available_seats) {
            available_seats -= num_tickets;
            return true;
        }
        return false;
    }

    public boolean cancel_booking(int num_tickets) {
        if(available_seats + num_tickets <= total_seats) {
            available_seats += num_tickets;
            return true;
        }
        return false;
    }

    public abstract void display_event_details();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventId == event.eventId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }


}