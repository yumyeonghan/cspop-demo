`<%--
  Created by IntelliJ IDEA.
  User: hamhyeonjun
  Date: 2023/02/22
  Time: 1:03
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


    <!-- Theme CSS -->
    <link rel="stylesheet" href="../../../assets/css/theme.min.css">
</head>

<body class="bg-light">
<!-- content section -->

<div class="d-flex align-items-center position-relative vh-100">


    <div class="col-lg-5 col-xl-4 d-none d-lg-flex border-top border-primary border-4 align-items-center vh-100 px-0"
         style="background-image: url(../assets/images/register-img.jpg); background-position:13% 50%;">
    </div>
    <div class="container">
        <div class="row g-0">
            <div class="col-md-8 col-lg-7 col-xl-6 offset-md-2 offset-lg-2 offset-xl-3 space-top-3 space-lg-0">
                <!-- Form -->
                <div class="bg-white p-4 p-xl-6 p-xxl-8 p-lg-4 rounded-3 border">
<%--                    <form id="form" action="/api/home" method="get">--%>
                        <h1 class="mb-2 text-center h3 ">Register</h1>

                        <!-- <div class="mb-3">
                            <label for="id" class="form-label">학번<span class="text-danger">*</span> </label>
                            <input type="text" id="id" class="form-control" placeholder="학번을 입력하세요" required="">
                        </div> -->
                        <div class="mb-3">
                            <div class="">
                                <label for="id" class="form-label">학번</label><span id="warningID"></span>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="id" placeholder="학번을 입력해주세요."
                                           value="" required maxlength="9">
                                    <button class="btn btn-outline-primary" onclick=checkId()>중복확인
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="mb-3 ">
                            <label for="password" class="form-label">비밀번호<span class="text-danger">*</span></label>
                            <input type="password" id="password" class="form-control" placeholder="8글자 이상으로 설정하세요"
                                   required="">
                        </div>
                        <div class="mb-3 ">
                            <label for="password" class="form-label">비밀번호 확인<span
                                    class="text-danger">*</span></label>
                            <input type="password" id="password2" class="form-control" placeholder="비밀번호 확인"
                                   required="">
                        </div>
                        <div class="mb-3">
                            <label for="name" class="form-label">이름<span class="text-danger">*</span></label>
                            <input type="text" id="name" class="form-control" placeholder="이름을 입력해주세요" required="">
                        </div>
                        <div class="md-3">
                            <label for="gender" class="form-label">성별</label>
                            <div id="gender">
                                <div class="form-check">
                                    <input id="male" name="gender" type="radio" class="form-check-input" value="남"
                                           checked required>
                                    <label class="form-check-label" for="male">남</label>
                                </div>
                                <div class="form-check">
                                    <input id="female" name="gender" type="radio" class="form-check-input" value="여"
                                           required>
                                    <label class="form-check-label" for="female">여</label>
                                </div>
                            </div>
                        </div>
                        <div class="md-3">
                            <label for="email" class="form-label">E-mail</label><span id="warningEmail"></span>
                            <div class="input-group">
                                <input type="email" class="form-control" id="email" placeholder="E-mail을 입력해주세요.">
                            </div>
                        </div>
                        <div class="col-12">
                            <label for="phone" class="form-label">휴대폰 번호</label>
                            <input type="text" class="form-control" id="phone" placeholder="휴대폰 번호를 입력해주세요."
                                   required maxlength="13"/>
                        </div>
                        <div class="col-12">
                            <label for="major" class="form-label">학과</label>
                            <select class="form-select" id="major" required></select>
                            <div class="invalid-feedback">
                                학과를 선택해 주세요
                            </div>
                        </div>
                        <br>

                        <div class="d-grid">
                            <button class="btn btn-primary" type="submit" onclick="signUp()" id="submit-button" >
                                회원가입
                            </button>
                        </div>
                        <p class="mt-3 mb-0 text-muted font-14">
                            이미 회원입니까?<a href="/api/login">Login</a>
                        </p>
<%--                    </form>--%>
                </div>
                <!-- End Form -->
            </div>
        </div>
    </div>

</div>
<!--  Jquery 가져오기 -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>

<%--account js include--%>
<script src="../../../assets/js/account.js"></script>

<!-- Libs JS -->
<script src="../../../assets/libs/jquery/dist/jquery.min.js"></script>
<script src="../../../assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<script src="../../../assets/libs/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="../../../assets/libs/magnific-popup/dist/jquery.magnific-popup.min.js"></script>
<script src="../../../assets/libs/prismjs/prism.js"></script>
<script src="../../../assets/libs/leaflet/dist/leaflet.js"></script>
<script src="../../../assets/libs/litepicker/dist/litepicker.js"></script>
<script src="../../../assets/libs/ion-rangeslider/js/ion.rangeSlider.min.js"></script>
<script src="../../../assets/libs/inputmask/dist/jquery.inputmask.min.js"></script>

<!-- clipboard -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/1.5.12/clipboard.min.js"></script>
<!-- Theme JS -->
<script src="../../../assets/js/theme.min.js"></script>
</body>

</html>
