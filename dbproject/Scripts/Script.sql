USE webproject2;
-- Create table if it does not exist
CREATE TABLE IF NOT EXISTS Account (
    username VARCHAR(20),
    email VARCHAR(20) PRIMARY KEY NOT NULL,
    pass VARCHAR(20) NOT NULL,
    userId VARCHAR(128) NOT NULL UNIQUE,
    cartId VARCHAR(128) NOT NULL UNIQUE,
    ordersId VARCHAR(128) NOT NULL UNIQUE,
    obj BLOB NOT NULL,
    cart BLOB NOT NULL,
    orders BLOB NOT NULL,
    auth VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS Cart (
    cartId VARCHAR(128) PRIMARY KEY NOT NULL,
    userId VARCHAR(128) NOT NULL UNIQUE,
    items BLOB,
    lastUpdate BIGINT,
    obj BLOB NOT NULL
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
    price VARCHAR(32)
);

-- Create table if it does not exist
CREATE TABLE IF NOT EXISTS CartItem (
    cartId VARCHAR(128) NOT NULL,
    itemId VARCHAR(128) PRIMARY KEY NOT NULL,
    listingTime BIGINT,
    item BLOB NOT NULL,
    itemCount SMALLINT,
    obj BLOB NOT NULL
);

-- Create table if it does not exist
CREATE TABLE IF NOT EXISTS Orders (
    ordersId VARCHAR(128) PRIMARY KEY NOT NULL,
    userId VARCHAR(128) UNIQUE NOT NULL,
    ordersList BLOB NOT NULL,
    obj BLOB NOT NULL
 );