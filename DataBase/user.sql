-- create a database
create database irctcproject;

-- set that database as active
use irctcproject;

-- create user table
create table user (
user_name varchar(50) primary key,
first_name varchar(30),
last_name varchar(30),
dob varchar(20),
gender varchar(20),
nationality varchar(30),
address varchar(50),
phone varchar(10)
);

-- create user login table 
create table user_login (
user_name varchar(50) primary key,
password varchar(20),
email varchar(50),
foreign key (user_name) references user(user_name) on delete cascade
);

-- table to store pnr information
create table pnr_status(
pnr_no varchar(20) primary key,
train_no varchar(10),
train_name varchar(50),
`from` varchar(50),
`to` varchar(50),
date_of_travel varchar(20),
foreign key (train_no) references trains(train_no) on delete cascade
);

-- table to store booking details of user
create table bookings(
booking_id varchar(20) primary key,
pnr_no varchar(20) ,
user_name varchar(50),
date varchar(20),
foreign key (pnr_no) references pnr_status(pnr_no) on delete cascade,
foreign key (user_name) references user(user_name) on delete cascade
);

-- passenger table
create table passengers(
pnr_no varchar(20),
name varchar(50),
age integer,
gender varchar(20),
seat_no integer,
foreign key (pnr_no) references pnr_status(pnr_no) on delete cascade
);