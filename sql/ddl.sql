drop table member;
drop SEQUENCE seq_member;
create table member
(
	id number(8),
	name varchar2(20),
	userid varchar2(20),
	password varchar2(20),
	constraint id_pk primary key (id)
);
create sequence seq_member;