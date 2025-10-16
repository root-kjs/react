
-- DCL : 계정관리, 계정의 사용권한 관리
-- [1] 계정 만들기 : root(최고관리자) 계정으로 가능하다.
-- DB 설치하면 기본적으로 root(최고관리자) 계정이 존재한다.

-- create user '계정명'@'허용도메인' identified by '비밀번호';
-- % 모든 도메인 허용 --> 'dev1'@'%'
-- create user 'dev1'@'localhost' identified by '5678'; -- 비번 5678로 잘못 입력함...;;;
-- create user 'dev2'@'%' identified by '1234'; -- 외부 접속을 모두 허용한다. 
create user 'dev3'@'%' identified by '1234'; -- 외부 접속을 모두 허용한다. 


-- 계정의 권한 부여( grant ), *(와일드카드) : 전체 
-- grant all privileges on 데이터베이스명.테이블명 to '계정명'@'도메인';
-- grant all privileges on springweb2.* to 'dev1'@'localhost';
grant all privileges on springweb2.* to 'dev1'@'localhost';

grant select on springweb2.student to 'dev3'@'%';
show databases;

show grants for 'dev1'@'localhost';

# 계정 권한 회수
-- revoke select on 데이터베이스명.테이블명/뷰명 from '계정명'@'도메인';
revoke select on springweb2.student from 'dev3'@'%';

alter user 'dev3'@'%' identified by '1234';

# 계정의 삭제
drop user 'dev3'@'%' ;

use springweb2;
create user 'dev4'@'localhost' identified by '1234';
create or replace view student_view as select * from student;
select * from student_view;
#
grant select on springweb2.student_view to 'dev4'@'localhost';

