drop table Taxtable_Table;

create table Taxtable_Table(
tax_id int auto_increment,
tax_Min int,
tax_Max int,
tax_TaxOnLowerLimit int,
tax_TaxOnExcessOverLimit double,
primary key(tax_id)
);

insert into Taxtable_Table(tax_Min,tax_Max,tax_TaxOnLowerLimit,tax_TaxOnExcessOverLimit)
values(0,250000,0,0),(250000,400000,0,.20),(400000,800000,30000,.25),(800000,2000000,130000,.30),(2000000,8000000,490000,.32),(8000000,8000001,2410000,.35);

drop table Taxtable_Table;
DELIMITER \\
CREATE PROCEDURE allTax()
BEGIN
SELECT tax_Min,tax_Max,tax_TaxOnLowerLimit,tax_TaxOnExcessOverLimit FROM Taxtable_Table;
END \\
DELIMITER ;
call allTax();

drop procedure allTax;
