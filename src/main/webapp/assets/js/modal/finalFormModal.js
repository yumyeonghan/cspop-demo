function clickFinalFormModify(event, id) {
    let inputsInFinalForm = $('#finalFormModal input');
    for (let i = 0; i < inputsInFinalForm.length; i++) {
        if(inputsInFinalForm[i].attributes.hasOwnProperty('disabled')) {
            inputsInFinalForm[i].disabled = false;
        }
        if(inputsInFinalForm[i].attributes.hasOwnProperty('readonly')) {
            $(inputsInFinalForm[i]).attr('readonly',false);
        }
    }

    // 파일 다운로드 태그 숨기기
    $('#finalFormDownloadFile').hide();
    // 파일 업로드 input 보여주기
    $('input[name="finalFormUploadFile"]').show();
    event.target.innerHTML = '제출';
    event.target.setAttribute('onclick',`finalFormModalSubmit(this, ${id})`);
}

function getFinalForm(id) {
    $.ajax({
        type: 'get',    
        url: '/api/userStatus/modifyFinalForm?finalFormId='+id,
        
        success: (data) => {
            console.log(data);
            $('#finalFormModify .modal-body').empty();
            $('#finalFormModify .modal-body').append(data);
            // 파일 업로드 input 숨기기
            $('input[name="finalFormUploadFile"]').hide();
        },
        error: (error) => {
            console.log(error);
        }
    });
}

function finalFormModalSubmit(target, id) {
    
    if(confirm('제출 하시겠습니까?')){
        // Get form         
        var form = $('#finalFormModal')[0];  	    
        // Create an FormData object          
        var data = new FormData(form);
        $.ajax({
            type: 'post',
            enctype: 'multipart/form-data',
            url: '/api/userStatus/modifyFinalForm?finalFormId='+id,
            data: data,
            processData: false,    
            contentType: false,
            cache:false,
            success: (data) => {
                console.log(data);
                getFinalForm(id);
                target.innerHTML='수정'
                target.setAttribute('onclick',`clickFinalFormModify(event, ${id})`);
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