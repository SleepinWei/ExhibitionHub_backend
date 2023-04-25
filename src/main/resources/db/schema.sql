DROP TABLE IF EXISTS user;

CREATE TABLE user(
    id INTEGER NOT NULL AUTO_INCREMENT,
    role VARCHAR(10) check(role='管理员' or role='普通用户' or role='博物馆') not null,
    username VARCHAR(128) NOT NULL,
    password VARCHAR(32) NOT NULL,
    email VARCHAR(32) NOT NULL,
    sex VARCHAR(4) check(sex='男' or sex='女') not null,
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
    venue_name VARCHAR(32) DEFAULT NULL,
    organizer VARCHAR(32) DEFAULT NULL,
    begin_date DATE DEFAULT NULL,
    end_date DATE DEFAULT NULL,
    begin_time TIME DEFAULT NULL,
    end_time TIME DEFAULT NULL,
    location VARCHAR(256) DEFAULT NULL,
    ticket_info VARCHAR(256) DEFAULT NULL,
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