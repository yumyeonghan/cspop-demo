// 학과 선택 함수
$(function () {
    let major = $(`#major`);
    let majorText = `<option> 컴퓨터공학부 </option>`;
    major.append(majorText);
})

// 회원가입 함수
function signUp() {
    let userData = {
        studentId: $(`#id`).val(),
        studentPassword: $(`#password`).val(),
        studentName: $(`#name`).val(),
        sex: $(`#gender`).val(),
        birth: "1999-10-13",
        email: $(`#email`).val(),
        phoneNumber: $(`#phone`).val(),
        department: $(`#major`).val()
    }

    if (userData.password !== $(`#password2`).val()) {
        alert("비밀번호가 다릅니다.")
    } else {
        $.ajax({
            url: "api/user",
            type: "post",
            data: userData,
            success: function (){
                alert("signup test success");
            }
        })
    }
}

//아이디 중복 확인 함수
function checkId() {
    // api/user/duplicate-check
    let checkId = $(`#id`).val();
    $.ajax({
        url: "api/user/duplicate-check",
        type: "post",
        data: checkId,
        success: function (data) {
            data ? console.log("success") : console.log("fail");
        }
    })
}