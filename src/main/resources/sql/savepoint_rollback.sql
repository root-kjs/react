use springweb2;
SET sql_safe_updates = 0; -- mysql workbench : safeMode 해제(끄기 0 / 켜기 1) -- 워크벤치에서 업데이트 설정alter

# 트랜잭션 : 여러작업들을 묶음으로 간주하여 성공하면 commit, 실패하면 rollback
-- mysql 워크벤치에서 자동 commit 비활성화 설정( 학습용 ) 
set autocommit = 0; 

# 1. 트랜잭션 시작
start transaction;

# 2. 여러 작업( dml만 가능 / ddl은 불가능_alter, create, drop )
update trans set money = money - 30000 where name = "신동엽";
update trans set money = money + 30000 where name = "서장훈";

# 3. 되돌리기(취소)
rollback;

# 4. 완료
commit;

select * from trans;
-- ----------------------------------------------------------------
# 1. 트랜잭션 시작
start transaction;

# 2. 여러 작업( dml만 가능 / ddl은 불가능_alter, create, drop )
update trans set money = money - 30000 where name = "신동엽";
update trans set money = money + 300000 where name = "신동엽";
# 3. 저장 지점 만들기
savepoint pointA; -- 특정 지점 저장 지점
# 4. 여러 작업 2
update trans set money = money + 30000 where name = "서장훈";
# 5. 완료 
commit;
# 6. 특정한 지점으로 롤백
rollback to pointA;
# 7. 확인 (1) 7123457 ---> 2, 4 적용  (2)1234657 ---> 2번만 적용
select * from trans;

-- ----------------------------------------------------------------
# 1. 트랜잭션 시작
start transaction;

# 2. 여러 작업( dml만 가능 / ddl은 불가능_alter, create, drop )
update trans set money = money - 10000 where name = "유재석";
-- ----------------------------------------------------------------
# 1. 트랜잭션 시작
start transaction;

# 2. 여러 작업( dml만 가능 / ddl은 불가능_alter, create, drop )
update trans set money = money - 10000 where name = "유재석";
savepoint step1;
update trans set money = money - 10000 where name = "서장훈";
savepoint step2;
update trans set money = money - 10000 where name = "강호동";
savepoint step3;

rollback to step1; commit; -- 1까지 반영
rollback to step2; commit; -- 1, 2까지 반영
rollback to step3; commit; -- 1, 2, 3 까지 반영
select * from trans;





