create database payroll_final;
use payroll_final;

drop table employee_Table;
drop table payroll_Table;



create table payroll_Table(
salary_id int auto_increment,
job_title varchar(255),
salary int,

primary key(salary_id)
);

insert into payroll_Table(job_title,salary)
values("admin", 15000),( "Junior_programmer", 30000),("Senior_programmer", 50000);

create table employee_Table(
emp_id int auto_increment,
username varchar(255),
passwords varchar(255),
emp_type int,
status_id int,
last_name varchar(45) DEFAULT NULL,
first_name varchar(45) DEFAULT NULL,
dob date DEFAULT NULL,
age int not null,
gender varchar(45) DEFAULT NULL,
country varchar(45) DEFAULT NULL,
city varchar(45) DEFAULT NULL,
address varchar(45) DEFAULT NULL,
email varchar(45) DEFAULT NULL,
mobile_no varchar(10) DEFAULT NULL,
primary key(emp_id),
foreign key(emp_type)references roleType_Table(emp_type),
foreign key(status_id)references userStatus_table(status_id)
);

insert into employee_Table(username, passwords, emp_type, status_id,last_name,first_name,dob,age,gender,country,city,address,email,mobile_no )
values
('q', 'q',2,1,"lacsamana", "neil","1995-09-17",23,"male","ph","makati", "cavite camella","nlacsama09@gmail.com","9157018454"),
('w', 'w',1,1,"nanquil", "aly","1995-09-17",21,"female","ph","makati", "cavite camella","nlacsama09@gmail.com","9157018454"),
('e', 'e',2,1,"rivera", "reese","1995-09-17",21,"male","ph","makati", "cavite camella","nlacsama09@gmail.com","9157018454"),
('r', 'r',1,1,"miranda", "vivian","1995-09-17",21,"female","ph","makati", "cavite camella","nlacsama09@gmail.com","9157018454");

create table roleType_Table(
emp_type int,
emp_role varchar(100),
primary key(emp_type)
);

insert into roleType_Table
values(1, 'admin'),(2, 'employee');


create table userStatus_table(
status_id int,
activity_status varchar(100),
primary key(status_id)
);

insert into userStatus_table
values(1, 'ACTIVE'),(2, 'INACTIVE'),(3, 'DEACTIVATED');




/*check Login admin/emp*/
DELIMITER \\
CREATE PROCEDURE checklog()
BEGIN
SELECT username, passwords , emp_type FROM employee_able;
END \\
DELIMITER ;

/*View pending inactive*/
delimiter \\
create procedure viewAllInactiveUser()
begin
select emp_id, last_name, first_name, email, activity_status from employee_Table
join userStatus_table
on employee_Table.status_id = employee_Table.status_id;
end \\
delimiter ;
call viewAllInactiveUser();
drop procedure viewAllInactiveUser;

