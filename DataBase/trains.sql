-- set that database as active
use irctcproject;

-- create trains table-->used to store train details
create table trains(
train_no varchar(10) primary key,
train_name varchar(50),
start_seat integer,
end_seat integer,
admin_id varchar(50),
foreign key(admin_id)references admin(admin_id)
);

-- create schedule table
create table schedule(
train_no varchar(10) primary key,
mon varchar(5),
tue varchar(5),
wed varchar(5),
thur varchar(5),
fri varchar(5),
sat varchar(5),
sun varchar(5),
foreign key (train_no) references trains(train_no) on delete cascade
);

-- insert train details
insert into trains values('16545','YPR HPT EXP',0,30,'suchith');
insert into trains values('12649','Sampark Kranti',0,30,'suchith');
insert into trains values('16592','Hampi Express',0,30,'suchith');
insert into trains values('16591','Hampi Express',0,30,'suchith');
insert into trains values('22691','Rajdhani Express',0,30,'suchith');
insert into trains values('20607','CHENNAI MYSURU Vande Bharat Express',0,30,'suchith');
insert into trains values('16217','Mys Snsi Express',0,30,'suchith');
insert into trains values('12627','Karnataka Express',0,30,'suchith');
insert into trains values('16546','HPT YPR EXP',0,30,'shashidhar');
insert into trains values('12628','Karnataka Express',0,30,'shashidhar');
insert into trains values('12864','Ypr Howrah Express',0,30,'shashidhar');
insert into trains values('22626','Mas Double Decker',0,30,'shashidhar');
insert into trains values('12658','Chennai Mail',0,30,'shashidhar');
insert into trains values('16587','Ypr Bkn Express',0,30,'shashidhar');
insert into trains values('11302','Udyan Express',0,30,'shashidhar');

-- retreive train details
select * from trains;

-- schedule for trains
insert into schedule values('11302','y','y','y','y','y','y','y');
insert into schedule values('12627','y','y','y','y','y','y','y');
insert into schedule values('12628','y','y','y','y','y','y','y');
insert into schedule values('12649','y','n','y','n','y','y','y');
insert into schedule values('12658','y','y','y','y','y','y','y');
insert into schedule values('12864','y','y','y','y','y','y','y');
insert into schedule values('16217','y','n','n','n','n','n','n');
insert into schedule values('16545','y','y','y','y','y','y','y');
insert into schedule values('16546','y','y','y','y','y','y','y');
insert into schedule values('16587','n','n','n','n','y','n','y');
insert into schedule values('16591','y','y','y','y','y','y','y');
insert into schedule values('16592','y','y','y','y','y','y','y');
insert into schedule values('20607','y','y','n','y','y','y','y');
insert into schedule values('22626','y','y','y','y','y','y','y');
insert into schedule values('22691','y','y','y','y','y','y','y');

-- to retreive train schedule
select * from schedule;

-- STORED PROCEDURE

delimiter $$
	create procedure showtrains ()
    begin
		select train_no ,train_name from trains;
        end $$
	delimiter ;
    
call showtrains();
    