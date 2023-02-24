function login(){
    let account = {
        studentId : $(`#id`).val(),
        studentPassword: $(`#password`).val()
    }
    $.ajax({
        url: "/api/login/auth",
        type: "post",
        data: JSON.stringify(account),
        contentType: "application/json; charset=utf-8",
        dataTypes: "json",
        success:function (url){
            alert("로그인 성공")
            window.location.replace(url);
        },error:function (error){
            alert(error.responseJSON.errorMessage);
        }
    })
}