function clickOtherFormModify(event, id) {
    let inputsInOtherForm = $('#otherFormModal input');
    for (let i = 0; i < inputsInOtherForm.length; i++) {
        if(inputsInOtherForm[i].attributes.hasOwnProperty('disabled')) {
            inputsInOtherForm[i].disabled = false;
        }
        if(inputsInOtherForm[i].attributes.hasOwnProperty('readonly')) {
            $(inputsInOtherForm[i]).attr('readonly',false);
        }
    }

    // 파일 다운로드 태그 숨기기
    $('#otherFormDownloadFile').hide();
    // 파일 업로드 input 보여주기
    $('input[name="otherFormUploadFile"]').show();
    event.target.innerHTML = '제출';
    event.target.setAttribute('onclick',`otherFormModalSubmit(this, ${id})`);
    console.log(event);
}

function getOtherForm(id) {
    $.ajax({
        type: 'get',
        url: '/api/userStatus/modifyOtherForm?otherFormId='+id,

        success: (data) => {
            console.log(data);
            $('#otherFormModify .modal-body').empty();
            $('#otherFormModify .modal-body').append(data);
            // 파일 업로드 input 숨기기
            $('input[name="otherFormUploadFile"]').hide();
        },
        error: (error) => {
            console.log(error);
        }
    });
}

function otherFormModalSubmit(target, id) {

    if(confirm('제출 하시겠습니까?')){
        // Get form
        var form = $('#otherFormModal')[0];
        // Create an FormData object
        var data = new FormData(form);
        $.ajax({
            type: 'post',
            enctype: 'multipart/form-data',
            url: '/api/userStatus/modifyOtherForm?otherFormId='+id,
            data: data,
            processData: false,
            contentType: false,
            cache:false,
            success: (data) => {
                console.log(data);
                getOtherForm(id);
                target.innerHTML='수정'
                target.setAttribute('onclick',`clickOtherFormModify(event, ${id})`);
                // location.reload(); // 새로고침
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