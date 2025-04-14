package service;

import bean.Event;
import bean.Venue;

import java.time.LocalDate;
import java.time.LocalTime;

public interface IEventServiceProvider {
    public Event create_event(String event_name, LocalDate event_date, LocalTime event_time, int total_seats, double ticket_price, String event_type, Venue venue);
    public void getEventDetails(Event event);
    public int getAvailableNoOfTickets(Event event);
}
