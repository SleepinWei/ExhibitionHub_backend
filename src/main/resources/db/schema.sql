DROP TABLE IF EXISTS user;
CREATE TABLE user(
    id INTEGER NOT NULL AUTO_INCREMENT,
    username VARCHAR(128) NOT NULL,
    password VARCHAR(32) NOT NULL,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS tag;
CREATE TABLE tag(
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS exhibition;
CREATE TABLE exhibition(
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL,
    organizer VARCHAR(32),
    begin_date DATE DEFAULT NULL,
    end_date DATE DEFAULT NULL,
    location VARCHAR(256) DEFAULT NULL,
    introduction VARCHAR(1024) DEFAULT NULL,
    link VARCHAR(256) DEFAULT NULL,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS exhibition_tag;
CREATE TABLE exhibition_tag(
    id INTEGER NOT NULL AUTO_INCREMENT,
    ex_id INTEGER NOT NULL,
    tag_id INTEGER NOT NULL,
    PRIMARY KEY(id)
);