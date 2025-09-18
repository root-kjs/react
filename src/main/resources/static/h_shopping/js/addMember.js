// [1] 회원등록
console.log("회원등록 js open");

// *** 유효성검사 체크리스트 ***
const signPass = [false]; // 0: 연락처

const addMember = async () => {
    console.log("회원등록 js start");

    // 마크업 DOM객체 가져오기 
    const mNameInput = document.querySelector(".mNameInput");
    const mPhoneInput = document.querySelector(".mPhoneInput");
    const mAddressInput = document.querySelector(".mAddressInput");
    const mGradeInput = document.querySelector(".mGradeInput");
    const mCityCodeInput = document.querySelector(".mCityCodeInput");

    // 마크업 입력값 가져오기
    const custname = mNameInput.value.trim();
    const phone = mPhoneInput.value;
    const address = mAddressInput.value.trim();
    const grade = mGradeInput.value.trim();
    const city = mCityCodeInput.value.trim();
    
    // 빈값 체크
    if (!custname || !phone || !address || !grade || !city) {
        alert('모든 항목을 입력하세요');
        
        // 입력되지 않은 첫 번째 필드로 포커스 이동
        if (!custname) mNameInput.focus();
        else if (!phone) mPhoneInput.focus();
        else if (!address) mAddressInput.focus();
        else if (!grade) mGradeInput.focus();
        else if (!city) mCityCodeInput.focus();
        
        return;
    }

    // 고객 등급 유효성 검사 (A, B, C만 허용)
    const validGrades = ['A', 'B', 'C', 'a', 'b', 'c'];
    if (!validGrades.includes(grade)) {
        alert('고객 등급은 A, B, C 중 하나만 입력 가능합니다.');
        mGradeInput.focus(); // 해당 입력란으로 포커스 이동
        return;
    }

    // 도시 코드 유효성 검사 (두 자리 숫자만 허용)
    const cityRegex = /^[0-9]{2}$/;
    if (!cityRegex.test(city)) {
        alert('도시코드는 2자리 숫자로 입력해야 합니다.');
        mCityCodeInput.focus(); // 해당 입력란으로 포커스 이동
        return;
    }
    
    // 연락처 중복 검사 통과 여부 확인
    if (!signPass[0]) {
        alert('전화번호 중복 검사를 통과해야 합니다.');
        mPhoneInput.focus(); // 해당 입력란으로 포커스 이동
        return;
    }

    // 객체화 
    const obj = { custname, phone, address, grade, city };

    // fetch로 데이터 전송
    try {
        const option = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(obj)
        };
        const response = await fetch("/member/signup", option);
        const data = await response.json();
        
        if (data > 0) {
            alert("회원등록 성공");
            // 회원등록 성공시 입력창 초기화
            mNameInput.value = "";
            mPhoneInput.value = "";
            mAddressInput.value = "";
            mGradeInput.value = "";
            mCityCodeInput.value = "";
            // 연락처 초기화
            signPass[0] = false;
            // 회원목록 페이지 이동
            location.href = "/h_shopping/member/list.jsp";
        } else {
            alert("회원등록 실패");
        }
    } catch (error) {
        console.log(error);
    }
}; //func end 

// [2] 연락처중복검사 : 인풋에 입력할때마다 발동
const phonecheck = async () => {
    const mphone = document.querySelector('.mPhoneInput').value; // 1. 입력받은 연락처 가져오기 
    const phoneCheck = document.querySelector('.phoneCheck'); // 2. 결과 메시지를 출력할 dom객체 가져오기 
    const regex = /^\d{3}-\d{4}-\d{4}$/; // 정규표현식 : 000-0000-0000 형식
    
    // 유효성 검사: 전화번호 형식 검사
    if (!regex.test(mphone)) {
        phoneCheck.innerHTML = `❌ 전화번호 형식(000-0000-0000)이 아닙니다.`;
        phoneCheck.style.color = 'red';
        signPass[0] = false;
        return; // 함수 종료 
    }

    // 중복 검사 (fetch)
    try { 
        const option = { method: "GET" };
        const response = await fetch(`/member/check?type=phone&data=${mphone}`, option);
        const data = await response.json();
        
        if (data) { // 중복이면 true
            phoneCheck.innerHTML = `❌ 사용중인 연락처 입니다.`;
            phoneCheck.style.color = 'red';
            signPass[0] = false;
        } else { // 중복이 아니면 false
            phoneCheck.innerHTML = `✅ 사용가능한 연락처 입니다.`;
            phoneCheck.style.color = 'green';
            signPass[0] = true; // 상태 변경
        }
    } catch (error) { 
        console.log(error);
        phoneCheck.innerHTML = `오류 발생. 다시 시도해주세요.`;
        phoneCheck.style.color = 'gray';
        signPass[0] = false;
    }
}; // func end 

// 가입일자 > 오늘 날짜 넣기 > 문서가 모두 로드되면 실행되는 함수
document.addEventListener('DOMContentLoaded', (event) => {
    const today = new Date();
    function formatDate(date) {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    }
    const dateInput = document.querySelector('.mJoinDateInput');
    dateInput.value = formatDate(today);
});