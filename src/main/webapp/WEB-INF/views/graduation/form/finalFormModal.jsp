<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container">
    <c:set var="finalFormExists" value="true" />
    <c:forEach var="element" items="${notApprovalList}">
        <c:if test="${element == '최종보고서'}">
            <c:set var="finalFormExists" value="false" />
        </c:if>
    </c:forEach>
    <div class="row">
        <!-- <div class="col-xl-8 col-lg-8 col-md-7 col-sm-12 col-12 mb-8"> -->
            <div class="row">
                <form id="finalFormModal" method="POST" action="/api/finalForm" enctype="multipart/form-data" onsubmit="return confirm('제출 하시겠습니까?')">
                    <div class="card" style="padding: 10px;">
                        <input type="hidden" name="studentId" value=${userDetail.studentId}>
                        <input type="hidden" name="studentName" value=${userDetail.studentName}>
                        <input type="hidden" name="department" value=${userDetail.department}>
                        <input type="hidden" name="graduationDate" value=${userDetail.graduationDate}>
                        <input type="hidden" name="advisor" value="${userDetail.advisor}">
                        <input type="hidden" name="qualification" value="${userDetail.qualification}">
                        <div class="form-group">
                            <label for="title" class="custom-label">제목</label>
                            <div class="card" style="padding: 5px;">
                                <input type="text" class="form-control" id="title" name="title"
                                value="${finalForm.title}" ${finalFormExists ? 'readonly' : ''} required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>구분</label>
                            <div class="card" style="padding: 5px;">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" id="category1" name="division"
                                    ${finalForm.division == 'option1' ? 'checked' : ''} ${finalFormExists ? 'disabled' : ''} value="option1" required>
                                    <label class="form-check-label" for="category1">구현논문</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" id="category2" name="division"
                                    ${finalForm.division == 'option2' ? 'checked' : ''} ${finalFormExists ? 'disabled' : ''}    value="option2">
                                    <label class="form-check-label" for="category2">조사(이론)논문</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>자격요건</label>
                            <div class="card" style="padding: 5px;">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="qualification"
                                    ${finalForm.qualification == 'check1' ? 'checked' : ''} ${finalFormExists ? 'disabled' : ''}
                                     id="category3" value="check1" required>
                                    <label class="form-check-label" for="category3">논문양식파일사용</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="qualification"
                                    ${finalForm.qualification == 'check2' ? 'checked' : ''} ${finalFormExists ? 'disabled' : ''}
                                    id="category4" value="check2" required>
                                    <label class="form-check-label" for="category4">목차,서론,본론,결론,참고문헌
                                        포함</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="qualification"
                                    ${finalForm.qualification == 'check3' ? 'checked' : ''} ${finalFormExists ? 'disabled' : ''}
                                    id="category5" value="check3" required>
                                    <label class="form-check-label" for="category5">본인이 직접 작성한 파일임을
                                        확인함</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pageNumber">쪽수</label>
                            <div class="card" style="padding: 5px;">
                                <div class="row">
                                    <div class="col-lg-2 col-3">
                                        <input type="number" class="form-control" name="pageNumber"
                                        value=${finalForm.pageNumber} ${finalFormExists ? 'readonly' : ''} 
                                        id="pageNumber" style="width: 100px" required>
                                    </div>
                                    <div class="col align-self-center">
                                        쪽 (서론/본론/결론/참고문헌 부분 쪽수, 표지/목차 제외)
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>최종보고서 파일</label>
                            <div class="card" style="padding: 5px;">
                                <input id="inputFile" type="file" name="finalFormUploadFile">
                            </div>
                        </div>
                    </div>
                    <div class="form-group" style="text-align: right; margin-top: 5px;">
                        <label for="sign">서명</label>
                        <input type="text" class="form-control" name="sign" id="sign" placeholder="본인의 이름을 입력"
                            style="display: inline-block; width: 200px;">
                        <br>
                        <!-- <c:if test="${finalFormExists}">
                            <button type="button" onclick="clickFinalFormModify(event)" class="btn btn-lg btn-primary"
                                style="width: 13%; height: 10%;">수정</button>
                        </c:if>
                        <c:if test="${!finalFormExists}">
                            <button type="submit" class="btn btn-lg btn-primary"
                                style="width: 13%; height: 10%;">제출</button>
                        </c:if> -->
                        <!-- <button type="button" class="btn btn-lg btn-secondary"
                            style="width: 13%; height: 10%;">취소</button> -->
                    </div>
                </form>
            </div>
        <!-- </div> -->
        
</div>