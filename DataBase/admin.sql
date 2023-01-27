-- set that database as active
use irctcproject;

-- create admin table
create table admin(
admin_id varchar(50) primary key,
first_name varchar(30),
last_name varchar(30),
gender varchar(20),
address varchar(50)
);

-- create admin login table
create table admin_login(
admin_id varchar(50) primary key,
password varchar(20),
email varchar(50),
foreign key (admin_id) references admin(admin_id) on delete cascade
);

-- insert values for admin table

insert into admin values('suchith','Suchith','Kumar','Male','Bangalore');
insert into admin values('shashidhar','Shashidhar','AS','Male','Chintamani');

-- retrieve admin details
select * from admin;

-- insert values for admin table

insert into admin_login values('suchith','suchith','suchithkumaryt@gmail.com');
insert into admin_login values('shashidhar','shashi','shashidhar.shivaraj715@gmail.com');

-- retrieve admin login details
select * from admin_login;