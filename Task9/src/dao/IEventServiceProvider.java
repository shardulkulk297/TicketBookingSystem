package dao;

import Entity.Event;
import Entity.Venue;
import exception.EventNotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IEventServiceProvider {
    public Event create_event(String event_name, LocalDate event_date, LocalTime event_time, int total_seats, double ticket_price, String event_type, Venue venue);
    public void getEventDetails(Event event) throws EventNotFoundException;
    public int getAvailableNoOfTickets(Event event) throws EventNotFoundException;
}
