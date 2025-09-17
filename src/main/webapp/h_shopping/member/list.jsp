<%@ page language = "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/h_shopping/header.jsp"></jsp:include>

    <div class="container">
        <h2>회원목록조회/수정</h2>
        <table>
            <thead>
            <tr>
                <th>회원번호</th>
                <th>회원성명</th>
                <th>전화번호</th>
                <th>주소</th>
                <th>가입일자</th>
                <th>고객등급</th>
                <th>거부지역</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td><a href="/h_shopping/member/detail.jsp?memberId=100001">100001</a></td>
                    <td>홍길동</td>
                    <td>010-1234-5678</td>
                    <td>서울시 강남구</td>
                    <td>2023-01-15</td>
                    <td>A</td>
                    <td>NY</td>
                </tr>
            </tbody>
        </table>    
    </div>
<script src="/h_shopping/js/listMember.js"></script>
<jsp:include page="/h_shopping/footer.jsp"></jsp:include>