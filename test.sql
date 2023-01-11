show databases;
create database temp;
use temp;

show tables;

create table ex(
id integer auto_increment primary key,
name varchar(10),
cost integer
);

truncate table ex;
desc ex;
select * from ex;

insert into ex(name) values('s');

delimiter $$
create procedure costpro()
begin
insert into ex(name) values('s');
end
$$
delimiter ;

drop procedure costpro;

call costpro();

DELIMITER //  
Create Trigger cost
before INSERT ON ex FOR EACH ROW  
BEGIN 
set new.cost=10;
END //  

delimiter $$
create procedure costpro()
begin
DELIMITER //  
Create Trigger cost
before INSERT ON ex FOR EACH ROW  
BEGIN 
set new.cost=10;
END //  
end
$$
delimiter ;



drop trigger cost;

create table student(
id integer auto_increment primary key,
sub1 integer,
sub2 integer,
sub3 integer,
total integer,
per integer
);

delimiter $$
create procedure sample()
begin
insert into student(sub1,sub2,sub3) values(10,20,30);
end
$$
delimiter ;

drop procedure sample;

call sample();

select * from student;

drop trigger stud_marks;

DELIMITER // 
create trigger stud_marks 
before INSERT 
on 
student 
for each row 
set new.total = new.sub1 + new.sub2 + new.sub3, new.per = new.total * 60 / 100;
END 
// 
DELIMITER ;