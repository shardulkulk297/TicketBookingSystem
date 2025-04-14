package bean;

import exception.EventNotFoundException;
import exception.InvalidBookingException;
import repository.IBookingSystemRepository;
import util.DBUtil;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookingSystemRepositoryImpl implements IBookingSystemRepository {

    @Override
    public Event create_event(String event_name, LocalDate event_date, LocalTime event_time, int total_seats, double ticket_price, String event_type, Venue venue) throws EventNotFoundException{
        Event event = null;
        Connection con = null;

        try{
            con = DBUtil.getDBConn();
            String addVenue = "insert into Venue (venue_name, address) VALUES(?, ?)";
            PreparedStatement stmtt = con.prepareStatement(addVenue, Statement.RETURN_GENERATED_KEYS);
            stmtt.setString(1, venue.getVenue_name());
            stmtt.setString(2, venue.getAddress());

            int venue_id = -1;

            int affectedRows = stmtt.executeUpdate();
            if(affectedRows > 0){
                ResultSet generatedKeys = stmtt.getGeneratedKeys();
                if(generatedKeys.next()){
                    venue_id = generatedKeys.getInt(1);
                }
            }
            else{
                System.out.println("Something went wrong while adding the Venue");
            }

            String sql = "insert into Event (event_name, event_date, event_time, venue_id, total_seats, available_seats, ticket_price, event_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, event_name);
            stmt.setDate(2, Date.valueOf(event_date));
            stmt.setTime(3, Time.valueOf(event_time));
            stmt.setInt(4, venue_id);
            stmt.setInt(5, total_seats);
            stmt.setInt(6, total_seats);
            stmt.setDouble(7, ticket_price);
            stmt.setString(8, event_type);
            int rowsAdded = stmt.executeUpdate();
            int eventId = -1;
            if(rowsAdded > 0){
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if(generatedKeys.next())
                {
                   eventId =  generatedKeys.getInt(1);
                }
                event = new Event(
                        event_name,
                        event_date,
                        event_time,
                        venue,
                        total_seats,
                        ticket_price,
                        event_type
                );
                event.setEvent_id(eventId);
                System.out.println("Event Created Successfully");
            }
            else{
                System.out.println("Something WENT Wrong");
            }


        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        finally {
            try{
                con.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return event;
    }
    @Override
    public void getEventDetails(Event event) throws EventNotFoundException{

        if(event != null){
            event.display_event_details();
        }
        else{
            throw new EventNotFoundException("Event Not found");
        }

    }
    @Override
    public int getAvailableNoOfTickets(Event event) throws EventNotFoundException{
        int tickets = 0;
        if(event != null){
            tickets = event.getAvailable_seats();
        }
        else{
            throw new EventNotFoundException("Event Not found");
        }
        return tickets;

    }
    @Override
    public void calculate_booking_cost(Event event, int num_tickets, Booking booking) throws EventNotFoundException{

        if(event == null){
            throw new EventNotFoundException("Event Not found");
        }
        double totalCost = event.getTicket_price() * num_tickets;
        booking.setTotal_cost(totalCost);

    }
    @Override
    public Booking book_tickets(Event event, int num_tickets, List<Customer> customers) throws EventNotFoundException{
        Booking booking = null;
        Connection con = null;
        if(event == null){
            throw new EventNotFoundException("Event Not found");
        }

        if(num_tickets == 0){
            throw new EventNotFoundException("Enter valid no of Tickets");
        }

        if(customers.isEmpty()){
            throw new EventNotFoundException("Customer List can't be empty");
        }

        if(event.getAvailable_seats() < num_tickets)
        {
            throw new EventNotFoundException("Only " + event.getAvailable_seats() + "Remaining");
        }

        try{
            con = DBUtil.getDBConn();

            con.setAutoCommit(false);

            String addCustomers = "insert into customer(email, phone_number, customer_name) VALUES(?,?,?)";
            PreparedStatement stmt = con.prepareStatement(addCustomers, Statement.RETURN_GENERATED_KEYS);
            int customerId = 0;
            boolean customerAdded = false;
            for(int i=0; i<customers.size(); i++){
                Customer c = customers.get(i);
                stmt.setString(1, c.getEmail());
                stmt.setString(2, c.getPhone_number());
                stmt.setString(3, c.getCustomer_name());
                int rowsAffected = stmt.executeUpdate();
                if(rowsAffected > 0){
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if(generatedKeys.next()){

                        if(i==0){
                            customerId = generatedKeys.getInt(1);
                        }


                    }
                    customerAdded = true;
                }
                else{
                    System.out.println("SOMETHING WENT WRONG WHILE ADDING CUSTOMERS");
                    return booking;
                }
            }
            if(!customerAdded){
                System.out.println("SOMETHING WENT WRONG WHILE ADDING CUSTOMERS");
                return booking;
            }

            String addBookings = "insert into booking (customer_id, event_id, num_tickets, total_cost, booking_date)\n" +
                    "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement smt = con.prepareStatement(addBookings, Statement.RETURN_GENERATED_KEYS);
            smt.setInt(1, customerId);
            smt.setInt(2, event.getEvent_id());
            smt.setInt(3, num_tickets);
            smt.setDouble(4, event.getTicket_price() * num_tickets);
            smt.setDate(5, Date.valueOf(LocalDate.now()));

            int rowsAffected = smt.executeUpdate();
            if(rowsAffected > 0){
                int bookingId = -1;

                ResultSet genereatedKeys = smt.getGeneratedKeys();
                if(genereatedKeys.next())
                {
                    bookingId = genereatedKeys.getInt(1);
                }

                booking = new Booking(
                        event,
                        customers,
                        num_tickets,
                        event.getTicket_price() * num_tickets,
                        LocalDate.now()
                );

                booking.setBookingId(bookingId);

            }
            else{
                System.out.println("Something Went Wrong while Booking");
                return booking;
            }

            //updating event

            String updateSeatsSql = "UPDATE Event SET available_seats = available_seats - ? WHERE event_id = ? AND available_seats >= ?";
            PreparedStatement emt = con.prepareStatement(updateSeatsSql);
            emt.setInt(1, num_tickets);
            emt.setInt(2, event.getEvent_id());
            emt.setInt(3, num_tickets);

            int rowsUpdated = emt.executeUpdate();
            if(rowsUpdated == 0){
                throw new EventNotFoundException("Something went wrong while booking event");
            }


            con.commit();
            System.out.println("Booking Successfull!!!!");


        }
        catch (SQLException e){
            e.printStackTrace();
        }

        finally {
            if (con != null) {
                try {
                    con.close(); // Close the connection
                } catch (SQLException finalEx) {
                    System.err.println("Error closing connection: " + finalEx.getMessage());
                }
            }
        }

        return booking;
    }
    @Override
    public void cancel_tickets(int booking_id) throws InvalidBookingException {
        Connection con = null;
        Booking booking = null;
        if(booking_id == 0 || booking_id < 0){
            throw new InvalidBookingException("Booking Id Can't be 0");
        }

        try{

            con = DBUtil.getDBConn();

            con.setAutoCommit(false);

            String sql = "Select from booking WHERE booking_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, booking_id);
           int eventId = 0;
           int num_tickets = 0;
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                eventId = rs.getInt("event_id");
                num_tickets = rs.getInt("num_tickets");

            }
            else{
                System.out.println("Failed to fetch Events");
                return;
            }

            String sql2 = "Update Event SET available_seats = available_seats + ? WHERE event_id = ?";
            PreparedStatement stmt2 = con.prepareStatement(sql2);
            stmt2.setInt(1, num_tickets);
            stmt2.setInt(2, eventId);

            int rowsUpdated = stmt2.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("Tickets Allocated");
            }
            else{
                System.out.println("Something went wrong while updating event seats");
                return;
            }

            String sql3 = "Delete From Booking WHERE booking_id = ?";
            PreparedStatement stmt3 = con.prepareStatement(sql3);
            stmt3.setInt(1, booking_id);

            int rowsDeleted = stmt3.executeUpdate();
            if(rowsDeleted > 0){
                System.out.println("Booking canceled Successfully");
            }
            else{
                throw new InvalidBookingException("Booking Not FOund");
            }


            con.commit();


        }

        catch (SQLException e){
            e.printStackTrace();
        }

        finally {
            if (con != null) {
                try {
                    con.close(); // Close the connection
                } catch (SQLException finalEx) {
                    System.err.println("Error closing connection: " + finalEx.getMessage());
                }
            }

    }

    }
    @Override
    public void get_booking_details(int booking_id) throws InvalidBookingException
    {
        Connection con = null;
        Booking booking = null;

        try{
            con = DBUtil.getDBConn();
            String sql = "SELECT * FROM Booking " +
                    "JOIN Event ON Booking.event_id = Event.event_id JOIN Venue ON Event.venue_id = Venue.venue_id " +
                    "JOIN Customer ON Booking.customer_id = Customer.customer_id " +
                    "WHERE Booking.booking_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, booking_id);
            ResultSet rs = stmt.executeQuery();

            List<Customer> customers = new ArrayList<>();
            Event event = null;
            int num_tickets = 0;
            double total_cost = 0.0;
            LocalDate booking_date = null;

            while (rs.next()) {
                if (event == null) {
                    String eventName = rs.getString("event_name");
                    LocalDate eventDate = rs.getDate("event_date").toLocalDate();
                    LocalTime eventTime = rs.getTime("event_time").toLocalTime();
                    String venueName = rs.getString("venue_name");

                    Venue venue = new Venue(venueName, "");
                    int totalSeats = rs.getInt("total_seats");
                    double ticketPrice = rs.getDouble("ticket_price");
                    String eventType = rs.getString("event_type");
                    event = new Event(eventName, eventDate, eventTime, venue, totalSeats, ticketPrice, eventType);
                    num_tickets = rs.getInt("num_tickets");
                    total_cost = rs.getDouble("total_cost");
                    booking_date = rs.getDate("booking_date").toLocalDate();
                }


                String customer_name = rs.getString("customer_name");
                String email = rs.getString("email");
                String phone_number = rs.getString("phone_number");
                Customer customer = new Customer(customer_name, email, phone_number);
                customers.add(customer);
            }

            if (event == null) {
                throw new InvalidBookingException("No booking found for ID: " + booking_id);
            }

            booking = new Booking(event, customers, num_tickets, total_cost, booking_date);
            booking.display_booking_details();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException finalEx) {
                    System.err.println("Error closing connection: " + finalEx.getMessage());
                }
            }
        }
    }





}
