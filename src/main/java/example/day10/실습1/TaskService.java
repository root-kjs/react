package example.day10.실습1;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    // 1. 매 30초마다 모든 제품의 재고는 3개씩 감소한다.
    @Scheduled( cron = " 0/30 * * * * * ") // 예시] 12:57:00 , 12:57:30
    public void task1(){
        System.out.println("TaskService.task1");
        // ++ DAO 호출하여 모든 제품 3개씩 감소 요청 ++
    }
    // 2. 매 1분마다 모든 제품 정보를 조회/출력 한다.
    @Scheduled( cron = " 0 0/1 * * * *") // 예시1] 12:57:00 , 12:58:00
    // @Scheduled( cron = " 0 1 * * * *") // 예시2] 12:01:00 , 13:01:00
    public void task2(){
        System.out.println("TaskService.task2");
        // ++ DAO 호출하여 모든 제품 정보를 조회 요청 하여 출력 ++
    }
    // 3. 매 5분마다 재고가 10 이하인 상품의 재고를 +20개 추가한다.
    @Scheduled( cron = " 0 0/5 * * * *") // 예시1] 12:55:00 , 13:00:00 , 13:05:00
    public void task3(){
        System.out.println("TaskService.task3");
        // ++ DAO 호출하여 재고가 10 이하인 상품의 재고를 +20개 추가 요청.++
    }
}
