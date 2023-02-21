<%--
  Created by IntelliJ IDEA.
  User: hamhyeonjun
  Date: 2023/02/22
  Time: 0:51
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
         style="background-image: url(../../../assets/images/register-img.jpg); background-position:13% 50%;">
        <!-- not yet -->
    </div>
    <div class="container">
        <div class="row g-0">
            <div class="col-md-8 col-lg-7 col-xl-6 offset-md-2 offset-lg-2 offset-xl-3 space-top-3 space-lg-0">
                <!-- Form -->
                <div class="bg-white p-4 p-xl-6 p-xxl-8 p-lg-4 rounded-3 border">
                    <form>
                        <h1 class="mb-1 text-center h3">LOGIN</h1>
                        <div class="mb-3">
                            <label for="text" class="form-label">ID<span class="text-danger">*</span> </label>
                            <input type="text" id="text" class="form-control " placeholder="ID" required="">
                        </div>
                        <div class="mb-3 mb-4">
                            <label for="password" class="form-label">Password<span
                                    class="text-danger">*</span></label>
                            <input type="password" id="password" class="form-control" placeholder="Password"
                                   required="">
                        </div>
                        <div class="d-grid">
                            <button class="btn btn-primary" type="submit">
                                LOGIN
                            </button>
                        </div>
                        <div class="d-xxl-flex justify-content-between mt-4 ">
                                <span class="text-muted font-14 mb-0">
                                    <a href="/api/signup">회원가입</a>
                                </span>
                            <span>&nbsp;</span>
                            <span class="font-14 mb-0">
                                    <a href="#">비밀번호 찾기</a>
                                </span>
                        </div>
                    </form>
                </div>
                <!-- End Form -->
            </div>
        </div>
    </div>

</div>

<!-- Optional JavaScript -->


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
