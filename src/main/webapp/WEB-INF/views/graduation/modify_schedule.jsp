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
<!--변경할 일정을 선택할 select box 구현-->
<p>변경할 일정:
    <select id="list" name="list">
        <option selected>선택하세요</option>
        <c:forEach var="data" items="${step}">
            <option value="step">${data.step.stepToString}</option>
        </c:forEach>
    </select></p>

<!--선택된 step value를 바탕으로 시작 일정&종료 일정 출력-->
<br>
<div class="div1">
    <p>시작일정:</p>
    <p>종료일정:</p>
</div>

<!--수정 버튼- 변경된 값을 db에 저장& view에 출력-->
<form action="progress_schedule.modify" method="post">
    <input style="float: left" type="submit"  value="수정"/>
</form>

<!--취소 버튼- 이전 화면으로 이동-->
<form action="progress_schedule" method="get">
    <input style="float: left" type="submit"  value="취소"/>
</form>
</body>
<%@include file="../common/commonJS.jsp" %>

<script>
    $("select[name=list]").change(function(){
        //선택한 value에 해당하는 text 출력
        console.log($("select[name=list] option:selected").text());
    });

    //선택되면 날짜를 보이도록
    $('#list').change(function() {
        var result = $('#list option:selected').val();
        if (result == 'step') {
            $('.div1').show();
        }
        else {
            $('.div1').hide();
        }
    });
</script>
</html>

