<%@ page language = "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/h_shopping/header.jsp"></jsp:include>

    <div class="container">
        <h2>홈쇼핑 회원 등록</h2>
        <table>
            <tr>
                <th>회원번호(자동발생)</th>
                <td><input type="text" name="memberId" readonly /></td>
            </tr>
            <tr>
                <th>회원성명</th>
                <td><input type="text" name="memberName" required /></td>
            </tr>
            <tr>
                <th>회원전화</th>
                <td><input type="text" name="memberPhone" required /></td>
            </tr>
            <tr>
                <th>회원주소</th>
                <td><input type="text" name="memberAddress" required /></td>
            <tr>
                <th>가입일자</th>
                <td><input type="date" name="memberJoinDate" readonly /></td>
            </tr>
            <tr>
               <th>고객등급(A:VIP,B:일반,C:직원)</th>
                <td><input type="text" name="memberGrade" required/></td>
            </tr>
            <tr>
                <th>도시코드</th>
                <td><input type="text" name="memberCityCode" required/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="button" onclick="updateMember()">수정</button>
                    <button type="button">조회</button>
                </td>
            </tr>
        </table>    
    </div>
<script src="/h_shopping/js/updateMember.js"></script>
<jsp:include page="/h_shopping/footer.jsp"></jsp:include>