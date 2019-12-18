-- creating the database and related tables
create database STORE;
use STORE;

-- create Register relation
Create table Register (
	Id varchar(20) not null,
	Password varchar(20) not null,
	Admin boolean,
	primary key (Id)
	);

-- create Stock relation
create table Stock (
	Name varchar(20) not null,
	Cost int(11) not null,
	Whole int(11) not null,
	Edate datetime,
	Quantity int not null,
	Event boolean,
	Lid varchar(20) not null,
	primary key(Name, Edate)
	);

-- create Sales relation
create table Sales (
	Pname varchar(20) not null,
	Psales decimal(5,3) not null,
	Margin int not null,
	Discounts decimal(3,2) not null,
	primary key(Pname)
	);

-- create foreign key in stock
alter table Stock
add constraint StockID
foreign key (Lid)
references Register (Id);

-- create foreign key in sales
alter table Sales
add constraint SalesName
foreign key (Pname)
references Stock (Name);