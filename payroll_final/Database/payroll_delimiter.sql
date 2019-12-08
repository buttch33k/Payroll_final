/*check Login admin/emp*/
DELIMITER \\
CREATE PROCEDURE checklogz()
BEGIN
SELECT username, passwords , emp_type,status_id,emp_id,last_name FROM employee_Table;
END \\
DELIMITER ;
call checklogz();
drop procedure checklogz;

/*View pending inactive*/
delimiter \\
create procedure viewAllInactiveUser()
begin
select emp_id, last_name, first_name, email, activity_status from employee_Table
join userStatus_table
on employee_Table.status_id = userStatus_table.status_id;
end \\
delimiter ;
call viewAllInactiveUser();
drop procedure viewAllInactiveUser;

/*clock*/
DELIMITER &&
CREATE PROCEDURE insertAttend(in empID int, in petsa varchar(255), in pumasok varchar(255), IN timeout varchar(255), IN totalhours varchar(255))
BEGIN
INSERT INTO Attendance_Now(employee_id, date, time_in,time_out,no_of_hours) VALUES (empID, petsa,pumasok,timeout, totalhours);
SELECT * FROM Attendance_Now;
END &&
DELIMITER ;
drop procedure insertAttend;

select A.emp_id, A.username, A.passwords, B.date, B.time_in,B.time_out,B.no_of_hours 
from employee_Table A inner join Attendance_Now B on A.emp_id= B.employee_id;

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





