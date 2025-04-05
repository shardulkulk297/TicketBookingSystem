insert into Venue (venue_name, address) VALUES 
("Raj Auditoriam", "123 MG Road Pune"),
("Vasudev Kala Mandir", "456 Sangli Road, Miraj"),
("Rangmanch", "Sector 12 Mumbai"),
("DY Patil Cricket Stadium", "Mumbai Pune Highway Pune"),
("Sangeet Bhavan", "88 Opera Street, Mumbai"),
("Lok Kala Kendra", "15 MG Road Kolhapur"),
("Shahu Event Place", "Shahupuri, Kolhapur"),
("Bhalchandra Talkies", "Chandni chouk, Kolhapur"),
("Shilp Sadan", "9 East end, Mumbai"),
("Nritya Kala Mandir", "25 Kothrud, Pune");

insert into Event (event_name, event_date, event_time, venue_id, total_seats, available_seats, ticket_price, event_type) VALUES
('Bollywood Drama', '2025-05-01', '19:00:00', 1, 100, 80, 500.00, 'Concert'),
('Cricket Championship Cup', '2025-05-10', '14:30:00', 4, 200, 150, 2000.00, 'Sports'),
('Tollywood Blockbuster Premiere', '2025-06-05', '18:45:00', 3, 150, 100, 600.00, 'Movie'),
('Indian Classical Music Night', '2025-06-15', '20:00:00', 4, 120, 90, 1000.00, 'Concert'),
('Kabaddi League Opener', '2025-07-01', '16:00:00', 5, 180, 130, 650.00, 'Sports'),
('South Indian Film Festival', '2025-07-10', '15:30:00', 6, 160, 140, 1000.00, 'Movie'),
('Rock Concert India', '2025-08-01', '21:00:00', 7, 250, 200, 800.00, 'Concert'),
('Indian Premier League Cup', '2025-08-15', '17:00:00', 4, 300, 250, 1500.00, 'Sports'),
('Hindi Film Soiree', '2025-09-05', '19:30:00', 9, 140, 110, 700.00, 'Movie'),
('Fusion Music Fest', '2025-09-20', '20:30:00', 10, 220, 190, 850.00, 'Concert');

insert into customer(email, phone_number, customer_name, booking_id) VALUES
('arjun.patil@example.com', 9876543210, 'Arjun Patil', 0),
('shardul@example.com', 9876543211, 'Shardul', 0),
('ravi.kumar@example.com', 9876543212, 'Ravi Kumar', 0),
('neha.singh@example.com', 9876543213, 'Neha Singh', 0),
('nitishreddy@example.com', 9876543214, 'Nitish Reddy', 0),
('Rohit sharma', 9876543215, 'Rohit sharma', 0),
('rahuldravid@example.com', 9876543216, 'Rahul Dravid', 0),
('pooja.reddy@example.com', 9876543217, 'Pooja Reddy', 0),
('suresh.menon@example.com', 9876543218, 'Suresh Menon', 0),
('anjali.gupta@example.com', 9876543219, 'Anjali Gupta', 0);

insert into booking (customer_id, event_id, num_tickets, total_cost, booking_date)
VALUES
  (2, 8, 2, 3000, '2025-04-01'),
  (2, 2, 1, 2000, '2025-04-02'),
  (3, 3, 3, 1800, '2025-04-03'),
  (4, 4, 2, 2000, '2025-04-04'),
  (5, 5, 4, 2600, '2025-04-05'),
  (6, 6, 2, 2000, '2025-04-06'),
  (7, 7, 3, 2400, '2025-04-07'),
  (8, 8, 1, 1500, '2025-04-08'),
  (9, 9, 2, 1400, '2025-04-09'),
  (10,10, 4, 3400, '2025-04-10');


insert into booking (customer_id, event_id, num_tickets, total_cost, booking_date) 
VALUES
(1, 1, 2, 1000, '2025-04-11'),
(1, 2, 1, 2000, '2025-04-12'),
(1, 3, 3, 1800, '2025-04-13'),
(1, 8, 1, 1500, '2025-04-02'),

(2, 4, 2, 2000, '2025-04-14'),
(2, 5, 4, 2600, '2025-04-15'),
(2, 6, 2, 2000, '2025-04-17'),
(2, 8, 1, 1500, '2025-04-02'),

(3, 9, 2, 1400, '2025-04-19'),
(4, 10, 4, 3400, '2025-04-20');

update customer SET booking_id = 11 WHERE customer_id = 1;
select * from customer;





