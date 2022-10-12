CREATE Table Coffees (
	name VARCHAR(20) PRIMARY KEY,
	manufacturer VARCHAR(20)
);

CREATE Table Coffeehouses (
	name VARCHAR(20) PRIMARY KEY,
	address VARCHAR(30),
	license VARCHAR(20)
);

Create Table Sells (
	coffeehouse VARCHAR(20) references Coffeehouses(name),
	coffee VARCHAR(20) references Coffees(name),
	price REAL
);

Insert into Coffees (name, manufacturer) VALUES 
	('Arabica', 'BKI'),
	('Liberica', 'Peter Larsen'),
	('Robusta', 'MOKKAFFE'),
	('Excelsa', 'BKI');

Insert into Coffeehouses (name, address, license) values
	('Analog', 'Rued Langgaards Vej 7', 'xdk'),
	('Espresso House', 'Vestergade', 'true'),
	('Starbucks', 'Copenhagen Airport', 'true'),
	('Coffee Lab', 'Strandlodsvej 48B', 'true');

Insert into Sells (coffeehouse, coffee, price) values
	('Analog', 'Arabica', 3.07),
	('Analog', 'Liberica', 4.05),
	('Analog', 'Robusta', 12.5),
	('Espresso House', 'Arabica', 4.5),
	('Espresso House', 'Arabica', 5.6),
	('Espresso House', 'Excelsa', 8.3),
	('Espresso House', 'Liberica', 12),
	('Starbucks', 'Liberica', 15),
	('Starbucks', 'Robusta', 16),
	('Coffee Lab', 'Robusta', 8);
	
Select coffeehouse, coffee, price, address, manufacturer from Sells inner join Coffeehouses ON coffeehouse = Coffeehouses.name inner join Coffees on coffee = Coffees.name where coffeehouse = 'Analog' and price < (select avg(price) from sells);