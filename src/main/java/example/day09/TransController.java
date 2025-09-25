package example.day09;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/d9/trans")
@RequiredArgsConstructor
public class TransController {
    private final TransService transService;

    // 1.
    @PostMapping
    public boolean trans1(){
        return transService.trans1();
    }
    
    // 신동엽 서장훈에게 10만원 보내기 신동엽 - , 서장훈 +
    @PostMapping("/2")// { "fromname" : "신동엽" , "toname" : "서장훈" , "money" : "100000"}
    public boolean transfer(@RequestBody Map<String, Object> transInfo ){
        return transService.transfer(transInfo);
    }
    
}// class end
