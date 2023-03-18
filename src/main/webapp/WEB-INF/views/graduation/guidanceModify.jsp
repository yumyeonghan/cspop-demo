<%--
  Created by IntelliJ IDEA.
  User: Jung kyoungwon
  Date: 2023-03-16
  Time: 오후 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%@include file="../common/sessionController.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<body>
<h3>졸업논문 안내 및 내규 수정</h3><hr>
<form action="../modify_guide/${data.id}" method="post">
    <input type="hidden" value="${data.id}">
    <p>변경사항:</p> <textarea class="form-control" name="text" style="resize: none" cols="90" rows="3">${data.text}</textarea><br>
    <spring:hasBindErrors name="guidanceBoardDto">
        <c:if test="${errors.hasFieldErrors('text') }">
            <h4 style="color: red">${errors.getFieldError( 'text' ).defaultMessage }</h4>
        </c:if>
    </spring:hasBindErrors>
    <input style="float: start" type="submit" value="수정"/>
</form>

<!--취소 버튼- 이전 화면으로 이동-->
<form action="../guide" method="get">
    <input style="float: start" type="submit" value="뒤로"/>
</form>
</body>
<%@include file="../common/commonJS.jsp" %>
</html>
