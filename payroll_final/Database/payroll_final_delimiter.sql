/*check Login admin/emp*/
DELIMITER \\
CREATE PROCEDURE checklog()
BEGIN
SELECT username, passwords , emp_type FROM employee_able;
END \\
DELIMITER ;
