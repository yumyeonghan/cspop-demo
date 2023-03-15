<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <form method="post" enctype="multipart/form-data">
        <ul>
            <li>제목 <input type="text" name="title"></li>
            <li>본문 <input type="text" name="text"></li>


        </ul>
        <!-- multiple 속성추가 -->
        <h2>파일 여러개 올릴꺼면 한번에 여러개 선택해주세요~</h2>
        <input type="file" name="files" placeholder="파일 선택" multiple/><br/>
        <input type="submit" value="upload">
    </form>

</div> <!-- /container -->
</body>
</html>
