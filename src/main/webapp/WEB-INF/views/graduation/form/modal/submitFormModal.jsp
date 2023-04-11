<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="../../../../assets/js/modal/submitFormModal.js"></script>
<div class="container">
    <c:set var="submitFormExists" value="true" />
    <c:forEach var="element" items="${notApprovalList}">
    <c:if test="${element == '신청접수'}">
        <c:set var="submitFormExists" value="false" />
    </c:if>
    </c:forEach>
    <div class="row">
        <div class="card" style="padding: 10px;">
            <div class="col-md-6">
                <button type="button" class="btn btn-lg btn-${submitForm.qualification == '기타자격' ? '' : 'outline-'}primary" 
                ${submitFormExists ? "disabled" : ""} style="width: 100%" value="기타자격">기타자격</button>
            </div>
            <div class="col-md-6">
                <button type="button" class="btn btn-lg btn-${submitForm.qualification == '논문' ? '' : 'outline-'}primary" 
                ${submitFormExists ? "disabled" : ""} style="width: 100%" value="논문">졸업논문</button>
            </div>
        </div>
        </div>
</div>