DELETE FROM user;

--INSERT INTO user(id,username,password) VALUES
--(1,'root','000000'),
--(2,'zyw','123456');

INSERT INTO user(id,role,username,password,email,sex) VALUES
(1,'管理员','admin','123456','123456@qq.com','男'),
(2,'普通用户','user','123456','666666@qq.com','女'),
(3,'普通用户','vv','123456','wangyxin517@163.com','女');