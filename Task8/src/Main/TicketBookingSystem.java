package Main;
import Entity.*;
import dao.BookingSystemServiceProviderImpl;
import dao.EventServiceProviderImpl;
import dao.IBookingServiceProvider;
import dao.IEventServiceProvider;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketBookingSystem {

    List<Event> events = new ArrayList<>();
    List<Booking> bookings = new ArrayList<>();
    IEventServiceProvider e = new EventServiceProviderImpl();
    IBookingServiceProvider b = new BookingSystemServiceProviderImpl();
    Scanner sc = new Scanner(System.in);


    private static LocalDate ConvertDate(int year, int month, int day){
        return LocalDate.of(year, month, day);
    }

    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        Scanner sc = system.sc;

        while (true) {
            System.out.println("\n1. Create Event");
            System.out.println("2. View Event Details");
            System.out.println("3. Book Tickets");
            System.out.println("4. Cancel Tickets");
            System.out.println("5. Get Booking Details");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter event name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter date (yyyy-mm-dd): ");
                    System.out.println("Enter Year: ");
                    int year = sc.nextInt();
                    System.out.println("Enter Month: ");
                    int month = sc.nextInt();
                    System.out.println("Enter Day: ");
                    int day = sc.nextInt();
                    LocalDate date = ConvertDate(year, month, day);
                    System.out.print("Enter time (HH:mm): ");
                    System.out.println("Enter Hour: ");
                    int Hours = sc.nextInt();
                    System.out.println("Enter Minutes:");
                    int Minutes = sc.nextInt();
                    LocalTime time = LocalTime.of(Hours, Minutes);
                    sc.nextLine();
                    System.out.print("Enter total seats: ");
                    int seats = sc.nextInt();
                    System.out.print("Enter ticket price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter event type (Movie, Concert, Sports): ");
                    String type = sc.nextLine();
                    System.out.println("Enter Venue Name: ");
                    String venueName = sc.nextLine();
                    sc.nextLine();
                    System.out.println("Enter Venue Address: ");
                    String addressV = sc.nextLine();
                    Venue venue = new Venue(venueName, addressV);

                    Event newEvent = system.e.create_event(name, date, time, seats, price, type, venue);
                    system.events.add(newEvent);

                }
                case 2 -> {
                    for (int i = 0; i < system.events.size(); i++) {
                        System.out.println((i + 1) + ". " + system.events.get(i).getEvent_name());
                    }
                    System.out.print("Select event number: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    if (idx >= 1 && idx <= system.events.size()) {

                        system.e.getEventDetails(system.events.get(idx - 1));

                    } else {
                        System.out.println("Invalid selection.");
                    }
                }
                case 3 -> {
                    for (int i = 0; i < system.events.size(); i++) {
                        System.out.println((i + 1) + ". " + system.events.get(i).getEvent_name());
                    }
                    System.out.print("Select event number: ");
                    int idx = Integer.parseInt(sc.nextLine());

                    if (idx >= 1 && idx <= system.events.size()) {
                        System.out.print("Enter number of tickets to book: ");
                        int tickets = Integer.parseInt(sc.nextLine());
                        List<Customer> customers = new ArrayList<>();
                        for(int i=0; i<tickets; i++)
                        {
                            System.out.println("Enter Your name: ");
                            String name = sc.nextLine();
                            System.out.println("Enter email: ");
                            String email = sc.nextLine();
                            System.out.println("Enter Phone Number: ");
                            String phone = sc.next();
                            sc.nextLine();
                            Customer customer = new Customer(name, email, phone);
                            customers.add(customer);
                        }

                        Booking booking = system.b.book_tickets(system.events.get(idx-1), tickets, customers);
                        system.bookings.add(booking);

                    } else {
                        System.out.println("Invalid selection.");
                    }
                }
                case 4 -> {
                    for (int i = 0; i < system.events.size(); i++) {
                        System.out.println((i + 1) + ". " + system.events.get(i).getEvent_name());
                    }
                    System.out.print("Enter Booking Id: ");
                    int bookingId = Integer.parseInt(sc.nextLine());

                    if (bookingId >= 1 && bookingId <= system.bookings.size()) {
                        system.b.cancel_tickets(bookingId);
                    }
                    else {
                        System.out.println("Booking NOT FOUND");
                    }
                }

                case 5->{
                    for (int i = 0; i < system.events.size(); i++) {
                        System.out.println((i + 1) + ". " + system.events.get(i).getEvent_name());
                    }
                    System.out.print("Enter BookingID: ");
                    int bookingId = Integer.parseInt(sc.nextLine());

                    if (bookingId >= 1 && bookingId <= system.bookings.size()) {
                        system.b.get_booking_details(bookingId);
                    } else {
                        System.out.println("Invalid Booking Id");
                    }
                }


                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

}
