DELETE FROM user;

INSERT INTO user(id,username,password) VALUES
(1,'root','000000'),
(2,'zyw','123456');

INSERT INTO exhibition(id,name,venue_name, organizer,begin_date,end_date,location,introduction,link) VALUES
(1,"ex1","venue1","o1","2001-01-01","2001-01-01","street0","some intro","link0"),
(2,"ex2","venue2","o2","2001-01-01","2001-01-01","street1","some intro","link1");

INSERT INTO tag(id,name) VALUES
(1,"tag1"),
(2,"tag2");

INSERT INTO exhibition_tag(id,ex_id,tag_id)VALUES
(1,1,1),
(2,1,2),
(3,2,2);
