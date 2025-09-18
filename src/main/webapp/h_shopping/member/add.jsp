<%@ page language = "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/h_shopping/header.jsp"></jsp:include>

    <div class="container">
        <h2>홈쇼핑 회원 등록</h2>
        <table>
            <tr>
                <th>회원번호(자동발생)</th>
                <td><input type="text" class="mIdInput" readonly /></td>
            </tr>
            <tr>
                <th>회원성명</th>
                <td><input type="text" class="mNameInput" required /></td>
            </tr>
            <tr>
                <th>회원전화</th>
                <td><input type="text" onkeyup="phonecheck()" class="mPhoneInput" required pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}"/><div class="phoneCheck"> </div></td>
            </tr>
            <tr>
                <th>회원주소</th>
                <td><input type="text" class="mAddressInput" required /></td>
            <tr>
                <th>가입일자</th>
                <td><input type="date" class="mJoinDateInput" readonly /></td>
            </tr>
            <tr>
                <th>고객등급(A:VIP,B:일반,C:직원)</th>
                <td><input type="text" class="mGradeInput" required minlength="1" maxlength="1" /></td>
            </tr>
            <tr>
                <th>도시코드</th>
                <td><input type="text" class="mCityCodeInput" required  pattern="[0-9]{2}" maxlength="2" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="button" onclick="addMember()">등록</button>
                    <button type="button">조회</button>
                </td>
            </tr>
        </table>    
    </div>
<script src="/h_shopping/js/addMember.js"></script>
<jsp:include page="/h_shopping/footer.jsp"></jsp:include>