package dao;

import Entity.Event;
import Entity.Venue;
import exception.EventNotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;


public interface IEventServiceProvider {
    Event create_event(String event_name, LocalDate event_date, LocalTime event_time, int total_seats, double ticket_price, String event_type, Venue venue);
    void getEventDetails(Event event) throws EventNotFoundException;
    int getAvailableNoOfTickets(Event event) throws EventNotFoundException;
}