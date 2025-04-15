import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String ticketType;

        System.out.println("Welcome to the Movie Ticket Reservation System!");
        System.out.println("Available Seating Options:");
        System.out.println("1. Silver  - ₹200");
        System.out.println("2. Gold    - ₹400");
        System.out.println("3. Diamond - ₹700");

        while (true) {
            System.out.print("\nSelect seat type or type 'quit' to exit: ");
            ticketType = input.nextLine().toLowerCase();

            if (ticketType.equals("quit")) {
                System.out.println("Thanks for using the Movie Ticket Reservation System!");
                break;
            }

            int ticketPrice;

            if (ticketType.equals("silver")) {
                ticketPrice = 200;
            } else if (ticketType.equals("gold")) {
                ticketPrice = 400;
            } else if (ticketType.equals("diamond")) {
                ticketPrice = 700;
            } else {
                System.out.println("Invalid seat type. Please try again.");
                continue;
            }

            System.out.print("How many seats would you like to reserve? ");
            int seats;

            try {
                seats = Integer.parseInt(input.nextLine());

                if (seats <= 0) {
                    System.out.println("Seat count must be a positive number.");
                    continue;
                }

                int totalAmount = ticketPrice * seats;
                System.out.println("Reservation Confirmed!");
                System.out.println("Total amount for " + seats + " " + ticketType + " seat(s): ₹" + totalAmount);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value for seats.");
            }
        }

        input.close();
    }
}
