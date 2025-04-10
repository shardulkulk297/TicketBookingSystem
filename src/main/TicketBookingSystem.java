package main;

import entity.*;
import jdk.jfr.Percentage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//This is file for Task 6 and Task 7 before Database implementation that's why the event details are directly passed in the constructor
public class TicketBookingSystem extends BookingSystem {
    private Event event;


    private List<Event> events;
    private Venue venue;
    private List<Customer> customers;
    private List<Booking> bookings;


    public TicketBookingSystem() {
        events = new ArrayList<>();
        //sample events
        Venue venue1 = new Venue("IMax Theatre", "New York");
        Venue venue2 = new Venue("Madison Square Garden", "Silicon Valley");
        Venue venue3 = new Venue("National Stadium", "Mumbai");

        events.add(new Movie("Inception", LocalDate.of(2025, 05, 01), LocalTime.of(15, 00), venue1,
                100, 100, 15.0, "Movie", "Leonardo DiCaprio", "Elliot Page", "Action"));

        events.add(new Concert("Coldplay Live", LocalDate.of(2025, 06, 14), LocalTime.of(20, 00), venue2,
                200, 200, 50.0, "Concert", "Coldplay", "Rock"));

        events.add(new Sports("Cricket Finals", LocalDate.of(2025, 07, 07), LocalTime.of(17, 00), venue3,
                300, 300, 20.0, "Sports", "Cricket", "India vs Pakistan"));
    }



    @Override
    public Event create_event(String event_name, LocalDate event_date, LocalTime event_time, int total_seats, double ticket_price, String event_type, Venue venue){
        event.setEvent_name(event_name);
        event.setEvent_date(event_date);
        event.setEvent_time(event_time);
        event.setEvent_type(event_type);
        event.setTicket_price(ticket_price);
        event.setTotal_seats(total_seats);
        event.setVenue(venue);

        return event;
    }

    @Override
    public void display_event_details(Event event){
        event.display_event_details();
    }


    @Override
    public double book_tickets(Event event, int num_tickets,List<Customer> customers){
       if(event.getAvailable_seats() >= num_tickets){

           if(customers == null || customers.size() != num_tickets){
               System.out.println("List must match");
               return 0.00;
           }

           double totalCost = event.getTicket_price() * num_tickets;

           Booking booking = new Booking();
           booking.setCustomers(customers);
           booking.setBooking_date(LocalDate.now());
           booking.setEvent(event);
           booking.setTotal_cost(totalCost);
           event.setAvailable_seats(event.getAvailable_seats() - num_tickets);

           bookings = new ArrayList<>();
           bookings.add(booking);
           System.out.println("Booking successful");
           return totalCost;





       }

       else{
           System.out.println("SOMETHING WENT WRONG");
           return 0.00;
       }

    }

    @Override
    public void cancel_tickets(Event event, int num_tickets){
        event.cancel_booking(num_tickets);
    }

    @Override
    public int getAvailableNoOfTickets(Event event){
        return event.getAvailable_seats();
    }

    public List<Event> getEventDetails(){
        return events;
    }

    public static void main(String[] args) {
        BookingSystem system = new TicketBookingSystem();
        //upcasting
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (true) {
            System.out.println("\n----- Ticket Booking System Menu -----");
            System.out.println("1. View Event Details");
            System.out.println("2. Book Tickets");
            System.out.println("3. Cancel Tickets");
            System.out.println("4. View available Tickets");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            List<Event> events = system.getEventDetails();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1-> {
                    // List available events
                    System.out.println("\nAvailable Events:");
                    for (int i = 0; i < events.size(); i++) {

                        System.out.println((i + 1) + ". " + events.get(i).getEvent_name());
                    }
                    System.out.print("Select event number to view details: ");
                    int eventIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (eventIndex > 0 && eventIndex <= events.size()) {
                        System.out.println("\nEvent Details:");
                        // Polymorphic call; the correct overridden display_event_details() is executed
                        system.display_event_details(events.get(eventIndex - 1));
                    } else {
                        System.out.println("Invalid event selection.");
                    }
                }

                case 2-> {
                    // Book tickets
                    System.out.println("\nAvailable Events:");
                    for (int i = 0; i < events.size(); i++) {
                        System.out.println((i + 1) + ". " + events.get(i).getEvent_name());
                    }
                    System.out.print("Select event number to book tickets: ");
                    int eventIndex = scanner.nextInt();
                    System.out.print("Enter number of tickets to book: ");
                    int numTickets = scanner.nextInt();
                    scanner.nextLine();

                    List<Customer> customers = new ArrayList<>();
                    for(int i=0; i<numTickets; i++)
                    {
                        System.out.println("Enter Your name: ");
                        String name = scanner.next();
                        System.out.println("Enter email: ");
                        String email = scanner.next();
                        System.out.println("Enter Phone number: ");
                        String phone_number = scanner.next();
                        Customer cust = new Customer(name, email, phone_number);

                        customers.add(cust);

                    }

                    if (eventIndex > 0 && eventIndex <= events.size()) {
                        system.book_tickets(events.get(eventIndex - 1), numTickets,  customers);
                    } else {
                        System.out.println("Invalid event selection.");
                    }
                }

                case 3-> {
                    // Cancel tickets
                    System.out.println("\nAvailable Events:");
                    for (int i = 0; i < events.size(); i++) {
                        System.out.println((i + 1) + ". " + events.get(i).getEvent_name());
                    }
                    System.out.print("Select event number to cancel tickets: ");
                    int eventIndex = scanner.nextInt();
                    System.out.print("Enter number of tickets to cancel: ");
                    int numTickets = scanner.nextInt();
                    scanner.nextLine();


                    if (eventIndex > 0 && eventIndex <= events.size()) {
                        system.cancel_tickets(events.get(eventIndex - 1), numTickets);
                    } else {
                        System.out.println("Invalid event selection.");
                    }
                }
                case 4->{
                    System.out.print("Select event number to view no of available seats: ");
                    int eventIndex = scanner.nextInt();
                    if (eventIndex > 0 && eventIndex <= events.size()) {
                        Event event = events.get(eventIndex - 1);
                        int tickets =  system.getAvailableNoOfTickets(event);
                        System.out.println(tickets);
                    }
                    else{
                        System.out.println("Invalid event selection.");
                    }
                }

                case 5->{
                    List<Event> allEvents = system.getEventDetails();
                    if(allEvents.isEmpty()){
                        System.out.println("No events");
                    }
                    else{
                        for(Event e: allEvents){
                            System.out.println(e);
                        }
                    }
                }

                case 0->{
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);
                }


                default->{
                    System.out.println("Invalid choice. Please try again.");
                }

            }
        }
    }

}
