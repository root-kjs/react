use springweb2;

-- 1.기존 테이블 초기화
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS member;

-- 2.회원(member) 테이블 생성
CREATE TABLE member (
    mno INT AUTO_INCREMENT PRIMARY KEY,   -- 회원번호 (PK)
    name VARCHAR(50),                     -- 이름
    grade VARCHAR(20)                     -- 등급 (VIP, GOLD, SILVER)
);

-- 3. 주문(orders) 테이블 생성
CREATE TABLE orders (
    ono INT AUTO_INCREMENT PRIMARY KEY,   -- 주문번호 (PK)
    mno INT,                              -- 회원번호 (FK)
    product VARCHAR(50),                  -- 상품명
    price INT,                            -- 가격
    order_date DATE,                      -- 주문일자
    FOREIGN KEY (mno) REFERENCES member(mno)
);

-- 4. 샘플 데이터 삽입
INSERT INTO member (name, grade)
VALUES ('유재석', 'VIP'), ('강호동', 'GOLD'), ('신동엽', 'SILVER');

INSERT INTO orders (mno, product, price, order_date)
VALUES
(1, '노트북', 1500000, '2025-10-10'),
(1, '마우스', 30000, '2025-10-11'),
(2, '키보드', 50000, '2025-10-11'),
(3, '모니터', 200000, '2025-10-12');
# ------------------------------------------------------------------
select * from member;
select * from orders;

# 뷰(view) : 가상 테이블 , 하나 이상의 원본 테이블 기반으로 만들어진 가상 테이블
# 권한 : 신입은 원본 테이블 사용 권한을 주는 경우가 드물다.
# 보안 : 비밀번호 등의 특정 컬럼 보호를 위해 (개인정보 보호) 
# 재사용 : 집계/통계, 복잡한 쿼리문 결과 저장(재사용/성능 향상) 등등, 미리 집계를 내놓는다. 

# [1] 뷰 생성
select * from orders;
-- creat or replace view 뷰테이블명 as 
create or replace view order_test as select * from orders;

# 2. 뷰 목록 확인
show full tables where table_type = "view";

# 3. 뷰 수정 
# alter view order_test as 새로운 쿼리문;
alter view order_test as select product, price from orders;

# 4. 뷰 조회
select * from order_test;

# 5. 뷰 삭제
drop view if exists order_test;

# 뷰 주의할 점 : 특정한 경우가 아니면 select만 가능! update, delete, insert 사용이 어렵다. 
# 주로 읽기 모드로 사용하거나 테스트 용도로 사용된다. 
# 수정이 불가능 : 새로운 테이블 결합 : join(집계/계산식) , 섞여서 만든 테이블 group by 도 안됨. 새로운 데이터를 만든 테이블은 불가!
# 수정이 가능한 뷰는 단위테이블 기반은 수정이 가능 --> 순수 테이블

# [2] 먼저 원본이 되는 테이블 먼저 정의하고 뷰 테입르 만든다. 
# 1. view 만들기
create or replace view vip_member as
select * from member where grade = 'VIP';

# 2. vip 조회
select * from vip_member;
select * from member where grade = 'VIP';

# 뷰테이블 수정
update vip_member set name ="유재석1";

# [3] join view 조인 뷰 생성 : 서로 다른 테이블간의 (pk-fk) 연관관계를 구조 
# 객체 지향 : nosql
create or replace view view_order_summary as
select o.mno, m.name, o.product from member m inner join orders o on m.mno = o.mno;

select * from view_order_summary;

# 뷰테이블 수정
update view_order_summary set name = "유제석3";

select * from member;
create or replace view vip_member2 as
	select *, 10+10 as 집계 from member where grade = 'VIP';

select * from vip_member1;
select * from vip_member2;

update vip_member2 set 집계 = 30; -- 새로운 데이터는 수정이 안됨. 
update vip_member2 set name = "유제석4"; -- 기본 테이블의 컬럼은 수정이 된다. 
# 뷰테이블의 데이터 수정 조건은 단일 테이블의 원본 필드이면 가능. 집계/통계/계산/그룹 등의 필드에 대해서는 수정 반영이 불가!!!















