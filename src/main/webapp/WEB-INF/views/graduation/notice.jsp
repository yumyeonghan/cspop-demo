<%--
  Created by IntelliJ IDEA.
  User: hamhyeonjun
  Date: 2023/02/22
  Time: 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<!-- header -->
<div class="header fixed-top border-3 border-top border-primary border-sm">
    <!-- navigation start -->
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-default">
            <a class="navbar-brand" href="/home">
                <img src="../../../assets/images/fitness/cspop_logo.png" alt="" width="100em">
            </a>
            <button class="navbar-toggler collapsed" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbar-default" aria-controls="navbar-default" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="icon-bar top-bar mt-0"></span>
                <span class="icon-bar middle-bar"></span>
                <span class="icon-bar bottom-bar"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbar-default">
                <button class="navbar-toggler collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbar-default" aria-controls="navbar-default" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <i class="fas fa-times"></i>
                </button>
                <ul class="navbar-nav mx-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link" href="/api/graduation/notice" data-bs-toggle="dropdown" aria-haspopup="true"
                           aria-expanded="false" data-bs-display="static">
                            공지사항
                        </a>
                    </li>
                    <li class="nav-item dropdown ">
                        <a class="nav-link dropdown-toggle" href="/api/graduation/announcement" aria-haspopup="true" aria-expanded="false" data-bs-display="static">
                            안내 및 내규
                        </a>
                    </li>
                    <li class="nav-item dropdown ">
                        <a class="nav-link dropdown-toggle" href="/api/graduation/progress_schedule"
                           data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                           data-bs-display="static">
                            진행일정
                        </a>
                    </li>
                    <li class="nav-item dropdown ">
                        <a class="nav-link dropdown-toggle" href="/api/graduation/target_management"
                           data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                           data-bs-display="static">
                            대상자 전체 관리
                        </a>
                    </li>
                    <li class="nav-item dropdown ">
                        <a class="nav-link dropdown-toggle" href="/api/graduation/application_management" data-bs-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false" data-bs-display="static">
                            신청서 접수 관리
                        </a>
                    </li>
                    <li class="nav-item dropdown ">
                        <a class="nav-link dropdown-toggle" href="/api/graduation/graduation_status" data-bs-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false" data-bs-display="static">
                            나의 졸업 현황
                        </a>
                    </li>
                </ul>
                <div id="userCheck"></div>
            </div>
        </nav>
    </div>
</div>
<!-- navigation close -->
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
                                            공지사항
                                        </li>
                                    </ol>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-12">
                                <h1 class="h2 text-white mb-2">공지사항</h1>
                                <p class="text-white-50 lead">
                                    졸업 논문 공지사항
                                </p>
                            </div>
                            <div class="col-lg-6 col-md-6 col-12">
                                <form>
                                        <span class="position-absolute ps-3 pt-1 mt-1">
                                            <i class="fe fe-search"></i>
                                        </span>
                                    <input type="search" class="form-control ps-6 mb-2 border-0"
                                           placeholder="search"/>
                                </form>
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
                                        <tr>
                                            <th data-field="board_id">
                                                <div class="th-inner sortable both">번호</div>
                                            </th>
                                            <th data-field="title">
                                                <div class="th-inner sortable both">제목</div>
                                            </th>
                                            <th data-field="writer_id">
                                                <div class="th-inner sortable both">글쓴이</div>
                                            </th>
                                            <th data-field="writed_date">
                                                <div class="th-inner sortable both">작성일</div>
                                            </th>
                                            <th data-field="views">
                                                <div class="th-inner sortable both">조회수</div>
                                            </th>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="../common/commonJS.jsp" %>
<script src="../../../assets/js/login.js"></script>
<!--  Jquery 가져오기 -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<!-- bootstrap 가져오기 -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<!-- bootstrap table 가져오기  -->
<script src="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.js"></script>

<script>
    $(() => {
        let $table = $("#table");
        // 테이블 표현할 데이터 표현
        let data = [
            {
                "board_id": "1",
                "title": "test1",
                "writer_id": "admin",
                "writed_date": "2023-02-18",
                "views": "1"
            },
            {
                "board_id": "2",
                "title": "test2",
                "writer_id": "admin2",
                "writed_date": "2023-01-11",
                "views": "120"
            },
            {
                "board_id": "3",
                "title": "test3",
                "writer_id": "admin3",
                "writed_date": "2023-03-01",
                "views": "203"
            },
            {
                "board_id": "4",
                "title": "test4",
                "writer_id": "admin4",
                "writed_date": "2023-04-16",
                "views": "999"
            },
            {
                "board_id": "5",
                "title": "test5",
                "writer_id": "admin5",
                "writed_date": "2023-05-12",
                "views": "0"
            }
        ];
        $table.bootstrapTable({data: data})
    });
</script>
</body>

</html>
