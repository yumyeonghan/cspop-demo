
// 학과 선택 함수
$(function () {
    let major = $(`#major`);
    let majorText = `<option> 학부생 </option>`;
    major.append(majorText);
})

// 회원가입 함수
function signUp() {
    let userData = {
        studentId: $(`#id`).val(),
        studentPassword: $(`#password`).val(),
        studentName: $(`#name`).val(),
        sex: "female",
        birth: "1999-10-13",
        email: $(`#email`).val(),
        phoneNumber: $(`#phone`).val(),
        classification: "학부생",
        department: "컴퓨터공학부"
    }
    $.ajax({
        url: "/api/user",
        type: "post",
        data: JSON.stringify(userData),
        contentType: "application/json; charset=utf-8",
        success: function (url){
            window.location.replace(url);
        }
    })
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