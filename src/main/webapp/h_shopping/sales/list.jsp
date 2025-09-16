<%@ page language = "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/h_shopping/header.jsp"></jsp:include>

    <div class="container">
        <h2>회원목록조회/수정</h2>
        <table>
            <thead>
            <tr>
                <th>회원번호</th>
                <th>회원성명</th>
                <th>고객등급</th>
                <th>매출</th>
            </tr>
            </thead>
            <tbody id="salesBody">
                <tr>
                    <td>1</td>
                    <td>홍길동</td>
                    <td>VIP</td>
                    <td>1,000,000</td>
                </tr>
            </tbody>
        </table>    
    </div>
<script src="/h_shopping/js/listSales.js"></script>
<jsp:include page="/h_shopping/footer.jsp"></jsp:include>