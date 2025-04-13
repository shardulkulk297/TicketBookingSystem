package Main;
import Entity.*;
import dao.BookingSystemServiceProviderImpl;
import dao.EventServiceProviderImpl;
import dao.IBookingServiceProvider;
import dao.IEventServiceProvider;
import exception.EventNotFoundException;
import exception.InvalidBookingException;
import util.EventComparator;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;


public class TicketBookingSystem {


    IEventServiceProvider eventService = new EventServiceProviderImpl();
    BookingSystemServiceProviderImpl bookingService = new BookingSystemServiceProviderImpl();
    List<Event> events = new ArrayList<>();

    Scanner sc = new Scanner(System.in);


    private static LocalDate ConvertDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr); // Assumes yyyy-mm-dd format
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            return null;
        }
    }

    private static LocalTime ConvertTime(String timeStr) {
        try {
            return LocalTime.parse(timeStr); // Assumes HH:mm format
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please use HH:mm.");
            return null;
        }
    }


    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        Scanner sc = system.sc;

        while (true) {
            System.out.println("\n--- Ticket Booking System Menu ---");
            System.out.println("1. Create Event");
            System.out.println("2. View All Events");
            System.out.println("3. Book Tickets");
            System.out.println("4. Cancel Tickets");
            System.out.println("5. Get Booking Details");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }


            switch (choice) {
                case 1 -> {
                    try {
                        System.out.print("Enter event name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter date (yyyy-mm-dd): ");
                        LocalDate date = ConvertDate(sc.nextLine());
                        if (date == null) continue;

                        System.out.print("Enter time (HH:mm): ");
                        LocalTime time = ConvertTime(sc.nextLine());
                        if (time == null) continue;


                        System.out.print("Enter total seats: ");
                        int seats = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter ticket price: ");
                        double price = Double.parseDouble(sc.nextLine());

                        System.out.print("Enter event type (Movie, Concert, Sports): ");
                        String type = sc.nextLine();
                        System.out.print("Enter Venue Name: ");
                        String venueName = sc.nextLine();
                        System.out.print("Enter Venue Address: ");
                        String addressV = sc.nextLine();
                        Venue venue = new Venue(venueName, addressV);

                        Event newEvent = system.eventService.create_event(name, date, time, seats, price, type, venue);
                        system.events.add(newEvent);

                        if(newEvent != null){

                            System.out.println("Event '" + newEvent.getEvent_name() + "' created successfully with ID: " + newEvent.getEventId());
                        } else {
                            System.out.println("Failed to create event. Check event type.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format entered. Please try again.");
                    } catch (Exception e) {
                        System.out.println("An error occurred during event creation: " + e.getMessage());
                    }

                }
                case 2 -> {
                    System.out.println("\n--- Available Events ---");

                    if(system.events.isEmpty())
                    {
                        System.out.println("No Events Scheduled");
                    }
                    else{
                        Collections.sort(system.events, new EventComparator());
                        for (int i = 0; i < system.events.size(); i++) {
                            System.out.println((i + 1) + ". " + system.events.get(i).getEvent_name());
                        }
                    }


                    System.out.print("Select event number: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    if (idx >= 1 && idx <= system.events.size()) {


                        try{
                            system.eventService.getEventDetails(system.events.get(idx - 1));
                        }
                        catch (EventNotFoundException e){
                            System.out.println(e.getMessage());

                        }




                    } else {
                        System.out.println("Invalid selection.");
                    }
                }
                case 3 -> {
                    try {
                        System.out.println("\n--- Select Event to Book ---");
                        Map<Integer, Event> eventsMap = system.bookingService.getAllEvents();
                        if (eventsMap.isEmpty()) {
                            System.out.println("No events available to book.");
                            continue;
                        }

                        eventsMap.forEach((id, ev) -> System.out.println("ID: " + id + " - " + ev.getEvent_name() + " (" + ev.getEvent_type() + ")"));
                        System.out.print("Enter Event ID to book: ");
                        int eventIdToBook = Integer.parseInt(sc.nextLine());


                        Event selectedEvent = eventsMap.get(eventIdToBook);

                        if (selectedEvent != null) {
                            System.out.print("Enter number of tickets to book: ");
                            int tickets = Integer.parseInt(sc.nextLine());
                            Map<String, Customer> customers = new HashMap<>(); // Create map for customers
                            for(int i=0; i<tickets; i++)
                            {
                                System.out.println("\nEnter details for ticket " + (i+1) + ":");
                                System.out.print("Enter Customer name: ");
                                String custName = sc.nextLine();
                                String email;
                                while (true) {
                                    System.out.print("Enter email (unique identifier): ");
                                    email = sc.nextLine();
                                    if (customers.containsKey(email)) {
                                        System.out.println("Email already used for this booking. Please enter a unique email.");
                                    } else {
                                        break;
                                    }
                                }
                                System.out.print("Enter Phone Number: ");
                                String phone = sc.nextLine();

                                Customer customer = new Customer(custName, email, phone);
                                customers.put(email, customer);
                            }


                            Booking booking = system.bookingService.book_tickets(selectedEvent, tickets, customers);


                            if(booking == null){
                                System.out.println("Booking failed. Check availability or customer details.");

                            }


                        } else {
                            System.out.println("Invalid Event ID selection.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format entered. Please enter numeric IDs/counts.");
                    } catch (EventNotFoundException e) {
                        System.out.println("Booking Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An unexpected error occurred during booking: " + e.getMessage());
                        e.printStackTrace(); // For debugging
                    }
                }
                case 4 -> {
                    try {
                        System.out.println("\n--- Cancel Booking ---");
                        Map<Integer, Booking> currentBookings = system.bookingService.getAllBookings();
                        if(currentBookings.isEmpty()){
                            System.out.println("No bookings available to cancel.");
                            continue;
                        }
                        System.out.println("Current Booking IDs: " + currentBookings.keySet());
                        System.out.print("Enter Booking ID to cancel: ");
                        int bookingId = Integer.parseInt(sc.nextLine());


                        system.bookingService.cancel_tickets(bookingId);


                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a numeric Booking ID.");
                    } catch (InvalidBookingException e) {
                        System.out.println("Cancellation Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An unexpected error occurred during cancellation: " + e.getMessage());
                    }
                }


                case 5->{
                    try {
                        System.out.println("\n--- Get Booking Details ---");
                        Map<Integer, Booking> currentBookings = system.bookingService.getAllBookings();
                        if(currentBookings.isEmpty()){
                            System.out.println("No bookings available.");
                            continue;
                        }
                        System.out.println("Current Booking IDs: " + currentBookings.keySet());
                        System.out.print("Enter Booking ID to view details: ");
                        int bookingId = Integer.parseInt(sc.nextLine());


                        system.bookingService.get_booking_details(bookingId);


                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a numeric Booking ID.");
                    } catch (InvalidBookingException e) {
                        System.out.println("Error retrieving details: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An unexpected error occurred while fetching details: " + e.getMessage());
                    }
                }




                case 0 -> {
                    System.out.println("Exiting Ticket Booking System...");
                    sc.close(); // Close scanner
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }


}