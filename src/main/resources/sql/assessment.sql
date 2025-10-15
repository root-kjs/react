DROP database if exists assessment;
create database assessment;
USE assessment;

# 01. 회원 정보 테이블
create table member_tbl_02( 
	custno int primary key auto_increment, 		-- 회원번호
    custname varchar(20), 						-- 회원명
    phone varchar(13), 							-- 전화번호
    address varchar(60), 						-- 주소
    joindate DATE DEFAULT (date(CURRENT_TIMESTAMP)),   	-- 가입일
    grade char(1),   							-- 고객등급(A/B/C) 1자리
    city char(2)								-- 거주도시 코드 2자리
);
ALTER TABLE member_tbl_02 AUTO_INCREMENT = 100007;

# 02. 회원매출정보 테이블 
create table money_tbl_02( 
	salenol int primary key auto_increment, 	-- 매출번호
	custno int,                                 -- 회원번호
    pcost int, 									-- 단가
    amount int, 								-- 수량
    price int, 									-- 가격
    pcode varchar(4), 							-- 상품코드
    sdate date default (date(CURRENT_TIMESTAMP)),			-- 판매일
    foreign key (custno) references member_tbl_02(custno) on delete set null
);

# 01. 샘플데이터 > 회원 정보 테이블
INSERT INTO member_tbl_02 (custno, custname, phone, address, joindate, grade, city) VALUES
(100001, '김형복', '010-1111-2222', '서울 동대문구 휘경1동', '2015-12-02', 'A', '01'),
(100002, '이축복', '010-1111-3333', '서울 동대문구 휘경2동', '2015-12-06', 'B', '01'),
(100003, '장믿음', '010-1111-4444', '울릉군 울릉읍 독도1리', '2015-10-01', 'B', '30'),
(100004, '최사랑', '010-1111-5555', '울릉군 울릉읍 독도2리', '2015-11-13', 'A', '30'),
(100005, '진평화', '010-1111-6666', '제주도 제주시 외나무골', '2015-12-25', 'B', '60'),
(100006, '자공단', '010-1111-7777', '제주도 제주시 감나무골', '2015-12-11', 'C', '60');

# 02. 샘플데이터 > 회원매출정보 테이블 
INSERT INTO money_tbl_02 (salenol, custno, pcost, amount, price, pcode, sdate) VALUES
(20160001, 100001, 500, 5, 2500, 'A001', '2016-01-01'),
(20160002, 100001, 1000, 4, 4000, 'A002', '2016-01-01'),
(20160003, 100001, 500, 3, 1500, 'A008', '2016-01-01'),
(20160005, 100002, 500, 1, 500, 'A001', '2016-01-03'),
(20160006, 100003, 1500, 2, 3000, 'A003', '2016-01-03'),
(20160007, 100004, 500, 2, 1000, 'A001', '2016-01-04'),
(20160008, 100004, 300, 1, 300, 'A005', '2016-01-04'),
(20160009, 100004, 600, 1, 600, 'A006', '2016-01-04'),
(20160010, 100004, 3000, 1, 3000, 'A007', '2016-01-04');

select * from member_tbl_02;  -- 01. 회원정보조회
select * from money_tbl_02;   -- 02. 회원매출조회



