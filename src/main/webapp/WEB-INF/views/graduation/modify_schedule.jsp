<%--
  Created by IntelliJ IDEA.
  User: Jung kyoungwon
  Date: 2023-03-08
  Time: 오전 2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Modify_View</title>
</head>
<%@include file="../common/sessionController.jsp"%>
<body>
<h3>졸업논문 일정수정</h3><hr>
<!--변경할 일정을 선택할 select box 구현
<p>변경할 일정:
    <select id="list" name="list" style="display: inline-block; width: 200px;">
        <option value="default">선택하세요</option>

    </select></p>-->

<!--선택된 step value를 바탕으로 시작 일정&종료 일정 출력-->

<!--수정 버튼- 변경된 값을 db에 저장& view에 출력 input에 해당하는 name parameter 값 일치 필요!-->
<form action="../modify_schedule/${data.id}" method="post">

    <p>변경할 일정: <input type="text" class="form-control" disabled value="${data.step.stepToString}" style="width: 150px;"></p>
    <p>시작일정: <input type="date" name="startDate" class="form-control" value="${data.startDate}" style="width: 150px;" required/> </p>
    <p>종료일정: <input type="date" name="endDate" class="form-control" value="${data.endDate}" style="width: 150px;" required/></p>
    <input style="float: left" type="submit" value="수정"/>
    <input type="hidden" value="${data.id}">

    <!--추후 일정에 따른 상태 변경 코드 진행-->
    <input type="hidden" name="scheduleState" disabled value="${data.scheduleState}">
</form>

<!--취소 버튼- 이전 화면으로 이동-->
<form action="../progress_schedule" method="get">
    <input style="float: left" type="submit" value="뒤로"/>
</form>
</body>
<%@include file="../common/commonJS.jsp" %>
</html>

