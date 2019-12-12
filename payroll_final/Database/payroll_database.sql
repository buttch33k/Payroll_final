create database payroll_final;
use payroll_final;

create table employee_Table(
emp_id int auto_increment,
username varchar(255),
passwords varchar(255),
emp_type int,
status_id int,
job_id int,
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
foreign key(status_id)references userStatus_table(status_id),
foreign key(job_id)references JobTitle_Table(job_id)
);

insert into employee_Table(username, passwords, emp_type, status_id, job_id,last_name,first_name,dob,age,gender,country,city,address,email,mobile_no )
values
('q', 'q',1,1,1,"lacsamana", "neil","1995-09-17",23,"male","ph","makati", "cavite camella","nlacsama09@gmail.com","9157018454"),
('w', 'w',1,2,2,"nanquil", "aly","1995-09-17",21,"female","ph","makati", "cavite camella","nlacsama09@gmail.com","9157018454"),
('e', 'e',2,1,3,"rivera", "reese","1995-09-17",21,"male","ph","makati", "cavite camella","nlacsama09@gmail.com","9157018454"),
('r', 'r',1,2,4,"miranda", "vivian","1995-09-17",21,"female","ph","makati", "cavite camella","nlacsama09@gmail.com","9157018454");

create table JobTitle_Table(
job_id int,
job_code varchar(225) default null,
job_title varchar(255) DEFAULT NULL,
job_salary int DEFAULT NULL,
PRIMARY KEY (job_id)
);

INSERT INTO JobTitle_Table(job_id,job_code,job_title,job_salary)
VALUES 
(1,'JT1','Newly Hired',0),(2,'JT2','Janitor',25000),
(3,'JT3','programmer',30000),(4,'JT4','senior programmer',40000),(5,'JT5','senior janitor',45000),(6,'JT6','senior janitor',50000);

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

create table Attendance_NowTI(
employee_id int not null,
date varchar(255) not null,
time_in varchar(255) default null,
foreign key(employee_id)references employee_Table(emp_id)
);

insert into Attendance_NowTI(employee_id, date, time_in)values
(5, CURDATE(), "8:01 am");

create table timeout(
e_id int not null,
time_out varchar(255) default null,
no_of_hours long default null,
FOREIGN KEY (e_id) REFERENCES employee_Table(emp_id)
);

insert into timeout()values
(5,"5:00pm",8);

-- #foreign key
-- ALTER TABLE  Attendance_NowTI
-- ADD constraint fk_employee_id
-- FOREIGN KEY (employee_id) REFERENCES employee_Table(emp_id);

-- ALTER TABLE  timeout
-- ADD constraint fk_e_id
-- FOREIGN KEY (e_id) REFERENCES employee_Table(emp_id);

