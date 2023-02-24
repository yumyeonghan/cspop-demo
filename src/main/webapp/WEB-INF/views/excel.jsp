<%@ page import="org.springframework.orm.jpa.JpaTransactionManager" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Excel_View</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

</head>
<body>
<br>
<h3 style="text-align: center">Result Excel</h3><hr>
<form action="excel.read" method="post" enctype="multipart/form-data">
    <h5>엑셀 찾기: <input type="file" name="file"/></h5>
    <h5>엑셀 업로드: <input type="submit" value="업로드" /></h5>
</form>
<form action="excel.download" method="get">
    <h5>엑셀 다운로드: <input type="submit" id="excelDown" value="다운로드"/></h5>
</form>

<table class="table table-striped">
    <thead class="thead-dark">
    <tr>
        <!--엔티티 속성에 맞게 컬럼 설정 추후 데이터 값 변경-->
        <th scope="col">#</th>
        <th scope="col">학번</th>
        <th scope="col">학생 이름</th>
        <th scope="col">교수 이름</th>
        <th scope="col">학생 졸업날짜</th>
        <th scope="col">단계</th>
        <th scope="col">상태</th>
        <th scope="col">기타 자격</th>
        <th scope="col">캡스톤 이수</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="data" items="${dataL}" varStatus="count">
        <tr style="text-align: left">
            <!--행 번호 지정-->
            <td>${count.count}</td>
            <td><c:out value="${data.studentId}"/></td>
            <td><c:out value="${data.studentName}"/></td>
            <td><c:out value="${data.professorName}"/></td>
            <td><c:out value="${data.graduationDate}"/></td>
            <td><c:out value="${data.step}"/></td>
            <td><c:out value="${data.state}"/></td>
            <td><c:out value="${data.otherQualifications}"/></td>
            <td><c:out value="${data.capstoneCompletion}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>