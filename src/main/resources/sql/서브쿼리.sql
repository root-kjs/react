
use springweb2;
# select 중첩해서 사용하는 것 : 서브 쿼리 
# [1] 일반 select
select avg( (kor+math) / 2 ) 평균점수 from student;

# [2] 중첩 select( 서브쿼리 ), 평균보다 높은 점수의 학생명을 출력
select name from student where ( (kor + math) / 2 ) > (서브쿼리);

-- 안쪽에 있는 기본 조건 쿼리문을 먼저 작성하고 밖으로 연산
select name from student 
	where ( (kor + math) / 2 ) > (
	select avg( (kor+math) / 2 ) 평균점수 from student
);

# [3] 국어 점수가 평균 이상인 학생들과 같은 점수를 가진 학생 조회
# 1. 작업 순서
-- 1) 먼저 내부 쿼리(서브쿼리) 기준으로 작성한다.
-- 2) 메인 쿼리를 작성한다. 

select avg( kor) from student; -- 1)국어 점수 평균 구하기
select kor from student  -- 2) 국어평균점수보다 높은 국어 점수
	where kor >= (
	select avg( kor) from student
);
-- 그점수들을 가진 학생들을 조회
-- in( 값1, 값2, 값3 ) 연산자 활용 --> or 연산자 처럼 1개라도 포함하면
-- 소괄호가 여러개 있는 경우 내부부터 작성하여 바깥으로 보기
select name from student -- 학생 이름 조회
	where kor in(
		select kor from student  -- 2) 국어평균점수보다 높은 국어 점수
		where kor >= (
		select avg( kor) from student -- 1) 국어 점수 평균 구하기
	)
);

# [4] 서브쿼리를 이용한 각 학생즐과 총점 비교
select * from student;
select s1.name (서브쿼리) from student s1;
select 
	s1.name, -- 학생 이름 조회
	( select count(*) from student s2  -- 레코드 수 
		where (s2.kor + s2.math) > (s1.kor + s1.math )
    )+1 as 등수
from student s1;

# rank 함수 --> 등수 구하는 함수

# [5] 서브쿼리로 평균 점수 정렬 , from 절에도 서브쿼리 가능 --> 1순위 , 2순위 --> where절
# select (서브쿼리) from (서브쿼리) where (서브쿼리);
select * from (
	select name, 
    (kor+math)/2 as 평균 from student
) as 평균테이블; 

select name, 평균 from (
	select name, 
    (kor+math)/2 as 평균 from student
) as 평균테이블
order by 평균 desc; 
 
 





