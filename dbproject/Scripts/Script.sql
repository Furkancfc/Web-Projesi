USE webproject2;

CREATE TABLE IF NOT EXISTS Account(
	username varchar(20),
	email varchar(20) primary key not null ,
	pass varchar(20) not null ,
	userId varchar(128) not null unique ,
	obj blob,
	cart blob ,
	orders blob,
	auth varchar(32) not null
);
CREATE TABLE IF NOT EXISTS Cart (
	cartId varchar(128) primary key not null ,
	items blob ,
	lastUpdate bigint ,
	obj blob not null 

);

CREATE TABLE IF NOT EXISTS Category (
    name VARCHAR(20) NOT NULL UNIQUE,
    categoryId VARCHAR(128) PRIMARY KEY NOT NULL,
    itemCount INT,
    items BLOB,
    obj BLOB NOT NULL
);

-- Create table if it does not exist
CREATE TABLE IF NOT EXISTS Item (
    itemId VARCHAR(128) PRIMARY KEY NOT NULL,
    title VARCHAR(32) NOT NULL,
    shortDesc VARCHAR(32) NOT NULL,
    longDesc VARCHAR(128) NOT NULL,
    imageURIs BLOB,
    createTime BIGINT,
    lastUpdate BIGINT,
    lastAccess BIGINT,
    obj BLOB NOT NULL,
    categoryName VARCHAR(32),
    price DOUBLE
);

-- Create table if it does not exist
CREATE TABLE IF NOT EXISTS CartItem (
    cartId VARCHAR(128) NOT NULL,
    itemId VARCHAR(128) PRIMARY KEY NOT NULL,
    listingTime BIGINT,
    itemCount SMALLINT,
    obj BLOB NOT NULL
);

CREATE TABLE IF NOT EXISTS Orders(
	ordersId varchar(128) primary key not null,
	ordersList blob not null,
	obj blob not null
);
