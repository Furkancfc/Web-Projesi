USE webproject2;
CREATE TABLE if not exists Account (
	username varchar(20),
	email varchar(20),
	password varchar(20),
	userId varchar(64),
	cartId varchar(64),
	bytes Blob
);
CREATE TABLE if not exists Cart (
	cartId varchar(64),
	items Blob,
	lastUpdate bigint,
	obj Blob 
);

CREATE TABLE if not exists Category(
	name varchar(20),
	categoryId varchar(64),
	itemCount int,
	itemsIds Blob,
	obj Blob
);
CREATE TABLE if not exists Item(
	itemId varchar(64) ,
	title varchar(20) ,
	shortdesc varchar(32) ,
	longdesc varchar(128) ,
	imageURIs Blob ,
	createTime bigint ,
	lastUpdate bigint ,
	lastAccess bigint ,
	obj Blob 
);

CREATE TABLE if not exists CartItem(
	listingTime bigint ,
	itemId varchar(64) ,
	itemCount SMALLINT ,
	item blob ,
	obj blob 
);