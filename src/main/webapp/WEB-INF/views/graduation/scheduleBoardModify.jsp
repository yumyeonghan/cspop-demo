<%--
  Created by IntelliJ IDEA.
  User: Jung kyoungwon
  Date: 2023-03-10
  Time: 오후 4:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <!--css 폴더 이동-->
    <style>
        #check-btn1 { display: none; }
        #check-btn1:checked ~ .form-control1 { display: block; }
        .form-control1 { display: none; }

        #check-btn2 { display: none; }
        #check-btn2:checked ~ .form-control2 { display: block; }
        .form-control2 { display: none; }

        #check-btn3 { display: none; }
        #check-btn3:checked ~ .form-control3 { display: block; }
        .form-control3 { display: none; }

        #check-btn4 { display: none; }
        #check-btn4:checked ~ .form-control4 { display: block; }
        .form-control4 { display: none; }

        #check-btn5 { display: none; }
        #check-btn5:checked ~ .form-control5 { display: block; }
        .form-control5 { display: none; }

        #check-btn6 { display: none; }
        #check-btn6:checked ~ .form-control6 { display: block; }
        .form-control6 { display: none; }
    </style>
    <title>Modify_View</title>
</head>
<%@include file="../common/sessionController.jsp"%>
<body>
<h3>진행일정 게시판 수정-text를 누르시면 수정하실 수 있습니다.</h3><hr>
<form action="../scheduleBoardModify/${data.id}" method="post">
    <label>
        <input id="check-btn1" type="checkbox"/>
        <label for="check-btn1">신청접수</label>
        <textarea class="form-control1" name="receivedText" placeholder="반드시 입력을 해주세요." style="resize: none" cols="90" rows="3">${data.receivedText}</textarea>
        <spring:hasBindErrors name="scheduleBoardDto">
            <c:if test="${errors.hasFieldErrors('receivedText') }">
                <span style="color: red">${errors.getFieldError( 'receivedText' ).defaultMessage }</span>
            </c:if>
        </spring:hasBindErrors>
        <br>
        <input id="check-btn2" type="checkbox"/>
        <label for="check-btn2">제안서</label>
        <textarea class="form-control2" name="proposalText" placeholder="반드시 입력을 해주세요." style="resize: none" cols="90" rows="3">${data.proposalText}</textarea>
        <spring:hasBindErrors name="scheduleBoardDto">
            <c:if test="${errors.hasFieldErrors('proposalText') }">
                <span style="color: red">${errors.getFieldError( 'proposalText' ).defaultMessage }</span>
            </c:if>
        </spring:hasBindErrors>

        <br>
        <input id="check-btn3" type="checkbox"/>
        <label for="check-btn3">중간보고서</label>
        <textarea class="form-control3" name="interimReportText" placeholder="반드시 입력을 해주세요." style="resize: none" cols="90" rows="3">${data.interimReportText}</textarea>
        <spring:hasBindErrors name="scheduleBoardDto">
            <c:if test="${errors.hasFieldErrors('interimReportText') }">
                <span style="color: red">${errors.getFieldError( 'interimReportText' ).defaultMessage }</span>
            </c:if>
        </spring:hasBindErrors>

        <br>
        <input id="check-btn4" type="checkbox"/>
        <label for="check-btn4">최종보고서</label>
        <textarea class="form-control4" name="finalReportText" placeholder="반드시 입력을 해주세요." style="resize: none" cols="90" rows="3">${data.finalReportText}</textarea>
        <spring:hasBindErrors name="scheduleBoardDto">
            <c:if test="${errors.hasFieldErrors('finalReportText') }">
                <span style="color: red">${errors.getFieldError( 'finalReportText' ).defaultMessage }</span>
            </c:if>
        </spring:hasBindErrors>
        <br>
        <input id="check-btn5" type="checkbox"/>
        <label for="check-btn5">최종통과</label>
        <textarea class="form-control5" name="finalPassText" placeholder="반드시 입력을 해주세요." style="resize: none" cols="90" rows="4">${data.finalPassText}</textarea>
        <spring:hasBindErrors name="scheduleBoardDto">
            <c:if test="${errors.hasFieldErrors('finalPassText') }">
                <span style="color: red">${errors.getFieldError( 'finalPassText' ).defaultMessage }</span>
            </c:if>
        </spring:hasBindErrors>
        <br>
        <input id="check-btn6" type="checkbox"/>
        <label for="check-btn6">기타자격</label>
        <textarea class="form-control6" name="otherQualificationsText" placeholder="반드시 입력을 해주세요." style="resize: none" cols="90" rows="3">${data.otherQualificationsText}</textarea>
        <spring:hasBindErrors name="scheduleBoardDto">
            <c:if test="${errors.hasFieldErrors('otherQualificationsText') }">
                <span style="color: red">${errors.getFieldError( 'otherQualificationsText' ).defaultMessage }</span>
            </c:if>
        </spring:hasBindErrors>
    </label>
    <br>
    <input style="float: start" type="submit" value="수정"/>
    <input type="hidden" value="${data.id}">
</form>

<!--취소 버튼- 이전 화면으로 이동-->
<form action="../schedule" method="get">
    <input style="float: left" type="submit" value="뒤로"/>
</form>
</body>
<%@include file="../common/commonJS.jsp" %>
</html>
