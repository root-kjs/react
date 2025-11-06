package example2.day03._자바참조;

public class Example {
    //
    public static void main(String[] args) {

        System.out.println("출력"); // 자바는 100% 객체 지향 oop
        // 클래스
        // 객체(매개변수) static(new 없는 객체) , print()함수
        // 클래스(설계도)를 코드에 설계하고 클래스 기반으로 new를 해서 객체(실체 instance)를 만든다.
        // .은 참조 --> A.B , A안에 B를 참조한다. A가 null 이면 B를 참조할 수 없다. NullPointer Exception.

        // JPA는 영속성 : DB랑 자바는 비슷
        // 데이터베이스의 테이블은 클래스랑 비슷 == 엔티티클래스
        // 테이블 내 한 행 레코드는 객체(인스턴스) == 엔티티객체

        // 1.  카테고리 2개 생성, PK(상위 테이블)
        Category category1 = new Category(); // 객체(실체) 생성
        Category category2 = new Category();

        category1.setCno(1); // 필드명 입력(세터)
        category1.setCname("공지사항");

        category2.setCno(2);
        category2.setCname("자유게시판");

        // 2. 게시물 생성, FK(하위테이블)
        // 2.1. 공지사항에 게시물 작성
        Board board1 = new Board();
        board1.setBno(1);
        board1.setBcontent("공지1입니다/");
        board1.setCategory(category1); // 1번 게시물에 1번 공지사항 객체 참조, 조인이 필요없다!!!!!!!!!

        System.out.println( board1.getCategory().getCname());// 해당 게시물의 카테고리를 확인할 수 있다. 참조를 해야 하기때문에 .
        // 접근 연산자 많이 사용하게 된다.  참조가 길어지면 변수로 지정

        // 3. 공지사항 카테고리 데이터로 게시물을 조회 <양방향> 역참조
        category1.getBoardList().add( board1 );
        System.out.println( category1.getBoardList() );// 스택 오버플로우(순환 참조)

        // 4. 상황1. 1번 공지사항에 게시물 작성
        Board board2 = new Board();
        board2.setBno(2); board2.setBtitle("11월공지"); board2.setBcontent("내용");
        board2.setCategory( category1 ); // 단방향 참조


        category1.getBoardList().add( board2 ); // 양방향은 사용을 지양 하자.

        // 양방향 참조 //
        // 카테고리로 게시물을 조회할 수 있고 , 게시물로 카테고리 조회
        System.out.println( category1.getBoardList() );
        System.out.println( board2.getCategory() );





    }// main thread
}// class end
























