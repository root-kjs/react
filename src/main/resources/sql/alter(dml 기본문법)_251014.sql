/* SQL
		DDL : 정의어
			CREATE		테이블/데이터베이스 생성
            DROP		테이블/데이터베이스 삭제 
            *ALTER		테이블 정보 수정 
        DML : 조작어
			INSERT
            SELECT
            UPDATE
            DELETE
        DCL : 제어어
*/
# alter --> 마이바티스에서 동적쿼리로 테이블 조작 가능(@Update )

use springweb2; -- 사용할 DB 선택
# [1] 
drop table if exists employee;
create table employee(
	id int,
    name varchar(50),
    dept varchar(30)
);
# [2]) 기존 테이블 필드 추가
# alter table 테이블명 add column 새로운 필드명 속성및제역조건; 
alter table employee add column age int default 10;
# 컬럼 추가 
alter table employee add column date date;

# [3] 기존 테이블의 필드 타입 정보 수정
# alter table employee modify column 수정할 필드명 새로운 타입

# [4] 기존 테이블의 필드명 수정
alter table employee change column name nickname varchar(100);

# alter table 테이블명 add column 새로운 필드명 속성및제역조건;
alter table employee modify dept longtext;

# [5] 기존 테이블의 필드명 삭제 (필드명==속성명==컬렁명)
# alter table 테이블명 drop column 삭제할 필드명;
alter table employee drop column date;

# [6] 특정한 테이블의 필드 확인
# show columns from 테이블명, show : 메타데이터 확인용
show columns from employee; -- 특정한 테이블의 속성명 타입 조회
show tables; -- DB 내 전체 테이블 확인 가능 

# [7] 제약조건 추가(pk/fk)
# alter table employee add constraint 제약조건명(pk_테이블명 : 작명 자유) primary key(id);
alter table employee add constraint employee_id primary key (id);
# alter table employee add constraint employee_name unique(name);
alter table employee add constraint employee_name unique(nickname);

# [8] 제약조건 삭제(pk/fk)
# ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건명(아무거나/중복불가) PRIMARY KEY (pk필드명);
# ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건명 FOREIGN KEY(FK필드명) REGERENCES 참조테이블명(PK필드명);
# alter table employee drop constraint 삭제할 제약조건명;
alter table employee drop constraint employee_id; -- 안됨(pk가 1개 일 경우에는 안됨, 복합키일 경우만 삭제 가능)
alter table employee drop primary key; -- pk가 1개 인 경우에는 이걸로 삭제
# alter table employee drop foreign key 삭제할 fk명;
alter table employee drop constraint employee_name;

# [9] 수정없이 삭제 후 다시 제약조건 추가

# [10] 제약조건 확인 --> 현재 DB 내 모든 테이블의 제약 조건이 모두 다 나옴
select * from information_schema.table_constraints;
select * from information_schema.table_constraints where table_schema = "springweb2";
select * from information_schema.table_constraints where table_schema = "springweb2" and table_name = 'employee';

select * from employee; -- 특정 테이블의 레코드 정보 확인(속성값 확인)
