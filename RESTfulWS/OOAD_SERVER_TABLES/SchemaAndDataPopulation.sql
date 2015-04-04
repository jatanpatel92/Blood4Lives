create database blood_for_lives;

USE blood_for_lives;
CREATE TABLE users
(
phone VARCHAR(20) PRIMARY KEY,
fname VARCHAR(25),
lname VARCHAR(30),
email varchar(100),
bloodgroup varchar(10),
dob date,
area varchar(50),
rating float
);

insert into users values('14693475416', 'kkk', 'jjj', 'email', 'A-', '1990-3-20', 'Dallas', 0);
insert into users values('11111111111', 'match1', 'surname1', 'email1', 'A-', '1990-3-21', 'Dallas', 0);
insert into users values('12222222222', 'match2', 'surname2', 'email2', 'A-', '1992-6-2', 'Dallas', 0);
insert into users values('13333333333', 'match3', 'surname3', 'email3', 'A-', '1992-6-23', 'Dallas', 0);
insert into users values('14444444444', 'nomatch', 'surname', 'email', 'A+', '1992-6-2', 'Dallas', 0);
