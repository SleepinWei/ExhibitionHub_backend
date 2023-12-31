drop database if exists exhibition;
create database exhibition;
use exhibition;

CREATE TABLE user(
    id INTEGER NOT NULL AUTO_INCREMENT,
    role VARCHAR(10) check(role='管理员' or role='普通用户' or role='博物馆') ,
    username VARCHAR(128) NOT NULL,
    password VARCHAR(32) NOT NULL,
    email VARCHAR(32) NOT NULL,
    sex VARCHAR(4) check(sex='男' or sex='女') ,
    biography VARCHAR(20),
    PRIMARY KEY(id)
);

CREATE TABLE tag(
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE exhibition(
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL,
    venue_name VARCHAR(32) DEFAULT NULL,
    organizer VARCHAR(32) DEFAULT NULL,
    begin_date DATE DEFAULT NULL,
    end_date DATE DEFAULT NULL,
    begin_time TIME DEFAULT NULL,
    end_time TIME DEFAULT NULL,
    province VARCHAR(50) DEFAULT NULL,
    city VARCHAR(50) DEFAULT NULL,
    area VARCHAR(50) DEFAULT NULL,
    address VARCHAR(256) DEFAULT NULL,
    ticket_info VARCHAR(256) DEFAULT NULL,
    introduction VARCHAR(1024) DEFAULT NULL,
    link VARCHAR(256) DEFAULT NULL,
    poster_url VARCHAR(256) DEFAULT NULL,
    is_checked BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY(id),
    FULLTEXT KEY (name,introduction) WITH PARSER `ngram` 
);

CREATE TABLE ex_review(
    id INTEGER NOT NULL AUTO_INCREMENT,
--    ex_id INTEGER NOT NULL,
    name VARCHAR(32) NOT NULL,
    venue_name VARCHAR(32) DEFAULT NULL,
    organizer VARCHAR(32) DEFAULT NULL,
    begin_date DATE DEFAULT NULL,
    end_date DATE DEFAULT NULL,
    begin_time TIME DEFAULT NULL,
    end_time TIME DEFAULT NULL,
    province VARCHAR(50) DEFAULT NULL,
    city VARCHAR(50) DEFAULT NULL,
    area VARCHAR(50) DEFAULT NULL,
    address VARCHAR(256) DEFAULT NULL,
    ticket_info VARCHAR(256) DEFAULT NULL,
    introduction VARCHAR(1024) DEFAULT NULL,
    link VARCHAR(256) DEFAULT NULL,
    poster_url VARCHAR(256) DEFAULT NULL,
    is_checked BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY(id)
);

CREATE TABLE exhibition_tag(
    id INTEGER NOT NULL AUTO_INCREMENT,
    ex_id INTEGER NOT NULL,
    tag_id INTEGER NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (ex_id) REFERENCES exhibition(id) on delete cascade
);

CREATE TABLE exhibitionRe_tag(
    id INTEGER NOT NULL AUTO_INCREMENT,
    exRe_id INTEGER NOT NULL,
    tag_id INTEGER NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE subscription(
    user_id INTEGER NOT NULL AUTO_INCREMENT,
    ex_id INTEGER NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id) on delete cascade,
    FOREIGN KEY (ex_id) REFERENCES exhibition(id) on delete cascade
);
CREATE TABLE user_ex_relation (
    user_id INTEGER NOT NULL,
    ex_id INTEGER NOT NULL,
    ex_review_id INTEGER NOT NULL,
    date DATETIME NOT NULL,
    review_date DATETIME DEFAULT NULL,
    type VARCHAR(20) NOT NULL,
    is_done BOOLEAN NOT NULL,
    result VARCHAR(20) NOT NULL,
--    true : finished , false: unfinished
    PRIMARY KEY (ex_review_id)
);

CREATE TABLE search_score (
    id INTEGER NOT NULL,
    score FLOAT NOT NULL DEFAULT 1, --设置为1是因为如果输入空字符串不做匹配，防止因为初始值问题认为搜索内容不匹配
    FOREIGN KEY (id) REFERENCES exhibition(id) on delete cascade
);

