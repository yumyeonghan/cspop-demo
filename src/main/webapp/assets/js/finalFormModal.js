function clickFinalFormModify(event) {
    let inputsInFinalForm = $('#finalFormModal input');
    for (let i = 0; i < inputsInFinalForm.length; i++) {
        if(inputsInFinalForm[i].attributes.hasOwnProperty('disabled')) {
            inputsInFinalForm[i].disabled = false;
            console.log('찾음');
        }
        if(inputsInFinalForm[i].attributes.hasOwnProperty('readonly')) {
            $(inputsInFinalForm[i]).attr('readonly',false);
            console.log('되나');
        }
    }
    console.log(inputsInFinalForm);
    event.target.innerHTML = '제출';
    // event.target.type = 'submit';
    // event.target.removeAttribute('onclick');
    event.target.setAttribute('onclick','finalFormModalSubmit(this)');
    event.preventDefault();
    console.log(event);
}

function getFinalForm(id) {
    $.ajax({
        type: 'get',    
        url: '/api/userStatus/modifyFinalForm?finalFormId='+id,
        
        success: (data) => {
            console.log(data);
            $('#finalFormModify .modal-body').empty();
            $('#finalFormModify .modal-body').append(data);
            
        },
        error: (error) => {
            console.log(error);
        }
    });
}

function finalFormModalSubmit(target) {
    
    if(confirm('제출 하시겠습니까?')){
        // Get form         
        var form = $('#finalFormModal')[0];  	    
        // Create an FormData object          
        var data = new FormData(form);
        $.ajax({
            type: 'post',
            enctype: 'multipart/form-data',
            url: '/api/userStatus/modifyFinalForm?finalFormId=1',
            data: data,
            processData: false,    
            contentType: false,
            cache:false,
            success: (data) => {
                console.log(data);
                getFinalForm();
                target.innerHTML='수정'
                target.setAttribute('onclick','clickFinalFormModify(event)');
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