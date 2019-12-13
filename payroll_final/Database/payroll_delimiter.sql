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

DELIMITER &&
CREATE PROCEDURE insertTI(in empID int, in petsa varchar(255), in pumasok varchar(255), in buwan int)
BEGIN
INSERT INTO Attendance(employee_id, date, time_in, month_) VALUES (empID, petsa,pumasok, buwan);
SELECT * FROM Attendance;
END &&
DELIMITER ;

drop procedure insertTI;

Delimiter &&
create procedure inserTO(IN timeout varchar(255), IN totalhours varchar(255))
begin
insert into attendance(time_out,totalhr) values (timeout,totalhours);
end &&
delimiter ;
drop procedure inserto;






