-- Active: 1742406895177@@127.0.0.1@3306@ticketbookingsystem
use TicketBookingSystem;
create table Venue (
    venue_id int(10) AUTO_INCREMENT PRIMARY KEY,
    venue_name varchar(25),
    address varchar(100)
);
create table Event (
    event_id int AUTO_INCREMENT PRIMARY KEY,
    event_date DATE,
    event_time TIME,
    venue_id int(10),
    total_seats int(100),
    available_seats int(100),
    ticket_price DECIMAL,
    event_type ENUM('Movie', 'Sports', 'Concert') NOT NULL,
    booking_id int(10),
    FOREIGN KEY (venue_id) REFERENCES Venue (venue_id)
);

create table customer (
    customer_id int PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(20) UNIQUE NOT NULL,
    phone_number int(10) UNIQUE NOT NULL
);
create table booking (
    booking_id int PRIMARY KEY AUTO_INCREMENT,
    customer_id int,
    event_id int,
    num_tickets int(50),
    total_cost int(50),
    booking_date DATE,
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
    FOREIGN KEY (event_id) REFERENCES Event (event_id)
);
Alter Table Event add CONSTRAINT FOREIGN KEY(booking_id) REFERENCES booking(booking_id);
Alter table Customer add column customer_name varchar(255);
Alter table Customer add column booking_id int;
Alter table Customer add CONSTRAINT FOREIGN KEY(booking_id) REFERENCES booking(booking_id);
