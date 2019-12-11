drop table Taxtable_Table;

create table Taxtable_Table(
tax_id int ,
tax_Min int,
tax_Max int,
tax_TaxOnLowerLimit int,
tax_TaxOnExcessOverLimit double,
primary key(tax_id)
);

insert into Taxtable_Table(tax_id,tax_Min,tax_Max,tax_TaxOnLowerLimit,tax_TaxOnExcessOverLimit)
values(1,0,250000,0,0),(2,250000,400000,0,.20),(3,400000,800000,30000,.25),(4,800000,2000000,130000,.30),(5,2000000,8000000,490000,.32),(6,8000000,8000001,2410000,.35);

DELIMITER \\
CREATE PROCEDURE allTax()
BEGIN
SELECT tax_Min,tax_Max,tax_TaxOnLowerLimit,tax_TaxOnExcessOverLimit FROM Taxtable_Table;
END \\
DELIMITER ;
call allTax();
drop procedure allTax;


create table sss_table(
	sss_id int primary key auto_increment not null,
	sss_min int not null,
    sss_max double not null,
    sss_ec double not null,
    sss_er double not null,
    sss_ee double not null,
    sss_total double not null
);
 
insert into sss_table (sss_min, sss_max, sss_ec, sss_er, sss_ee, sss_total) 
values 
(0, 2250, 10.00, 170.00, 80, 250.00), 
(2250, 2749.99, 10.00, 210.00, 100, 310.00), 
(2750, 3249.99, 10.00, 250.00, 120, 370.00),
(3250, 3749.99, 10.00, 290.00, 140, 430.00),
(3750, 4249.99, 10.00, 330.00, 160, 490.00),
(4250, 4749.99, 10.00, 370.00, 180, 550.00),
(4750, 5249.99, 10.00, 410.00, 200, 610.00),
(5250, 5749.99, 10.00, 450.00, 220, 670.00 ),
(5750, 6249.99, 10.00, 490.00, 240, 730.00),
(6250, 6749.99, 10.00, 530.00, 260, 790.00),
(6750, 7249.99, 10.00, 570.00, 280, 850.00),
(7250, 7749.99, 10.00, 610.00, 300, 910.00),
(7750, 8249.99, 10.00, 650.00, 320, 970.00 ),
(8250, 8749.99, 10.00, 690.00, 340, 1030.00),
(8750, 9249.99, 10.00, 730.00, 360, 1090.00 ),
(9250, 9749.99, 10.00, 770.00, 380, 1150.00),
(9750, 10249.99, 10.00, 810.00, 400, 1210.00),
(10250, 10749.99, 10.00, 850.00, 420, 1270.00),
(10750, 11249.99, 10.00, 890.00, 440, 1330.00),
(11250, 11749.99, 10.00, 930.00, 460, 1390.00),
(11750, 12249.99, 10.00, 970.00, 480, 1450.00),
(12250, 12749.99, 10.00, 1010.00,	500, 1510.00),
(12750, 13249.99, 10.00, 1050.00, 520, 1570.00),
(13250, 13749.99, 10.00, 1090.00, 540,	1630.00),
(13750, 14249.99, 10.00, 1130.00, 560, 1690.00),
(14250, 14749.99, 10.00, 1170.00, 580,	1750.00),
(14750, 15249.99, 30.00, 1230.00, 600, 1830.00),
(15250, 15749.99, 30.00, 1270.00, 620, 1890.00),
(15750, 16249.99, 30.00, 1310.00, 640, 1950.00),
(16250, 16749.99, 30.00, 1350.00, 660,	2010.00),
(16750, 17249.99, 30.00, 1390.00, 680,	2070.00),
(17250, 17749.99, 30.00, 1430.00, 700,	2130.00),
(17750, 18249.99, 30.00, 1470.00, 720, 2190.00),
(18250, 18749.99, 30.00, 1510.00, 740, 2250.00),
(18750, 19249.99, 30.00, 1550.00, 760, 2310.00),
(19250, 19749.99, 30.00, 1590.00, 780, 2370.00),
(19750,  50000.00, 30.00, 1630.00, 800, 2430.00);

DELIMITER \\
CREATE PROCEDURE allTax_sss()
BEGIN
SELECT sss_min, sss_max, sss_ec, sss_er, sss_ee, sss_total FROM sss_table;
END \\
DELIMITER ;
call allTax_sss();
drop procedure allTax_sss;


create table Philhealth_table(
	Philhealth_id int primary key auto_increment not null,
	Philhealth_min double not null,
    Philhealth_max double not null,
    Philhealth_employeeShare double not null,
    Philhealth_employerShare double not null,
    Philhealth_totalMonthlyPremium double not null
);

insert into Philhealth_table(Philhealth_min,Philhealth_max,Philhealth_employeeShare,Philhealth_employerShare,Philhealth_totalMonthlyPremium)
values
(	0.00	,	10000.00	,	137.00	,	137.50	,	275.00	),
(	10000.01	,	11000.00	,	151.25	,	151.25	,	302.50	),
(	11000.01	,	12000.00	,	165.00	,	165.00	,	330.00	),
(	12000.01	,	13000.00	,	178.75	,	178.75	,	357.50	),
(	13000.01	,	14000.00	,	192.50	,	192.50	,	385.00	),
(	14000.01	,	15000.00	,	206.25	,	206.25	,	412.50	),
(	15000.01	,	16000.00	,	220.00	,	220.00	,	440.00	),
(	16000.01	,	17000.00	,	233.75	,	233.75	,	467.50	),
(	17000.01	,	18000.00	,	247.50	,	247.50	,	495.00	),
(	18000.01	,	19000.00	,	261.25	,	261.25	,	522.50	),
(	19000.01	,	20000.00	,	275.00	,	275.00	,	550.00	),
(	20000.01	,	21000.00	,	288.75	,	288.75	,	577.50	),
(	21000.01	,	22000.00	,	302.50	,	302.50	,	605.00	),
(	22000.01	,	23000.00	,	316.25	,	316.25	,	632.50	),
(	23000.01	,	24000.00	,	330.00	,	330.00	,	660.00	),
(	24000.01	,	25000.00	,	343.75	,	343.75	,	687.50	),
(	25000.01	,	26000.00	,	357.50	,	357.50	,	715.00	),
(	26000.01	,	27000.00	,	371.25	,	371.25	,	742.50	),
(	27000.01	,	28000.00	,	385.00	,	385.00	,	770.00	),
(	28000.01	,	29000.00	,	398.75	,	398.75	,	797.50	),
(	29000.01	,	30000.00	,	412.50	,	412.50	,	825.00	),
(	30000.01	,	31000.00	,	426.25	,	426.25	,	852.50	),
(	31000.01	,	32000.00	,	440.00	,	440.00	,	880.00	),
(	32000.01	,	33000.00	,	453.75	,	453.75	,	907.50	),
(	33000.01	,	34000.00	,	467.50	,	467.50	,	935.00	),
(	34000.01	,	35000.00	,	481.25	,	481.25	,	962.50	),
(	35000.01	,	36000.00	,	495.00	,	495.00	,	990.00	),
(	36000.01	,	37000.00	,	508.75	,	508.75	,	1017.50	),
(	37000.01	,	38000.00	,	522.50	,	522.50	,	1045.00	),
(	38000.01	,	39000.00	,	536.25	,	536.25	,	1072.50	),
(	39000.01	,	39999.99	,	543.13	,	543.13	,	1086.26	),
(	0.00	,	40000.00	,	550.00	,	550.00	,	1100.00	);

DELIMITER \\
CREATE PROCEDURE allTax_PHIL()
BEGIN
SELECT Philhealth_min,Philhealth_max,Philhealth_employeeShare,Philhealth_employerShare,Philhealth_totalMonthlyPremium FROM Philhealth_table;
END \\
DELIMITER ;
call allTax_PHIL();
drop procedure allTax_PHIL;

create table Pagibig_table(
	Pagibig_id int primary key auto_increment not null,
	Pagibig_min float not null,
	Pagibig_max float not null,
    Pagibig_employeeShare float not null,
    Pagibig_employerShare float not null
);

insert into Pagibig_table(Pagibig_min,Pagibig_max,Pagibig_employeeShare,Pagibig_employerShare) values
(0,1500,.01,.02),(1500,0,.02,.02);

DELIMITER \\
CREATE PROCEDURE allTax_PAGibig()
BEGIN
SELECT Pagibig_min,Pagibig_max,Pagibig_employeeShare,Pagibig_employerShare FROM Pagibig_table;
END \\
DELIMITER ;
call allTax_PAGibig();
drop procedure allTax_PAGibig;

