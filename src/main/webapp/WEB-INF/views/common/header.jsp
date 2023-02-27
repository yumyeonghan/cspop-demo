<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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