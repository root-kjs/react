# 인덱스 : 데이터를 빠르게 검색하기 위한 색인
-- pk는 기본적인 인덱스를 갖는다. 테ㅣ이블당 pk 1개 권장
-- 관계형 데이터베이스 구조상 특정한 데이터를 찾을 때 검색기준(인덱스)를 미리 지정하면 빠르다.
USE springweb2;

DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    dept_id INT,
    salary INT,
    hire_date DATE,
    email VARCHAR(100)
);
-- 부서 테이블 생성
DROP TABLE IF EXISTS department;
CREATE TABLE department (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(50)
);

-- 샘플 데이터
INSERT INTO department VALUES
(1, '개발팀'),
(2, '기획팀'),
(3, '디자인팀');

INSERT INTO employee (name, dept_id, salary, hire_date, email) VALUES
('유재석', 1, 5000, '2023-01-10', 'yu@test.com'),
('강호동', 2, 4000, '2024-02-12', 'kang@test.com'),
('신동엽', 1, 7000, '2022-06-05', 'shin@test.com'),
('이수근', 2, 5500, '2025-03-22', 'lee@test.com'),
('하하', 3, 3500, '2025-04-10', 'haha@test.com'),
('정형돈', 1, 6200, '2023-07-11', 'don@test.com'),
('박명수', 2, 4800, '2023-09-02', 'park@test.com'),
('노홍철', 3, 3700, '2024-05-14', 'noh@test.com'),
('김종국', 1, 8000, '2022-11-01', 'kim@test.com'),
('양세형', 2, 4300, '2024-06-21', 'yang@test.com'),
('이광수', 3, 3900, '2023-12-12', 'kwang@test.com'),
('조세호', 1, 5100, '2023-03-18', 'cho@test.com'),
('김용만', 2, 4600, '2022-09-23', 'yong@test.com'),
('정준하', 3, 3600, '2024-04-04', 'jun@test.com'),
('김태호', 1, 9000, '2021-10-15', 'taeho@test.com'),
('서장훈', 2, 5800, '2024-08-25', 'seo@test.com'),
('전현무', 3, 4000, '2022-12-01', 'jeon@test.com'),
('김구라', 1, 7500, '2023-11-05', 'gura@test.com'),
('유병재', 2, 4200, '2025-01-20', 'yoo@test.com'),
('김민아', 3, 3800, '2024-10-08', 'mina@test.com');

# [1] primary key느
select * from employee where id = 1;

# [2] 인덱스 목록 조회
show index from employee;

# [3] 단일 컬럼 인덱스 생성(조인/통계/데이터가 많은 경우 활용)
create index idx_name on employee( name );

# [4] 쿼리 성능 조회 : 속도 측정 ( 캐시 : 임시저장소 , 처음 접속시에는 모두 느리고 다음에 빠르다) 
explain analyze
select * from employee where name = "유재석";
-- -> Index lookup on employee using idx_name (name='유재석')  (cost=0.35 rows=1) (actual time=0.0206..0.0222 rows=1 loops=1)
-- -> Index lookup on employee using idx_name (name='유재석')  (cost=0.35 rows=1) (actual time=0.0165..0.0181 rows=1 loops=1)
-- -> Index lookup on employee using idx_name (name='유재석')  (cost=0.35 rows=1) (actual time=0.0197..0.0212 rows=1 loops=1)
-- -> Index lookup on employee using idx_name (name='유재석')  (cost=0.35 rows=1) (actual time=0.0177..0.0192 rows=1 loops=1)
 
 # [5] 인덱스 삭제 
 drop index idx_name on employee;
 
 # [6] 복합 인덱스 생성
 -- create index idx_name on employee( 컬럼명1, 컬럼명2 )
create index idx_salary on employee( dept_id, salary );

# 주의할 점 : 첫번째 인덱스에 대해서는 단일 사용 가능하다. 두번째 인덱스부터는 단일 사용 불가능
-- pk느 기본적으로 인덱스가 있기 때문에 인덱스 생성은 좀...

explain analyze select * from employee where dept_id = 1;
-- -> Index lookup on employee using idx_salary (dept_id=1)  (cost=1.2 rows=7) (actual time=0.025..0.0292 rows=7 loops=1)

explain analyze select * from employee where salary = 9000;
explain analyze select * from employee where dept_id = 1 and salary = 9000; -- 같이 검색해야 됨
 
# join : pk - fk가 아니더라도 조인은 가능하다. 하지만 pk는 기본 index가 지원이 되고, unique 가 지원이 되기 때문에 조인에 많이 사용된다. 

# fk에 인덱스 추가 
create index idx_dept on employee( dept_id );

# fk에 인덱스 추가하면,  조인 속도가 증가한다!
select * from employee e inner join department d on e.dept_id = d.dept_id;

# [문자열 검색] : 자연어 NLP( 사람이 사용하는 언어 // 번역하지 않고 그 자체를 비교(바이트화 하지 않음, 유니코드로 변경하지 않음 ) // 기계어 : 컴퓨터 2진수  )
-- type 이 text, longtext, char, varchar 가능하다. 
create fulltext index idx_name_full on employee(name); 
-- AI 가 자연어 처리 
select * from employee where match(name) against('김구라'); -- 기계어 번역이 아니고 바로 검색 
select * from employee where match(name) against('김구'); -- 해당 단어가 100% 일치해야 한다. 부분만 검색지원 안됨. 
explain analyze select * from employee where match(name) against('김구라'); -- 기계어 번역이 아니고 바로 검색 
-- -> Filter: (match employee.`name` against ('유재석'))  (cost=0.35 rows=1) (actual time=0.0141..0.0145 rows=1 loops=1)
-- -> Full-text index search on employee using idx_name_full (name='유재석')  (cost=0.35 rows=1) (actual time=0.0127..0.013 rows=...





