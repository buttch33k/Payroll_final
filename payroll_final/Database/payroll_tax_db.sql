

create table Taxtable_Table(
tax_id int auto_increment,
tax_rate int,
tax_percent double,
tax_excess int,
primary key(tax_id)
);
insert into Taxtable_Table(tax_rate,tax_percent,tax_excess)

values(250000,.20,0),( 400000,.25,30000),(800000,.30,130000),(2000000,.32,490000),(8000000,.35,2410000);
drop table Taxtable_Table;
DELIMITER \\
CREATE PROCEDURE allTax()
BEGIN
SELECT tax_rate,tax_percent,tax_excess FROM Taxtable_Table;
END \\
DELIMITER ;
call allTax();

drop procedure checklogz;


delimiter \\
create procedure callTax()
begin
select tax_rate, tax_percent, tax_excess from taxtable_table;
end \\
delimiter ;

call callTax();