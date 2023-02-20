-------------------- 0216 새 계정만듬  // 비번 자동저장안햇는디,, spring 임 ----------------------
alter session set "_oracle_script" = true;
create user spring IDENTIFIED by spring
default tablespace  users;

alter user spring quota unlimited on users;

grant connect, resource to spring;
--------------------------------------------------------------------------------
-- dev테이블 생성 
-- 테이블 컬럼이랑 dto랑  이름 같게 해야 편함 ++ dev form name값
create table dev(
    no number,
    name varchar2(50) not null,
    career number not null, 
    email varchar2(200) not null,
    gender char(1),
    lang varchar2(100) not null,
    created_at date default sysdate, 
    CONSTRAINT pk_dev_no primary key(no),
    CONSTRAINT ch_dev_gender check(gender in('M','F'))
);
create SEQUENCE seq_dev_no;

select * from dev where no = 5;

-------------------------------------------------------------------------------------
create table member ( 
    member_id varchar2(50),
    password varchar2(300) not null,
    name varchar2(245) not null,
    birthday date,
    email varchar2(256),
    phone char(11) not null,
    created_at date default sysdate,
    enabled number default 1,
    constraint pk_member_id primary key (member_id),
    CONSTRAINT ck_member_enabled check(enabled in (1,0))
);
-- 샘플데이터
insert into spring.member values ('abcde','1234','아무개',to_date('88-01-25','rr-mm-dd'),'abcde@naver.com','01012345678', default, default);
insert into spring.member values ('qwerty','1234','김말년',to_date('78-02-25','rr-mm-dd'),'qwerty@naver.com','01098765432', default, default);
insert into spring.member values    ('admin','1234','관리자',to_date('90-12-25','rr-mm-dd'),'admin@naver.com','01012345678', default, default);

commit;

select * From member;
-- 원래 멤버들  비번 암호화 처리 업데이트 각각..(0217) 
update member 
set password = '$2a$10$QS/CTrKf7zF0YxhWnysXsOwh/3MiViG3mLlamMvis/KCTqWefW382'
where member_Id = 'honggd';

update member 
set password ='$2a$10$iTo3nKpGEW/agyUEyqJCquufu.c9DHI2LA3St6wIB8JebMBahPMGW'
where member_Id = 'admin' 


update member 
set password ='$2a$10$klf2yizU4r5EGbEvQX7eseh.qgdsOl0q01MlGhx2L0J5212DmKO4y'
where member_Id = 'qwerty' 

update member 
set password ='$2a$10$S.JP.BLYC2ngwi0gkOCDx.fXVBG/4ylHt67KEd3t6ZZkWnizoWJqu'
where member_Id = 'abcde'

update member 
set password ='$2a$10$RZE9W/czM20rqn7fv.JfaOjF88W6dbvWL3JN9NLxZ7qUzoPQoIDwa'
where member_Id = 'khkh'

commit;

select * From member ;

---- todo 테이블 만들기 0220 ---- 
create table todo(
    no number, 
    todo varchar2(4000),
    created_at date default sysdate,
    completed_at date,
    constraint pk_todo_no primary key(no)
);
create sequence seq_todo_no;

insert into todo values(seq_todo_no.nextval,'에어컨 청소하기',default,null);
insert into todo values(seq_todo_no.nextval,'형광등 교체하기',default,null);
insert into todo values(seq_todo_no.nextval,'gof의 디자인 패턴 책읽기',default,null);
insert into todo values(seq_todo_no.nextval,'장보기',default,null);

update todo set completed_at = sysdate where no = 4;
update todo set completed_at = sysdate where no = 2;


-- 할일 목록 조회
select * from todo where completed_at is null;

-- 할일은  no 오름차순 
-- 완료된 일은 내림차순으로 볼렴? 
select * 
from (   select * from todo where completed_at is  null order by no asc)
union all 
select * from ( select * from todo where completed_at is not null  order by  completed_at desc) ;

--------------------
select  row_number() over(order by no asc ) rnum, t.* 
from todo t
  union all
select  row_number() over(order by completed_at  asc ) rnum, t.* 
from todo t

select * from todo;

----  체크됐니안됐니에 따라 ,, 값두개를 컨트롤러를 확인할수잇는지 .. ?
update todo
set  completed_at = sysdate
where where no = ?

update todo
set  completed_at = null
where where no = ?



-- commit;

select * From todo

select * 
from ( select * From todo where completed_at is null order by no asc  )
union all --union all 을쓰면 order by 절은 제일끝에 한번 써야된다 
select * 
from (  select * From todo where completed_at is not null order by completed_at  desc) 
