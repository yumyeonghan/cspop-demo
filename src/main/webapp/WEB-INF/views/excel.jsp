<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Excel_View</title>
</head>
<body>
<form action="excel.read" method="post" enctype="multipart/form-data">
    <fieldset>
        <legend>파일 업로드</legend>
        <h5>파일 찾기: <input type="file" name="file"></h5>
        <h5>파일 업로드: <input type="submit" value="제출" /></h5>
    </fieldset>
</form>


<form action="excelDown.do" method="post">
    <fieldset>
        <legend>파일 다운로드</legend>
        <input type="submit" id="excelDown" value="다운로드"/>
    </fieldset>
</form>
</body>
</html>