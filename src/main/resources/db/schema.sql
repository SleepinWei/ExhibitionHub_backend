DROP TABLE IF EXISTS user;


/*CREATE TABLE user(
    id INTEGER NOT NULL AUTO_INCREMENT,
    role VARCHAR(5) check(role='管理员' or role='普通用户') not null,
    username VARCHAR(128) NOT NULL,
    password VARCHAR(32) NOT NULL,
    email VARCHAR(32) NOT NULL,
    sex VARCHAR(2) check(sex='男' or sex='女') not null,
    PRIMARY KEY(id)
);*/
CREATE TABLE user(
                     id INTEGER NOT NULL AUTO_INCREMENT,
                     username VARCHAR(128) NOT NULL,
                     password VARCHAR(32) NOT NULL,
                     email VARCHAR(64) NOT NULL ,
                     role VARCHAR(10) CHECK(role='管理员' or role='普通用户'),
                     sex VARCHAR(2) check(sex='男' or sex='女'),
                     PRIMARY KEY(id)
);