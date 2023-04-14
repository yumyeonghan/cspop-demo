function clickSubmitFormModify(event, id) {
    let buttonInSubmitForm = $('#submitFormModal .modal-body button');
    for (let i = 0; i < buttonInSubmitForm.length; i++) {
        if(buttonInSubmitForm[i].attributes.hasOwnProperty('disabled')) {
            buttonInSubmitForm[i].disabled = false;
        }
        if(buttonInSubmitForm[i].attributes.hasOwnProperty('readonly')) {
            $(buttonInSubmitForm[i]).attr('readonly',false);
        }
    }
    console.log(buttonInSubmitForm);
    buttonInSubmitForm.click(function() {
        console.log(this,'this');
        submitFormModalSubmit(this, id);
    })
    event.preventDefault();
    console.log(event);
}

function getSubmitForm(id) {
    $.ajax({
        type: 'get',
        url: '/api/userStatus/modifySubmitForm?submitFormId='+id,

        success: (data) => {
            console.log(data);
            $('#submitFormModify .modal-body').empty();
            $('#submitFormModify .modal-body').append(data);

        },
        error: (error) => {
            console.log(error);
        }
    });
}

function submitFormModalSubmit(target, id) {

    if(confirm(`${target.innerHTML}으로 수정 하시겠습니까?`)){
        var data = new FormData();
        data.append('qualification',target.innerHTML == '기타자격' ? '기타자격' : '논문');
        console.log(target.innerHTML);
        $.ajax({
            type: 'post',
            enctype: 'multipart/form-data',
            url: '/api/userStatus/modifySubmitForm?submitFormId='+id,
            data: data,
            processData: false,
            contentType: false,
            cache:false,
            success: (data) => {
                console.log(data);
                getSubmitForm(id);
                alert(`${target.innerHTML}으로 수정 성공!`)
                location.reload(); // 새로고침
            },
            error: (error) => {
                alert(error.responseJSON.errorMessage);
                console.log("hi",error);
            }
        });
        return true;
    }

    return false;
}