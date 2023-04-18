DELETE FROM user;

INSERT INTO user(id,username,password) VALUES
(1,'root','000000'),
(2,'zyw','123456');

DELETE FROM schedule;

INSERT INTO schedule(id,date,type,content) VALUES
(1,'2023-04-10',1,'玉石展'),
(2,'2023-04-11',2,'美食展'),
(3,'2023-04-23',1,'电影展');