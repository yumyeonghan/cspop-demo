<%--
  Created by IntelliJ IDEA.
  User: Jung kyoungwon
  Date: 2023-03-08
  Time: 오전 2:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>CSPOP</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Libs CSS -->
    <link rel="stylesheet" href="../../../assets/libs/ion-rangeslider/css/ion.rangeSlider.min.css">
    <link rel="stylesheet" href="../../../assets/libs/litepicker/dist/css/litepicker.css">
    <link rel="stylesheet" href="../../../assets/libs/bootstrap-icons/font/bootstrap-icons.css">
    <link rel="stylesheet" href="../../../assets/libs/magnific-popup/dist/magnific-popup.css">
    <!-- 부트스트랩 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- 부트스트랩 테이블 -->
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.css">
    <!-- Theme CSS -->
    <link rel="stylesheet" href="../../../assets/css/theme.min.css">
    <style>
        .fixed-top {
            background-color: #672EBB;
        }

        img {
            margin-top: 5%;
        }
    </style>
</head>
<%@include file="../common/sessionController.jsp"%>
<body>
<%@include file="../common/header.jsp"%>
<section class="page-start">
    <!-- pageheader section -->
    <div class="bg-shape bg-secondary">
        <div class="container">
            <div class="row">
                <div class="offset-xl-1 col-xl-10 col-lg-12 col-md-12 col-12">
                    <div class="pt-lg-18 pb-lg-16 py-12 ">
                        <div class="row align-items-center">
                            <div class="col-lg-12 col-md-12 col-12 mb-3">
                                <!-- breadcrumb -->
                                <div class="custom-breadcrumb">
                                    <ol class="breadcrumb mb-2">
                                        <li class="breadcrumb-item text-white">
                                            <a href="/api/home">Home</a>
                                        </li>
                                        <li class="breadcrumb-item active " aria-current="page">
                                            진행일정
                                        </li>
                                    </ol>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-12">
                                <h1 class="h2 text-white mb-2">진행일정</h1>
                                <p class="text-white-50 lead">
                                    졸업 논문 진행일정
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="pb-10 mt-n10">
        <div class="container">
            <div class="row">
                <div class="offset-lg-1 col-lg-10 col-md-12 col-12">
                    <div class="card">
                        <div class="card-body p-4 p-lg-7">
                            <div>
                                <div class="card table-responsive">
                                    <table id="table" class="table mb-0" border-color="black">
                                        <thead class="table-dark">
                                        <tr style="text-align: center">
                                            <th data-field="step">
                                                <div class="th-inner sortable both">단계</div>
                                            </th>
                                            <th data-field="start_date">
                                                <div class="th-inner sortable both">시작 일정</div>
                                            </th>
                                            <th data-field="final_date">
                                                <div class="th-inner sortable both">종료 일정</div>
                                            </th>
                                            <th data-field="state">
                                                <div class="th-inner sortable both">상태</div>
                                            </th>
                                        </tr>
                                        </thead>
                                        <c:forEach var="data" items="${dataL}">
                                            <tbody>
                                            <!--enum type stepToString-->
                                            <tr style="text-align: center">
                                                <td><a href="modify_schedule/${data.id}">${data.step.stepToString}</a></td>
                                                <td>${data.startDate}</td>
                                                <td>${data.endDate}</td>
                                                <td>${data.scheduleState.scheduleStateToString}</td>
                                            </tr>
                                            </tbody>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <!--상세 내용 양식 조정 중-->
                        <hr><br>
                        <c:forEach var="data" items="${dataL2}">
                            <h4 class="alert-light-info">신청접수</h4>
                            <p class="bi-text-indent-left">${data.receivedText}</p>
                            <br><br>
                            <h4 class="alert-light-info">제안서</h4>
                            <p class="bi-text-indent-left">${data.proposalText}</p>
                            <br><br>
                            <h4 class="alert-light-info">중간보고서</h4>
                            <p class="bi-text-indent-left">${data.interimReportText}</p><br><br>
                            <h4 class="alert-light-info">최종보고서</h4>
                            <p class="bi-text-indent-left">${data.finalReportText}</p><br><br>
                            <h4 class="alert-light-info">최종통과</h4>
                            <p class="bi-text-indent-left">${data.finalPassText}</p><br><br>
                            <h4 class="alert-light-info">기타자격</h4>
                            <p class="bi-text-indent-left">${data.otherQualificationsText}</p>
                            <br>
                            <button style="width: 80px; height: 30px;" type="submit"
                                    onclick="location.href='schedule_board/${data.id}'">수정</button>
                            <br>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="../common/commonJS.jsp" %>
</body>
</html>


