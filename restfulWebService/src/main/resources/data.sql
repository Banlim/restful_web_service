insert into user values(90001, sysdate(), 'User1', 'test1111', '701010-1234123');
insert into user values(90002, sysdate(), 'User2', 'test2222', '801010-2313123');
insert into user values(90003, sysdate(), 'User3', 'test3333', '901010-2564123');

insert into post values(10001, 'My first post', 90001);
insert into post values(10002, 'My second post', 90001);