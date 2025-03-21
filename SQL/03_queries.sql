select event_name from event;
select event_name, available_seats from event
WHERE available_seats > 0;

select event_name from event WHERE event_name LIKE '%cup%';

select event_name, ticket_price from event 
WHERE ticket_price BETWEEN 1000 AND 2500;

select event_name, event_date from event WHERE event_date BETWEEN '2025-06-01' AND '2025-08-01';

select event_name, available_seats, event_type from event 
WHERE 
available_seats > 0 
AND 
event_type = "Concert";

SELECT *
FROM customer
LIMIT 5 OFFSET 5;

select * from booking WHERE num_tickets > 4;

select booking.booking_id, customer.customer_name from booking JOIN customer ON booking.customer_id = customer.customer_id where booking.num_tickets >= 4; 

select customer_id, customer_name, phone_number, email from customer WHERE phone_number LIKE "%000";

select * from event WHERE total_seats > 15000;

select * from event WHERE event_name NOT LIKE "x%" 
AND event_name NOT LIKE "y%"
AND event_name NOT LIKE "z%";

select event_name, avg(ticket_price) from event  
GROUP BY event_name;

select event.event_name, SUM(booking.total_cost) as Total_Revenue from booking JOIN event ON event.event_id = booking.event_id GROUP BY event.event_name;

select event.event_name, SUM(booking.num_tickets) as TotalTicketSales from booking JOIN event ON booking.event_id = event.event_id GROUP BY event.event_name ORDER BY TotalTicketSales DESC LIMIT 1;

select event.event_name, SUM(booking.num_tickets) as TotalTicketSales from booking JOIN event ON booking.event_id = event.event_id GROUP BY event.event_name;

ALTER TABLE event add column (booking_id int);

select * from booking;

update event Set booking_id = event_id WHERE event_id>=2;
select * from event;

select event_name as eventWithNoSales FROM event where booking_id IS NULL OR booking_id=0; 

select customer.customer_name, SUM(booking.num_tickets) as UserWithMostTickets from customer JOIN booking ON customer.customer_id = booking.customer_id GROUP BY customer_name ORDER BY UserWithMostTickets DESC LIMIT 1;;

select event.event_name, DATE_FORMAT(booking.booking_date, '%Y-%m') as month, SUM(booking.num_tickets) as total_tickets_sold from booking JOIN event ON booking.event_id=event.event_id GROUP BY event.event_name, DATE_FORMAT(booking.booking_date, '%Y-%m'); 

select * from event;

select venue.venue_name, AVG(event.ticket_price) from event JOIN venue ON event.venue_id = venue.venue_id GROUP BY venue.venue_name, event.event_name;

select event.event_type, SUM(booking.num_tickets) as TicketsSold from booking JOIN event ON
booking.event_id = event.event_id GROUP BY event.event_type;

select event.event_name, DATE_FORMAT(booking.booking_date, "%Y"), SUM(booking.total_cost) as totalRevenue from booking JOIN event ON 
booking.event_id = event.event_id GROUP BY event.event_name,  DATE_FORMAT(booking.booking_date, "%Y");

select customer.customer_name, booking.customer_id from booking JOIN customer ON booking.customer_id = customer.customer_id 
GROUP BY customer.customer_name, booking.customer_id HAVING COUNT(DISTINCT booking.event_id) > 1;

select customer.customer_name, SUM(booking.total_cost) from booking JOIN customer ON booking.customer_id = customer.customer_id GROUP BY customer.customer_name;

select event.event_type, venue.venue_name, AVG(event.ticket_price) from event JOIN venue ON event.venue_id = venue.venue_id GROUP BY event.event_type, venue.venue_name;  

select customer.customer_name, SUM(booking.num_tickets) as totalTickets from booking JOIN customer ON booking.customer_id = customer.customer_id WHERE 
booking.booking_date >= ADDDATE(CURDATE(), INTERVAL -30 DAY) GROUP BY 
customer.customer_name;

select venue.venue_name, (select AVG(event.ticket_price) from event where event.venue_id = venue.venue_id) as TotalAvg from venue;

select e.event_name from event e WHERE (select SUM(b.num_tickets)
from booking b 
WHERE b.event_id = e.event_id) > (0.5 * e.total_seats);

Select event.event_name, (Select SUM(booking.num_tickets) from booking WHERE booking.event_id = event.event_id) as TotalTicketSales from event

select event.event_id, event.event_name from event WHERE event.event_id NOT IN(select booking.event_id from booking);

Select customer.customer_name from customer WHERE NOT EXISTS(Select booking.booking_id from booking where booking.customer_id = customer.customer_id);

Select t.event_type, SUM(t.tickets_sold) AS total_tickets_sold FROM (
    Select event.event_type, SUM(booking.num_tickets) AS tickets_sold from event LEFT JOIN booking ON event.event_id = booking.event_id GROUP BY event.event_id, event.event_type
) t GROUP BY t.event_type;

Select event.event_name, event.ticket_price from event WHERE ticket_price > (select AVG(event.ticket_price) from event);

Select customer.customer_name, (Select SUM(booking.total_cost) FROM booking where booking.customer_id = customer.customer_id) as total_Revenue from Customer;

Select customer.customer_name from customer WHERE customer.customer_id IN (select booking.customer_id from booking JOIN event ON booking.event_id = event.event_id WHERE event.venue_id=3);

Select event.event_type, SUM(booking.num_tickets) as totalTicektsSold from booking JOIN event ON booking.event_id = event.event_id GROUP BY event.event_type;

Select customer.customer_name, DATE_FORMAT(booking.booking_date, "%Y - %M") as Month, SUM(booking.num_tickets) as total_tickets  from booking JOIN customer ON booking.customer_id = customer.customer_id GROUP BY customer.customer_id, Month; 

Select venue.venue_name, (Select AVG(event.ticket_price) from event WHERE event.venue_id = venue.venue_id) from venue;

select * from event;