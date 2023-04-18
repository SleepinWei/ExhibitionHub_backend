DELETE FROM user;

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

INSERT INTO user(id,role,username,password,email,sex) VALUES
(1,'管理员','admin','123456','123456@qq.com','男'),
(2,'普通用户','user','123456','666666@qq.com','女');
