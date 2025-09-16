/**
 컴포넌트란? 화면에서 최소로 쪼개진 모듈(함수)
 만드는 방법 
 1) 생성할 폴더를 오른쪽 클릭해서 새파일
 2) 첫문자를 대문자오 시작하여 .jsx확장자를 생성한다. 영문 권장
 3) 함수형 컴포넌트를 만들기 위한 js 문법으로 함수 선언과 동일하게 선언
 4) 컴포넌트(함수) 안에 return 뒤로 출력할 html 작성 
 5) 다른(main.jsx) 파일에서 해당 컴포넌트를 import할 수 있게 export 정의한다.
 */

 function Component1( props ){
    return <h1> 내가 만든 첫 컴포넌트</h1> // 가상을 진짜로 만들어 준 역할을 render()가 담당--> main.jsx에 있음

 }// 

 export default Component1; 