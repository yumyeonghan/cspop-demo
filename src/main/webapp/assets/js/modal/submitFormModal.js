function clickSubmitFormModify(event, id) {
    let inputsInSubmitForm = $('#submitFormModal input');
    for (let i = 0; i < inputsInSubmitForm.length; i++) {
        if(inputsInSubmitForm[i].attributes.hasOwnProperty('disabled')) {
            inputsInSubmitForm[i].disabled = false;
            console.log('찾음');
        }
        if(inputsInSubmitForm[i].attributes.hasOwnProperty('readonly')) {
            $(inputsInSubmitForm[i]).attr('readonly',false);
            console.log('되나');
        }
    }
    console.log(inputsInSubmitForm);
    event.target.innerHTML = '제출';
    event.target.setAttribute('onclick',`submitFormModalSubmit(this, ${id})`);
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

    if(confirm('제출 하시겠습니까?')){
        // Get form
        var form = $('#submitFormModal')[0];
        // Create an FormData object
        var data = new FormData(form);
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
                getSubmitForm();
                target.innerHTML='수정'
                target.setAttribute('onclick',`clickSubmitFormModify(event, ${id})`);
                // location.reload(); // 새로고침
            },
            error: (error) => {
                console.log("hi",error);
            }
        });
        return true;
    }

    return false;
}