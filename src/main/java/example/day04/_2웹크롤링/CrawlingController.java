package example.day04._2웹크롤링;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task/day04")
@RequiredArgsConstructor
public class CrawlingController {

    private final
    CrawlingService crawlingService;

    // 1. 뉴스 크롤링
    @GetMapping("/craw1")
    public List<String> task1(){
        return crawlingService.task1();
    } // func end

} // class end










