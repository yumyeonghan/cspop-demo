<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error Page</title>
</head>
<body style="float: contour">
    <h1 style="text-align: center">파일을 업로드하지 않았거나 잘못된 파일을
    업로드 하셨습니다.</h1>
    <a href="excel" style=" float: right">뒤로 가기</a>
    <hr>
    <h4 style="text-align: center">Error Type:<%=exception.getClass().getName()%></h4>
    <h4 style="text-align: center">Error Message:<%=exception.getMessage()%></h4>
</body>
</html>
