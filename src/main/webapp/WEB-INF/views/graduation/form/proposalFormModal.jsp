<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
    <c:set var="proposalFormExists" value="true" />
    <c:forEach var="element" items="${notApprovalList}">
        <c:if test="${element == '제안서'}">
            <c:set var="proposalFormExists" value="false" />
        </c:if>
    </c:forEach>
    <div class="row">
        <div class="row">
            <form id="proposalFormModal" method="POST" action="/api/proposalForm" enctype="multipart/form-data" onsubmit="return confirm('제출 하시겠습니까?')">
                <div class="card" style="padding: 10px;">
                    <input type="hidden" name="studentId" value=${userDetail.studentId}>
                    <input type="hidden" name="studentName" value=${userDetail.studentName}>
                    <input type="hidden" name="department" value=${userDetail.department}>
                    <input type="hidden" name="graduationDate" value=${userDetail.graduationDate}>
                    <input type="hidden" name="advisor" value="${userDetail.advisor}">
                    <input type="hidden" name="qualification" value="${userDetail.qualification}">
                    <div class="form-group">
                        <label for="title">제목</label>
                        <div class="card" style="padding: 5px;">
                            <input type="text" class="form-control" id="title" name="title"
                            value="${proposalForm.title}" ${proposalFormExists ? 'readonly' : ''} required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>구분</label>
                        <div class="card" style="padding: 5px;">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" id="category1" name="division"
                                ${proposalForm.division == 'option1' ? 'checked' : ''} ${proposalFormExists ? 'disabled' : ''} value="option1" required>
                                <label class="form-check-label" for="category1">구현논문</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" id="category2" name="division"
                                ${proposalForm.division == 'option2' ? 'checked' : ''} ${proposalFormExists ? 'disabled' : ''}    value="option2">
                                <label class="form-check-label" for="category2">조사(이론)논문</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="keywords">키워드</label>
                        <div class="card" style="padding: 5px;">
                            <input type="text" class="form-control" id="keywords" name="keyword"
                            value="${proposalForm.keyword}" ${proposalFormExists ? 'readonly' : ''} required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content">내용</label>
                        <div class="card" style="padding: 5px;">
                            <textarea class="form-control" id="content" name="text"
                            value="${proposalForm.text}" ${proposalFormExists ? 'readonly' : ''} required></textarea>
                        </div>
                    </div>
                </div>
                <div class="form-group" style="text-align: right; margin-top: 5px;">
                    <label for="sign">서명</label>
                    <input type="text" class="form-control" name="sign" id="sign" placeholder="본인의 이름을 입력"
                        style="display: inline-block; width: 200px;">
                    <br>
                </div>
            </form>
        </div>
</div>
