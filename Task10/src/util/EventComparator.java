package util; // Assuming a util package

import Entity.Event;
import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event e1, Event e2) {
        int nameCompare = e1.getEvent_name().compareToIgnoreCase(e2.getEvent_name());
        if (nameCompare == 0) {
            return e1.getVenue().getVenue_name().compareToIgnoreCase(e2.getVenue().getVenue_name());
        }
        return nameCompare;
    }
}