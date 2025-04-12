import Entity.*;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketBookingSystem {

   List<Event> events = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public Event create_event(String event_name, LocalDate event_date, LocalTime event_time, int total_seats, double ticket_price, String event_type, String venue_name){

        Event event = null;

        if(event_type.equalsIgnoreCase("Movie")){
            System.out.println("Enter Movie Genre: ");
            String genre = sc.next();
            sc.nextLine();
            System.out.println("Enter Actor Name: ");
            String ActorName = sc.nextLine();
            System.out.println("Enter ActressName: ");
            String ActressName = sc.nextLine();

          event = new Movie(event_name, event_date, event_time, venue_name, total_seats, ticket_price, event_type, genre, ActorName, ActressName);
        }

        if(event_type.equalsIgnoreCase("Concert")){
            System.out.println("Enter Artist: ");
            String artist = sc.nextLine();
            System.out.println("Enter ConcertType");
            String concertType = sc.nextLine();

            event = new Concert(event_name, event_date, event_time, venue_name, total_seats, ticket_price, event_type, artist, concertType);
        }

        if(event_type.equalsIgnoreCase("Sports"))
        {
            System.out.println("Enter SportName: ");
            String sportName = sc.nextLine();
            System.out.println("Enter TeamsName: ");
            String teamsName = sc.nextLine();

            event = new Sports(event_name, event_date, event_time, venue_name, total_seats, ticket_price, event_type, sportName, teamsName);

        }
        events.add(event);
        return event;
    }

    public void display_event_details(Event event){
        event.display_event_details();
    }

    public double book_tickets(Event event, int num_tickets){
        double totalCost = 0.00;
        if(event.getAvailable_seats() < num_tickets) {
            System.out.println("Sorry the REMAINING TICKETS are only: "+ event.getAvailable_seats());
        }
        else{
            event.setAvailable_seats(event.getAvailable_seats() - num_tickets);
            totalCost = event.getTicket_price() * num_tickets;
            System.out.println("Booked Successfully");
        }

        return totalCost;

    }

    public void cancel_tickets(Event event, int num_tickets){
        if(num_tickets > event.getAvailable_seats())
        {
            System.out.println("ERROR");
        }
        else{
            event.setAvailable_seats(event.getAvailable_seats() + num_tickets);
            System.out.println("Canceled Successfully");
        }
    }

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
                    String venue = sc.nextLine();
                    sc.nextLine();
                    system.create_event(name, date, time, seats, price, type, venue);

                }
                case 2 -> {
                    for (int i = 0; i < system.events.size(); i++) {
                        System.out.println((i + 1) + ". " + system.events.get(i).getEvent_name());
                    }
                    System.out.print("Select event number: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    if (idx >= 1 && idx <= system.events.size()) {
                        system.display_event_details(system.events.get(idx - 1));
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
                        system.book_tickets(system.events.get(idx - 1), tickets);
                    } else {
                        System.out.println("Invalid selection.");
                    }
                }
                case 4 -> {
                    for (int i = 0; i < system.events.size(); i++) {
                        System.out.println((i + 1) + ". " + system.events.get(i).getEvent_name());
                    }
                    System.out.print("Select event number: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    if (idx >= 1 && idx <= system.events.size()) {
                        System.out.print("Enter number of tickets to cancel: ");
                        int tickets = Integer.parseInt(sc.nextLine());
                        system.cancel_tickets(system.events.get(idx - 1), tickets);
                    } else {
                        System.out.println("Invalid selection.");
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
