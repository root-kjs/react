package example.day05._2웹크롤링2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

@Service // 스프링이 컨테이너(메모리) 빈(객체) IOC
public class CrawlingService {

    // 1. 다음 날씨 정보 크롤링
    public Map<String,String> task1(){
        // 1-1 : 크롬 설치
        WebDriverManager.chromedriver().setup();
        // 1-2 : 크롬 옵션 객체
        ChromeOptions chromeOptions = new ChromeOptions();
        // * 크롬은 사용하지만 크롬브라우저 창은 띄우지 않는다.
        chromeOptions.addArguments("--headless=new" , "--disable-gpu");
        // 1-3 : 크롬 옵션을 웹드라이버(셀레니움) 에 객체생성
        WebDriver webDriver = new ChromeDriver( chromeOptions );
        // 1-4 : 크롤링 할 웹주소
        String URL = "https://weather.daum.net/";
        // 1-5 : 셀레니움(웹드라이버) 으로 크롤링할 웹주소 가져오기
        webDriver.get( URL );
        // 1-6 : 셀레니움(웹드라이버) 잠시 대기 , new WebDriverWait( 셀레니움객체 , Duration.ofSeconds( 초 )  );
            // * 왜? 대기하나요? 동적페이지는 JS(fetch)가 정보를 가져올때까지 정보가 없으므로 기다린다.
        WebDriverWait wait = new WebDriverWait( webDriver , Duration.ofSeconds( 3 ) );
        // 1-7 : 대기후 크롤링할 HTML CSS 식별하기 분석하기 , 권장: 식별자가 1개가 아닌 상위 식별자를 넣어서 중복방지
            // wait.until(ExpectedConditions.presenceOfElementLocated(  By.cssSelector( 크롤링할CSS선택자 ) ));
            // (1) 지역 , .info_location .tit_location        , 자손선택자: 식별자1 식별자2 , 자식선택자 : 식별자1 > 식별자2
        WebElement location
                = wait.until(ExpectedConditions.presenceOfElementLocated( By.cssSelector( ".info_location .tit_location" )  ));
        System.out.println("location = " + location);

            // (2) 온도 , .wrap_weather .num_deg
        WebElement temp =
                wait.until(ExpectedConditions.presenceOfElementLocated( By.cssSelector( ".wrap_weather .num_deg" ) ));
        System.out.println("temp = " + temp); // soutv

            // (3) 상태 , .wrap_weather .txt_sub
        WebElement status =
            wait.until(ExpectedConditions.presenceOfElementLocated( By.cssSelector( ".wrap_weather .txt_sub" ) ));
        System.out.println("status = " + status);

        return null; // 반환
    }

}














