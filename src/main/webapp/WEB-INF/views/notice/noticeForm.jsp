<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">

    <div class="py-5 text-center">
        <h2>상품 등록 폼</h2>
    </div>

    <h4 class="mb-3">상품 입력</h4>

    <form method="post" enctype="multipart/form-data">
        <ul>
            <li>제목 <input type="title" name="테스트 공지사항"></li>
            <li>본문 <input type="text" name="테스트 공지사항 입니다~~~~~~"></li>
            <li>파일명 <input type="text" name="uploadFileName"></li>
            <li>파일<input type="file" name="file" ></li>
        </ul>
        <input type="submit"/>
    </form>

</div> <!-- /container -->
</body>
</html>
