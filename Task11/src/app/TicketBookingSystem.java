package app;
import bean.BookingSystemRepositoryImpl;
import bean.Booking;
import bean.Customer;
import bean.Event;
import bean.Venue;
import exception.EventNotFoundException;
import exception.InvalidBookingException;
import repository.IBookingSystemRepository;
import service.IEventServiceProvider;
import util.DBUtil;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketBookingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IBookingSystemRepository repo = new BookingSystemRepositoryImpl();
        List<Event> events = new ArrayList<>();
        while (true) {
            System.out.println("\n1. Create Event");
            System.out.println("2. View Event Details");
            System.out.println("3. Book Tickets");
            System.out.println("4. Cancel Tickets");
            System.out.println("5. Get Booking Details");
            System.out.println("0. Exit");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: {
                    System.out.print("Enter event name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter event date (yyyy-mm-dd): ");
                    LocalDate eventDate = LocalDate.parse(sc.nextLine());
                    System.out.print("Enter event time (HH:mm): ");
                    LocalTime eventTime = LocalTime.parse(sc.nextLine());
                    System.out.print("Enter total seats: ");
                    int totalSeats = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter ticket price: ");
                    double ticketPrice = Double.parseDouble(sc.nextLine());
                    System.out.print("Enter event type: ");
                    String eventType = sc.nextLine();
                    System.out.print("Enter venue name: ");
                    String venueName = sc.nextLine();
                    System.out.print("Enter venue address: ");
                    String venueAddress = sc.nextLine();
                    Venue venue = new Venue(venueName, venueAddress);
                    try {
                        Event ev = repo.create_event(name, eventDate, eventTime, totalSeats, ticketPrice, eventType, venue);
                        events.add(ev);
                        System.out.println("Event created successfully with ID: " + ev.getEvent_id());
                    } catch (EventNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                }
                case 2: {
                    System.out.print("Enter event index to view details: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    if (idx >= 1 && idx <= events.size()) {
                        try {
                            repo.getEventDetails(events.get(idx - 1));
                        } catch (EventNotFoundException ex) {
                            System.out.println(ex.getMessage());
                        }
                    } else {
                        System.out.println("Invalid index");
                    }
                    break;
                }
                case 3: {
                    System.out.print("Enter event index to book tickets: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    if (idx >= 1 && idx <= events.size()) {
                        System.out.print("Enter number of tickets: ");
                        int numTickets = Integer.parseInt(sc.nextLine());
                        List<Customer> customers = new ArrayList<>();
                        for (int i = 0; i < numTickets; i++) {
                            System.out.print("Enter customer name: ");
                            String custName = sc.nextLine();
                            System.out.print("Enter customer email: ");
                            String custEmail = sc.nextLine();
                            System.out.print("Enter customer phone: ");
                            String custPhone = sc.nextLine();
                            customers.add(new Customer(custName, custEmail, custPhone));
                        }
                        try {
                            Booking booking = repo.book_tickets(events.get(idx - 1), numTickets, customers);
                            if (booking != null)
                                System.out.println("Booking successful. Booking ID: " + booking.getBookingId());
                        } catch (EventNotFoundException ex) {
                            System.out.println(ex.getMessage());
                        }
                    } else {
                        System.out.println("Invalid index");
                    }
                    break;
                }
                case 4: {
                    System.out.print("Enter booking ID to cancel tickets: ");
                    int bookingId = Integer.parseInt(sc.nextLine());
                    try {
                        repo.cancel_tickets(bookingId);
                    } catch (InvalidBookingException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                }
                case 5: {
                    System.out.print("Enter booking ID to view details: ");
                    int bookingId = Integer.parseInt(sc.nextLine());
                    try {
                        repo.get_booking_details(bookingId);
                    } catch (InvalidBookingException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                }
                case 0: {
                    System.out.println("Exiting...");
                    return;
                }
                default: {
                    System.out.println("Invalid choice");
                }
            }
        }
    }
}
