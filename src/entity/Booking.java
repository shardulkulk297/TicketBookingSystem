package entity;

import java.time.LocalDate;

public class Booking {

    private int num_tickets;
    private int total_cost;
    private LocalDate booking_date;

    public Booking(){

    }
    public Booking(int num_tickets, int total_cost, LocalDate booking_date){
        this.num_tickets = num_tickets;
        this.total_cost = total_cost;
        this.booking_date = booking_date;
    }

    public void setBooking_date(LocalDate booking_date) {
        this.booking_date = booking_date;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setNum_tickets(int num_tickets) {
        this.num_tickets = num_tickets;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }

    public Event getEvent() {
        return event;
    }

    public int getNum_tickets() {
        return num_tickets;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public LocalDate getBooking_date() {
        return booking_date;
    }


    Event event = new Event();

     public void calculate_booking_cost(int num_tickets){

         double totalCost = event.getTicket_price() * num_tickets;
         this.total_cost = total_cost;
     }

     public void book_tickets(int num_tickets){
         event.book_tickets(num_tickets);
     }

     public void cancel_booking(int num_tickets){
         event.cancel_booking(num_tickets);
     }

     public int getAvailableNoOfTickets(){
         return event.getAvailable_seats();
     }

     public void getEventDetails(){

         if(event!=null){
             event.display_event_details();
         }
         else{
             System.out.println("No events now");
         }

     }

}
