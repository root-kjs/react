package example.day01._1스프링스레드;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;

@RestController                 // 1. http 요청/응답 처리
@RequestMapping("/task/day01")  // 2. http url 연결(매핑)
@RequiredArgsConstructor        // 3. final 변수의 자동 생성자
public class ThreadController {
    // [*] 서비스 객체
    private final ThreadService threadService;

    // 1. http://localhost:8080/task/day01
    @GetMapping // 동기화 : 스프링 매핑 컨트롤러는 자동 동기화 처리한다.
    // 먼저 요청을 한 HTTP 부터 처리후 반환 한다. // 요청 순서대로
    public int thread1(){
        System.out.println("ThreadController.thread1");
        return threadService.thread1();
    }

    // 2. http://localhost:8080/task/day01
    @DeleteMapping
    public void thread2(){
        System.out.println("ThreadController.thread2");
        threadService.thread2();
    }

}








