function clickInterimFormModify(event, id) {
    let inputsInInterimForm = $('#interimFormModal input');
    for (let i = 0; i < inputsInInterimForm.length; i++) {
        if(inputsInInterimForm[i].attributes.hasOwnProperty('disabled')) {
            inputsInInterimForm[i].disabled = false;
        }
        if(inputsInInterimForm[i].attributes.hasOwnProperty('readonly')) {
            $(inputsInInterimForm[i]).attr('readonly',false);
        }
    }
    
     // 파일 다운로드 태그 숨기기
     $('#interimFormDownloadFile').hide();
     // 파일 업로드 input 보여주기
     $('input[name="interimFormUploadFile"]').show();
    event.target.innerHTML = '제출';
    event.target.setAttribute('onclick',`interimFormModalSubmit(this, ${id})`);
    console.log(event);
}

function getInterimForm(id) {
    $.ajax({
        type: 'get',
        url: '/api/userStatus/modifyInterimForm?interimFormId='+id,

        success: (data) => {
            console.log(data);
            $('#interimFormModify .modal-body').empty();
            $('#interimFormModify .modal-body').append(data);
            // 파일 업로드 input 숨기기
            $('input[name="interimFormUploadFile"]').hide();

        },
        error: (error) => {
            console.log(error);
        }
    });
}

function interimFormModalSubmit(target, id) {

    if(confirm('제출 하시겠습니까?')){
        // Get form
        var form = $('#interimFormModal')[0];
        // Create an FormData object
        var data = new FormData(form);
        $.ajax({
            type: 'post',
            enctype: 'multipart/form-data',
            url: '/api/userStatus/modifyInterimForm?interimFormId='+id,
            data: data,
            processData: false,
            contentType: false,
            cache:false,
            success: (data) => {
                console.log(data);
                getInterimForm(id);
                target.innerHTML='수정'
                target.setAttribute('onclick',`clickInterimFormModify(event, ${id})`);
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