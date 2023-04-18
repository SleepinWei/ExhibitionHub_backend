DROP TABLE IF EXISTS user;

CREATE TABLE user(
    id INTEGER NOT NULL AUTO_INCREMENT,
    username VARCHAR(128) NOT NULL,
    password VARCHAR(32) NOT NULL,
    PRIMARY KEY(id)
);


DROP TABLE IF EXISTS schedule;

CREATE TABLE schedule(
    id INTEGER NOT NULL AUTO_INCREMENT,
    date VARCHAR(12) NOT NULL,
    type INTEGER NOT NULL,
    content VARCHAR(128) NOT NULL,
    PRIMARY KEY(id)
);
