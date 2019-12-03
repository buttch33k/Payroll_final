create database payroll_final;
use payroll_final;

drop table employee_Table;
drop table payroll_Table;


create table employee_Table(
emp_id int auto_increment,
username varchar(255),
passwords varchar(255),
emp_type int,
status_id int,
job_id_emp varchar(45) DEFAULT NULL,
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
KEY job_id_idx (job_id_emp),
foreign key(emp_type)references roleType_Table(emp_type),
foreign key(status_id)references userStatus_table(status_id)

);


insert into employee_Table(username, passwords, emp_type, status_id, job_id_emp,last_name,first_name,dob,age,gender,country,city,address,email,mobile_no )
values
('q', 'q',2,1,"jt1","lacsamana", "neil","1995-09-17",23,"male","ph","makati", "cavite camella","nlacsama09@gmail.com","9157018454"),
('w', 'w',1,2,"jt2","nanquil", "aly","1995-09-17",21,"female","ph","makati", "cavite camella","nlacsama09@gmail.com","9157018454"),
('e', 'e',2,1,"jt3","rivera", "reese","1995-09-17",21,"male","ph","makati", "cavite camella","nlacsama09@gmail.com","9157018454"),
('r', 'r',1,2,"jt4","miranda", "vivian","1995-09-17",21,"female","ph","makati", "cavite camella","nlacsama09@gmail.com","9157018454");



create table JobTitle_Table(
job_id varchar(10) NOT NULL,
job_title varchar(255) DEFAULT NULL,
job_salary int DEFAULT NULL,
emp_id_job int DEFAULT NULL,
PRIMARY KEY (job_id),
KEY emp_id_idx (emp_id_job),
CONSTRAINT emp_id_job FOREIGN KEY (emp_id_job) REFERENCES employee_Table (emp_id) ON DELETE CASCADE ON UPDATE CASCADE
);
drop table JobTitle_Table;

INSERT INTO JobTitle_Table(job_id,job_title,job_salary,emp_id_job)
VALUES 
('JT1','S/W Dev',4000,1),('JT2','S/W Dev',8000,2),('JT3','S/W Dev',11000,3),('JT4','S/W Dev',15000,4);

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

ALTER TABLE  Attendance_Now
ADD constraint fk_employee_id
FOREIGN KEY (employee_id) REFERENCES employee_Table(emp_id);

create table Attendance_Now(
employee_id int not null,
date varchar(255) not null,
time_in varchar(255) not null,
time_out varchar(255) not null,
no_of_hours long not null
);

drop table Attendance_Now;

insert into Attendance_Now(employee_id, date, time_in,time_out,no_of_hours)values
(5, CURDATE(), "8:01 am","5:00pm",8);









