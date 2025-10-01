// 테스트할 URL (줄바꿈 없이 한 줄로 입력)
const url = "https://apis-navi.kakaomobility.com/v1/directions?origin=127.10764191124568,37.402464820205246&destination=127.11056336672839,37.39419693653072&summary=false&waypoints=127.17354989857544,37.36629687436494&priority=RECOMMEND&car_fuel=GASOLINE&car_hipass=false&alternatives=false&road_details=false";

// API 요청 설정
fetch(url, {
    method: 'GET',
    headers: {
        // 인증 헤더 추가
        'Authorization': 'KakaoAK c23eef8dd6c751e1e1490aee133c63b48f'
    }
})
.then(response => response.json()) // 응답을 JSON 형태로 변환
.then(data => console.log(data))   // 콘솔에 결과 출력
.catch(error => console.error('Error:', error)); // 에러 발생 시 출력