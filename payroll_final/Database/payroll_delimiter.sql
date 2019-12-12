/*check Login admin/emp*/
DELIMITER \\
CREATE PROCEDURE checklogz()
BEGIN
SELECT username, passwords , emp_type,status_id,emp_id,last_name FROM employee_Table;
END \\
DELIMITER ;
call checklogz();
drop procedure checklogz;

/*View STATUS OF EMPLOYEE*/
delimiter \\
create procedure viewAllsTATUSUser()
begin
select emp_id, last_name, first_name, email, activity_status from employee_Table
join userStatus_table
on employee_Table.status_id = userStatus_table.status_id;
end \\
delimiter ;
call viewAllsTATUSUser();
drop procedure viewAllInactiveUser;

/*View tYPE OF EMPLOYEE*/
delimiter \\
create procedure viewAllTypeUser()
begin
select emp_id, last_name, first_name, email, emp_role from employee_Table
join roleType_Table
on employee_Table.emp_type = roleType_Table.emp_type;
end \\
delimiter ;
call viewAllTypeUser();
drop procedure viewAllInactiveUser;


/*View all job by employee*/
delimiter \\
create procedure viewAlljob()
begin
select employee_Table.emp_id, employee_Table.last_name, employee_Table.first_name, employee_Table.email, JobTitle_Table.job_code from employee_Table
join JobTitle_Table
on employee_Table.job_id = JobTitle_Table.job_id;
end \\
delimiter ;
call viewAlljob();
drop procedure viewAlljob;

-- view all job
delimiter \\
create procedure addjobz()
begin
select job_id,job_code,job_title,job_salary from JobTitle_Table;
end \\
delimiter ;
call addjobz;

delimiter \\
create procedure ccheckti()
begin
select *
from Attendance_NowTI;
end \\
delimiter ; 
call ccheckti;


DELIMITER &&
CREATE PROCEDURE insertTI(in empID int, in petsa varchar(255), in pumasok varchar(255))
BEGIN
INSERT INTO Attendance_NowTI(employee_id, date, time_in) VALUES (empID, petsa,pumasok);
SELECT * FROM Attendance_NowTI;
END &&
DELIMITER ;

drop procedure insertti;

Delimiter &&
create procedure inserTO(in empid int,IN timeout varchar(255), IN totalhours varchar(255))
begin
insert into timeout(e_id, time_out, no_of_hours) values (empid, timeout, totalhours);
select * from timeout;
end &&
delimiter ;
drop procedure inserTO;



delimiter \\
create procedure viewtime()
begin
select A.emp_id, A.username, A.passwords, A.emp_type, B.date, B.time_in,C.time_out,C.no_of_hours 
from employee_Table A inner join Attendance_NowTI B on A.emp_id= B.employee_id join timeout C on A.emp_id = C.e_id;
end \\
delimiter ;
call viewtime;


delimiter \\
create procedure checktimeindate()
begin
select employee_id,  date from Attendance_NowTI;
end \\
delimiter ;
drop procedure checktimeindate;
call checktimeindate();

select A.emp_id, A.username, A.passwords, A.emp_type, B.date, B.time_in,C.time_out,C.no_of_hours 
from employee_Table A inner join Attendance_NowTI B on A.emp_id= B.employee_id join timeout C on A.emp_id = C.e_id;



Select * from employee_Table e INNER join JobTitle_Table j on e.emp_id =j.job_id where emp_id = 1;


