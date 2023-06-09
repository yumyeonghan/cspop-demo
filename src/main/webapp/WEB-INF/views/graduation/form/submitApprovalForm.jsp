<%--
  Created by IntelliJ IDEA.
  User: hamhyeonjun
  Date: 2023/02/22
  Time: 1:09
  To change this template use File | Settings | File Templates.
--%>

<%--
    자주 쓰이는 레이아웃 jsp 파일입니다.

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
    <link rel="stylesheet" href="../../../../assets/libs/ion-rangeslider/css/ion.rangeSlider.min.css">
    <link rel="stylesheet" href="../../../../assets/libs/litepicker/dist/css/litepicker.css">
    <link rel="stylesheet" href="../../../../assets/libs/bootstrap-icons/font/bootstrap-icons.css">
    <link rel="stylesheet" href="../../../../assets/libs/magnific-popup/dist/magnific-popup.css">
    <!-- 부트스트랩 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- 부트스트랩 테이블 -->
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.css">
    <!-- Theme CSS -->
    <link rel="stylesheet" href="../../../../assets/css/theme.min.css">
    <style>
        .fixed-top {
            background-color: #672EBB;
        }

        img {
            margin-top: 5%;
        }
    </style>
</head>
<%@include file="../../common/sessionController.jsp"%>
<body>
<%@include file="../../common/header.jsp"%>
<section class="page-start">
    <div class="bg-shape bg-secondary">
        <div class="container">
            <div class="row">
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
                                                    test
                                                </li>
                                            </ol>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-12">
                                        <h1 class="h2 text-white mb-2">test_view</h1>
                                    </div>
                                </div>
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
                <div class="col-xl-8 col-lg-8 col-md-7 col-sm-12 col-12 mb-8">
                    <div class="row">
                        <div class="card">
                            <form method="POST" >
                                <div class="form-group">
                                    <label for="professorName">담당 교수</label>
                                    <input type="text" class="form-control" id="professorName" name="professorName" required>
                                </div>
                                <div class="form-group">
                                    <label for="graduationDate">졸업 날짜</label>
                                    <input type="text" class="form-control" id="graduationDate" name="graduationDate" required>
                                </div>
                                <div class="form-group">
                                    <label>캡스톤 이수</label>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" id="category2" name="capstoneCompletion" value="option1" required>
                                        <label class="form-check-label" for="category2">이수</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" id="category3" name="capstoneCompletion" value="option2" required>
                                        <label class="form-check-label" for="category3">미이수</label>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-lg btn-primary" style="width: 10%">제출</button>
                            </form>

                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="../../common/commonJS.jsp" %>
</body>
</html>
