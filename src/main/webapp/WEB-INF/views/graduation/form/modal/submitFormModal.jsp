<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
    <c:set var="submitFormExists" value="true" />
    <c:forEach var="element" items="${notApprovalList}">
    <c:if test="${element == '신청접수'}">
        <c:set var="submitFormExists" value="false" />
    </c:if>
    </c:forEach>
    <div class="row">
        <div class="row">
            <div class="card" style="padding: 10px;">
                <div class="form-group">
                    <label for="qualification" class="custom-label">자격</label>
                    <div class="card" style="padding: 5px;">
                        <input type="text" class="form-control" id="qualification" name="qualification"
                               value="${submitForm.qualification}" ${submitFormExists ? 'readonly' : ''} required>
                    </div>
                </div>
            </div>
            <div class="form-group" style="text-align: right; margin-top: 5px;">
                <label for="sign">서명</label>
                <input type="text" class="form-control" name="sign" id="sign" placeholder="본인의 이름을 입력"
                       style="display: inline-block; width: 200px;">
                <br>
            </div>
        </div>
    </div>