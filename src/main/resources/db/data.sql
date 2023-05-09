 INSERT INTO exhibition(id,name,venue_name, organizer,begin_date,end_date,begin_time,end_time,location,introduction,link,poster_url,ticket_info,is_checked) VALUES
 (1,"【北京】国际安徒生奖50周年世界插画大展","北京王府井银泰in88 B2展厅","不知道什么传媒","2023-04-28","2023-10-15","09:00:00","20:00:00","北京市东城区王府井大街88号"
 ,"横跨百年的传世经典，云集全球插画界“诺贝尔奖”大师代 表画作匠心集成《国际安徒生奖50周年世界插画大展》北京站重磅登陆北京王府井银泰in88！ 26位国际安徒生奖插画家奖得主经典画作，不言而喻的珍贵与稀缺，300余幅作品带你领略艺术大师恢弘的美学世界，“趣味展馆”设计融入炫酷科技与丰富互动体验，打造艺术观展体验。"
 ,"https://bilibili.com","images/2.webp","100RMB",false),
 (2,"【北京】邂逅·多彩大明 1573","北京市 | 国家典籍博物馆","中国文物交流中心","2023-04-28","2023-08-29","09:00:00","20:00:00","北京市中关村南大街33号"
 ,"1. 130余件明代珍贵文物\n2.不出京的\"金翼善冠\"\n3.大明场景还原，沉浸式打卡\n4.研学互动，活的历史课堂","https://www.douban.com/event/35620820/",
 "images/1.webp","150RMB",false),
 (3,"【北京】邂逅·多彩大明 1600","北京市 | 国家典籍博物馆","中国文物交流中心","2023-04-28","2023-08-29","09:00:00","20:00:00","北京市中关村南大街33号"
 ,"1. 130余件明代珍贵文物\n2.不出京的\"金翼善冠\"\n3.大明场景还原，沉浸式打卡\n4.研学互动，活的历史课堂","https://www.douban.com/event/35620820/"
 ,"images/1.webp","200RMB",false),
 (4,"【北京】邂逅·多彩大明 1700","北京市 | 国家典籍博物馆","中国文物交流中心","2023-04-28","2023-08-29","09:00:00","20:00:00","北京市中关村南大街33号"
 ,"1. 130余件明代珍贵文物\n2.不出京的\"金翼善冠\"\n3.大明场景还原，沉浸式打卡\n4.研学互动，活的历史课堂","https://www.douban.com/event/35620820/",
 "images/1.webp","200RMB",false);

 INSERT INTO ex_review(id,name,venue_name, organizer,begin_date,end_date,begin_time,end_time,location,introduction,link,poster_url,ticket_info,is_checked) VALUES
 (1,"【北京】国际安徒生奖","北京王府井银泰in88 B2展厅","不知道什么传媒","2023-04-28","2023-10-15","09:00:00","20:00:00","北京市东城区王府井大街88号"
 ,"横跨百年的传世经典，云集全球插画界“诺贝尔奖”大师代 表画作匠心集成《国际安徒生奖50周年世界插画大展》北京站重磅登陆北京王府井银泰in88！ 26位国际安徒生奖插画家奖得主经典画作，不言而喻的珍贵与稀缺，300余幅作品带你领略艺术大师恢弘的美学世界，“趣味展馆”设计融入炫酷科技与丰富互动体验，打造艺术观展体验。"
 ,"https://bilibili.com","images/2.webp","300RMB",false),
 (2,"【北京】邂逅·多彩大明xxxx","北京市 | 国家典籍博物馆","中国文物交流中心","2023-04-28","2023-08-29","09:00:00","20:00:00","北京市中关村南大街33号"
 ,"1. 130余件明代珍贵文物\n2.不出京的\"金翼善冠\"\n3.大明场景还原，沉浸式打卡\n4.研学互动，活的历史课堂","https://www.douban.com/event/35620820/",
 "images/1.webp","150RMB",false);

 INSERT INTO user_ex_relation(user_id,ex_id,ex_review_id,date,type,status) VALUES
 (1,1,1,"2002-02-02 09:00:00","change",FALSE),
 (1,2,2,"2002-03-03 09:00:00","change",FALSE);

INSERT INTO tag(id,name) VALUES
(1,"历史"),
(2,"美术");

INSERT INTO exhibition_tag(id,ex_id,tag_id)VALUES
(1,1,1),
(2,2,1),
(3,2,2);

INSERT INTO user(id,role,username,password,email,sex) VALUES
(1,'管理员','admin','123456','123456@qq.com','男'),
(2,'普通用户','user','123456','666666@qq.com','女'),
(3,'普通用户','vv','123456','wangyxin517@163.com','女');
