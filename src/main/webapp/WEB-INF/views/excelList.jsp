<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Excel_View</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
<br>
<h3 style="text-align: center">Result Excel</h3><hr>
<table class="table table-striped">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">학번</th>
        <th scope="col">학생 이름</th>
        <th scope="col">교수 이름</th>
        <th scope="col">캡스톤 이수</th>
        <th scope="col">단계</th>
        <th scope="col">상태</th>
        <th scope="col">기타 자격</th>
        <!--현재 엑셀 속성:지연 횟수 ->학생 졸업 날짜로 속성 변경 예정-->
        <th scope="col">지연 횟수</th>
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