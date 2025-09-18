drop database if exists phone_book;
create database phone_book;
use phone_book;

# 전화번호부 테이블
create table phone_book(
	pno int unsigned primary key auto_increment,    -- PK번호
    pName varchar(30) not null, 					-- 이름
    pPhone varchar(13) unique not null, 			-- 전화번호
    pAge tinyint unsigned							-- 나이
);

# 전화번호부 샘플데이터
INSERT INTO phone_book (pName, pPhone, pAge) VALUES
('홍길동', '010-1234-5678', 30),
('김철수', '010-9876-5432', 25),
('이영희', '010-5555-4444', 35);

select * from phone_book;
