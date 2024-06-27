USE webproject2;
CREATE TABLE Account (
	username varchar(20),
	email varchar(20) primary key not null ,
	pass varchar(20) not null ,
	userId varchar(128) not null unique ,
	obj blob not null,
	cart blob not null,
	orders blob not null,
	auth varchar(32) not null
);
CREATE TABLE Cart (
	cartId varchar(128) primary key not null ,
	items blob ,
	lastUpdate bigint ,
	obj blob not null 
);

CREATE TABLE Category(
	name varchar(20) not null unique,
	categoryId varchar(128) primary key not null ,
	itemCount INT,
	items blob ,
	obj blob not null 
);
CREATE TABLE Item(
	itemId varchar(128) primary key not null ,
	title varchar(20) not null ,
	shortDesc varchar(32) not null ,
	longDesc varchar(128) not null ,
	imageURIs blob ,
	createTime bigint ,
	lastUpdate bigint ,
	lastAccess bigint ,
	obj blob not null ,
	categoryName varchar(32),
	price varchar(32)
);

CREATE TABLE CartItem(
	cartId varchar(128) not null,
	itemId varchar(128) primary key not null ,
	listingTime bigint ,
	item blob not null ,
	itemCount SMALLINT ,
	obj blob not null
);
create table Orders(
	ordersId varchar(128) primary key not null,
	ordersList blob not null,
	obj blob not null
);