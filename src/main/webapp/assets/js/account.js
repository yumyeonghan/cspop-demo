// 중복확인 여부 체크
var isCheckedId = 0;
// id 입력란 변화 체크
var isChangedId = 0;

// 학과 선택 함수
$(() => {
    let major = $(`#major`);
    let majorText = `<option> 컴퓨터공학부 </option>`;
    majorText += `<option> 인공지능학부 </option>`;
    majorText += `<option> 안전보안전공 </option>`;
    major.append(majorText);
})
//id 입력란 변화 감지 함수
$(() => {
    const idInput = document.getElementById('id');
    idInput.addEventListener('input', () => {
        isChangedId = 1
    });
})

//비밀번호 1,2 확인 함수
function checkPw(pw1, pw2) {
    return pw1 !== pw2 ? 1 : 0;
}


// 회원가입 함수
function signUp() {
    let userData = {
        studentId: $(`#id`).val(),
        studentPassword: $(`#password`).val(),
        studentName: $(`#name`).val(),
        sex: $(`#gender`).val(),
        birth: "0000-00-00",
        email: $(`#email`).val(),
        phoneNumber: $(`#phone`).val(),
        classification: "학부생",
        department: $(`#major`).val()
    }
    // 패스워드 중복검사
    if (isCheckedId === 0 || isChangedId === 1) {
        alert("아이디 중복 확인을 해주세요");
    } else {
        if (checkPw(userData.studentPassword, $(`#password2`).val())) {
            alert("입력한 패스워드가 다릅니다.")
        } else {
            $.ajax({
                url: "/api/user",
                type: "post",
                data: JSON.stringify(userData),
                contentType: "application/json; charset=utf-8",
                success: function (url) {
                    window.location.replace(url);
                }
            })
        }
    }
}

//아이디 중복 확인 함수
function checkId() {
    // api/user/duplicate-check
    let userId = {
        studentId: $(`#id`).val()
    }
    $.ajax({
        url: "/api/user/duplicate-check",
        type: "post",
        data: JSON.stringify(userId),
        contentType: "application/json; charset=utf-8",
        dataTypes: "json",
        success: function () {
            isCheckedId = 1;
            isChangedId = 0;
            alert("사용 가능한 아이디 입니다");
        }, error: function (data) {
            isCheckedId = 0;
            let inputId = document.getElementById('id')
            inputId.value = null;
            alert(data.responseJSON.errorMessage);
        }
    })
}