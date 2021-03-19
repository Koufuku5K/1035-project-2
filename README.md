CSC1035 Project 2 (Team Project)
================================

Circumstances
-------------

Halfway through the project one of our teammates left.
As a result the research he had done into JUnit for testing was never utilised.

Work done by each team member:

Alfie Fields - Database design, hibernate relationships, RoomBooking system

William Moses - Database table creation, hibernate annotations, RoomBooking system

Kyle Anderson - Created Initial classes, methods to add data to table, timetable system

Joseph Burley - Database design, timetable system, HQL query design

Systems
-------

The project has three systems DataIO, RoomBooking and Timetable.

Each system has its own psvm to run separately, they have a very basic interface
for navigation to run their methods.

DataIO System
----------------
Has a menu for user to enable choice between: \
    -Adding new Room \
    -Adding new Module \
    -Adding new Staff member \
    -Adding new Student 
    \
    

Room Booking System
-------------------
Has options to: \
    - Output a list of all the rooms stored in the database \
    - Input data for a booking, to find available rooms then book one \
    - Cancel a booking by giving its ID \
    - View a room timetable returns a list of all the bookings associated with a room \
    - Update a certain field in a room \
    - Get a confirmation of booked room \
    - Exit and close the system

TimeTable System
----------------
Has command line menu to: \
    -Choose A timetable type they would like to view \
    -Enter an ID to get the timetable of a specific item from the table
    -Outputs the query as a timetable
