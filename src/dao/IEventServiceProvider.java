package dao;

import entity.Event;
import entity.Venue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IEventServiceProvider {

    public Event create_event(String event_name, LocalDate event_date, LocalTime event_time, int total_seats, double ticket_price, String event_type, Venue venue);
    public List<Event> getEventDetails();
    public int getAvailableNoOfTickets();
}
