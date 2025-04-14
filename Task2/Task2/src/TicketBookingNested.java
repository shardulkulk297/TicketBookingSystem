import java.util.Scanner;

public class TicketBookingNested {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Available Ticket Categories:");
        System.out.println("1. Silver - ₹300");
        System.out.println("2. Gold   - ₹500");
        System.out.println("3. Diamond - ₹800");

        System.out.print("Enter ticket category (Silver/Gold/Diamond): ");
        String ticketType = sc.nextLine().toLowerCase();

        System.out.print("Enter number of tickets: ");
        int numberOfTickets = sc.nextInt();

        int base = 0;

        if (numberOfTickets > 0) {
            if (ticketType.equals("silver")) {
                base = 300;
            } else if (ticketType.equals("gold")) {
                base = 500;
            } else if (ticketType.equals("diamond")) {
                base = 800;
            } else {
                System.out.println("Invalid ticket category selected!");
                return;
            }

            int totalCost = base * numberOfTickets;
            System.out.println("Total cost for " + numberOfTickets + " " + ticketType + " ticket(s): ₹" + totalCost);
        } else {
            System.out.println("Invalid number of tickets.");
        }

        sc.close();
    }
}