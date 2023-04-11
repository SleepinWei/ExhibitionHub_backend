DELETE FROM user;

INSERT INTO user(id,username,password) VALUES
(1,'root','000000'),
(2,'zyw','123456');

INSERT INTO exhibition(id,name,organizer) VALUES
(1,"ex1","o1"),
(2,"ex2","o2");

INSERT INTO tag(id,name) VALUES
(1,"tag1"),
(2,"tag2");

INSERT INTO exhibition_tag(id,ex_id,tag_id)VALUES
(1,1,1),
(2,1,2),
(3,2,2);
