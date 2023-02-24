function login() {
    let account = {
        studentId: $(`#id`).val(),
        studentPassword: $(`#password`).val()
    }
    $.ajax({
        url: "/api/login/auth",
        type: "post",
        data: JSON.stringify(account),
        contentType: "application/json; charset=utf-8",
        dataTypes: "json",
        success: function (url) {
            alert("로그인 성공")
            window.location.replace(url);
        }, error: function (error) {
            alert(error.responseJSON.errorMessage);
        }
    })
}

$(function userCheck(){
    let userCheckButton = $(`#userCheck`)
    let button="";
    if (userId === "NotLogin"){
        button += `<a href="/api/login"><button class="btn btn-primary btn-sm float-right">Login</button></a>`
    }
    else{
        button = `<button class="btn btn-primary btn-sm float-right" onclick="logout()">Logout</button>`
    }
    userCheckButton.append(button);
})

function logout(){
    alert("logout!")
}

