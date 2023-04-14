<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
    <c:set var="interimFormExists" value="true" />
    <c:forEach var="element" items="${notApprovalList}">
    <c:if test="${element == '중간보고서'}">
        <c:set var="interimFormExists" value="false" />
    </c:if>
    </c:forEach>
    <div class="row">
        <div class="row">
            <div class="card" style="padding: 10px;">
                <div class="form-group">
                    <label for="title" class="custom-label">제목</label>
                    <div class="card" style="padding: 5px;">
                        <input type="text" class="form-control" id="title" name="title"
                               value="${interimForm.title}" ${interimFormExists ? 'readonly' : ''} required>
                    </div>
                </div>
                <div class="form-group">
                    <label>구분</label>
                    <div class="card" style="padding: 5px;">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="category1" name="division"
                            ${interimForm.division == '구현논문' ? 'checked' : ''} ${interimFormExists ? 'disabled' : ''} value="option1" required>
                            <label class="form-check-label" for="category1">구현논문</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="category2" name="division"
                            ${interimForm.division == '조사(이론)논문' ? 'checked' : ''} ${interimFormExists ? 'disabled' : ''}    value="option2">
                            <label class="form-check-label" for="category2">조사(이론)논문</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="text" class="custom-label">진행내용</label>
                    <div class="card" style="padding: 5px;">
                        <input type="text" class="form-control" id="text" name="text"
                               value="${interimForm.text}" ${interimFormExists ? 'readonly' : ''} required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="plan" class="custom-label">향후계획</label>
                    <div class="card" style="padding: 5px;">
                        <input type="text" class="form-control" id="plan" name="plan"
                               value="${interimForm.plan}" ${interimFormExists ? 'readonly' : ''} required>
                    </div>
                </div>
                <div class="form-group">
                    <label>중간보고서 파일</label>
                    <div class="card" style="padding: 5px;">
                        <a id="interimFormDownloadFile" href="/api/attach/interimForm/${interimForm.id}" style="display: block;">${interimForm.interimFormUploadFile.uploadFileName}</a>
                        <input id="inputFile" type="file" name="interimFormUploadFile" required>
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