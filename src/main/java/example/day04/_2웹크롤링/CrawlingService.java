package example.day04._2웹크롤링;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // 2. 상품 정보 : 예스24 , https://www.yes24.com/robots.txt , +DB +CSV +@스케줄링
    public List<Map<String,String> > task2(){
        List< Map<String,String> > list = new ArrayList<>(); // 2-1 : 책 정보들을 담을 리스트 선언
        try{
            for( int page = 1 ; page <= 3 ; page++ ){ // page를 1부터 3까지 반복
                // 2-2 : 웹크롤링할 주소 , 페이징처리 크롤링
                String URL = "https://www.yes24.com/product/category/daybestseller" +
                        "?categoryNumber=001"+
                        "&pageNumber="+page+            // 1페이지 대신에 변수를 넣어서 여러번 크롤링
                        "&pageSize=24&type=day";
                // 2-3 : JSOUP 활용한 지정한 주소 HTML로 가져오기
                Document document = Jsoup.connect( URL ).get();
                // 2-4 : 책제목( .info_name > .gd_name )  과 책가격( .info_price > .txt_num > .yes_b )
                Elements nameList = document.select( ".info_name > .gd_name" );
                Elements priceList = document.select( ".info_price > .txt_num > .yes_b");
                Elements imgList = document.select( ".img_bdr .lazy");
                // 2-5 : 반복문을 이용한 책정보 구성
                for( int i = 0 ; i < nameList.size() ; i++ ){
                    String name = nameList.get( i ).text(); // i번째 책제목 1개씩 호출
                    String price = priceList.get( i ).text(); // i번째 책가격 1개씩 호출
                    String img = imgList.get( i ).attr( "data-original"); // i번째 책이미지(링크) 속성값 1개씩 호출
                    Map<String,String> map = new HashMap<>();
                    map.put( "name" , name );
                    map.put( "price" , price ); // MAP객체생성 VS DTO
                    map.put( "img" , img );
                    list.add( map );
                } // for2 end
            } // for1 end
        } catch (Exception e) { System.out.println("e = " + e); }
        return list; // 리스트 반환 end
    } // func end

    // 3. 다음날씨 정보 https://weather.daum.net/robots.txt ***** 동적 페이지 JSOUP 안된다. --> 방안 : 셀레니움 *****
    public Map<String,String> task3(){
        Map<String,String> map = new HashMap<>(); // 3-1 날씨 정보를 저장할 맵
        try{
            String URL ="https://weather.daum.net/"; // 3-2 날씨 정보를 가져올(크롤링) 주소
            Document document = Jsoup.connect( URL ).get(); // 3-3 JSOUP 활용한 지정한 주소 HTML로 가져오기
            System.out.println("document = " + document);

            Elements elements = document.select(".info_weather .num_deg");// 3-4 온도( .info_weather .num_deg )
            // 즉] : 'info_weather' 마크업은 JS가 데이터를 표시하는 방법이므로 크롤링 불가능
            System.out.println("elements = " + elements);

        } catch (Exception e) { System.out.println("e = " + e); }
        return map;
    }

} // class end











