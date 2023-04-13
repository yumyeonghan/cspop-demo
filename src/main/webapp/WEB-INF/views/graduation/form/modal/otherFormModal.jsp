<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
    <c:set var="otherFormExists" value="true" />
    <c:forEach var="element" items="${notApprovalList}">
    <c:if test="${element == '기타자격'}">
        <c:set var="otherFormExists" value="false" />
    </c:if>
    </c:forEach>
    <div class="row">
        <div class="row">
            <div class="card" style="padding: 10px;">
                <div class="form-group">
                    <label for="title" class="custom-label">제목</label>
                    <div class="card" style="padding: 5px;">
                        <input type="text" class="form-control" id="title" name="title"
                               value="${otherForm.title}" ${otherFormExists ? 'readonly' : ''} required>
                    </div>
                </div>
                <div class="form-group">
                    <label>구분</label>
                    <div class="card" style="padding: 5px;">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="category1" name="division"
                            ${otherForm.division == '구현논문' ? 'checked' : ''} ${otherFormExists ? 'disabled' : ''} value="option1" required>
                            <label class="form-check-label" for="category1">구현논문</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" id="category2" name="division"
                            ${otherForm.division == '조사(이론)논문' ? 'checked' : ''} ${otherFormExists ? 'disabled' : ''}    value="option2">
                            <label class="form-check-label" for="category2">조사(이론)논문</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="text" class="custom-label">내용</label>
                    <div class="card" style="padding: 5px;">
                        <input type="text" class="form-control" id="text" name="text"
                               value="${otherForm.text}" ${otherFormExists ? 'readonly' : ''} required>
                    </div>
                </div>
                <div class="form-group">
                    <label>기타자격 파일</label>
                    <div class="card" style="padding: 5px;">
                        <a id="otherFormDownloadFile" href="/api/attach/otherForm/${otherForm.id}" style="display: block;">${otherForm.otherFormUploadFile.uploadFileName}</a>
                        <input id="inputFile" type="file" name="otherFormUploadFile" required>
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