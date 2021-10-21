CREATE TABLE Train(
train_id varchar(4),
PRIMARY KEY(train_id)
);

CREATE TABLE Station(
station_id int,
station_name varchar(255),
name_of_city varchar(255),
name_of_state varchar(255),
PRIMARY KEY(station_id)
);

CREATE TABLE Schedule(
TransitLinename varchar(255),
Train varchar(255),
Origin varchar(255),
Destination varchar(255),
Stops varchar(255),
Departure_datetime dateTime,
Fare numeric(8,2),
Arrival_datetime dateTime,
travel_time time,
PRIMARY KEY (TransitLinename, Train)
);

CREATE TABLE Customer
( Username varchar(255),
Last_name varchar(255),
First_name varchar(255),
Password varchar(255),
Email_address varchar(255),
PRIMARY KEY(Username)
);

CREATE TABLE Reservation
( Reservation_no int,
Date date,
Passenger varchar(255),
Total_Fare numeric(8,2),
PRIMARY KEY (Reservation_no)
);

CREATE TABLE Employee
( SSN varchar(15),
Username varchar(255),
Password varchar(255),
Last_name varchar(255),
First_name varchar(255),
primary key (SSN)
);

CREATE TABLE STOP
( train_id varchar(4),
station_id varchar(255),
primary key (train_id, station_id),
foreign key (train_id) references Train,
foreign key (station_id) references Station
);

CREATE TABLE Work_for
( train_id varchar(4),
SSN varchar(15),
primary key (train_id, SSN),
foreign key (train_id) references Train,
foreign key (SSN) references Employee
);

CREATE TABLE Follow
( train_id varchar(4),
TransitLinename varchar(255),
Train varchar(255),
primary key (train_id, TransitLinename, Train),
foreign key (train_id) references Train,
foreign key (TransitLinename, Train) references Schedule);

CREATE TABLE Store
( station_id int,
TransitLinename varchar(255),
Train varchar(255),
primary key (station_id, TransitLinename, Train),
foreign key (station_id) references Station,
foreign key (TransitLinename, Train) references Schedule);

CREATE TABLE Work_at
( station_id int,
SSN varchar(15),
primary key (SSN),
foreign key (station_id) references Station,
foreign key (SSN) references Employee);

CREATE TABLE Matching
( TransitLinename varchar(255),
Train varchar(255),
Reservation_no int ,
primary key (TransitLinename, Train, Reservation_no),
foreign key (TransitLinename, Train) references Schedule,
foreign key (Reservation_no) references Reservation);

CREATE TABLE Checking
( SSN varchar(15),
TransitLinename varchar(255),
Train varchar(255),
Reservation_no int,
primary key(SSN, TransitLinename, Train, Reservation_no),
foreign key (SSN) references Employee,
foreign key (TransitLinename, Train) references Schedule,
foreign key (Reservation_no) references Reservation);


CREATE TABLE Making
( Username varchar(255),
TransitLinename varchar(255),
Train varchar(255),
Reservation_no int,
primary key(TransitLinename, Train, Reservation_no),
foreign key (Username) references Customer,
foreign key (TransitLinename, Train) references Schedule,
foreign key (Reservation_no) references Reservation);
