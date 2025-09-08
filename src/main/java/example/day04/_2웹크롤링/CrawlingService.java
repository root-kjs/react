package example.day04._2웹크롤링;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlingService {
    // 1. 뉴스 크롤링 https://www.karnews.or.kr/
    public List<String> task1(){
        List<String> list = new ArrayList<>(); // 크롤링한 제목들을 담을 리스트
        try {
            // 1-1 : 크롤링할 웹페이지 주소
            String URL = "https://www.karnews.or.kr/news/articleList.html?sc_section_code=S1N1&view_type=sm";
            // 1-2 : JSOUP 이용한 웹주소의 HTML(문서) 가져오기
            // * Document import org.jsoup.nodes.Document;
            // * Jsoup.connect( 크롤링할주소 ).get()  *일반예외
            Document document = Jsoup.connect( URL ).get();
            // 1-3 : *********************** 가져올 HTML 식별자 .select( "CSS선택자" ); ******************
            // JS : document.querySelect("css선택자");
            // JSOUP : document.select( "css선택자");
            Elements aList = document.select( ".titles > a "); // 'title' 이라는 class 명을 가진 마크업 바로 아래 <a> 마크업
            // 1-4 : 가져온 마크업들을 반복하여 텍스트만 추출 .text()
            for( Element a : aList ){
                String title = a.text();
                if( title.isBlank() ) continue; // *만일 내용이 없으면 다음반복*
                list.add( title );
            }
        }catch (Exception e ){ System.out.println("e = " + e); }
        return list;
    } // func end
} // class end











