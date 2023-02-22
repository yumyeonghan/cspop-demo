<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Excel_View</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
<table class="table table-striped">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">학번</th>
        <th scope="col">학생 이름</th>
        <th scope="col">교수 이름</th>
        <th scope="col">학생 졸업 날짜</th>
        <th scope="col">단계</th>
        <th scope="col">상태</th>
        <th scope="col">기타 자격</th>
        <th scope="col">캡스톤 이수</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach var="data" items="${dataL}">
            <td c:text="${data.studentId}"></td>
            <td c:text="${data.studentName}"></td>
            <td c:text="${data.professorName}"></td>
            <td c:text="${data.graduationDate}"></td>
            <td c:text="${data.step}"></td>
            <td c:text="${data.state}"></td>
            <td c:text="${data.otherQualifications}"></td>
            <td c:text="${data.capstoneCompletion}"></td>
        </c:forEach>
    </tr>
    </tbody>
</table>
</body>
</html>