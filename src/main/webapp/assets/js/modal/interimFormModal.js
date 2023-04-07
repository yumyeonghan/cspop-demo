function clickInterimFormModify(event, id) {
    let inputsInInterimForm = $('#interimFormModal input');
    for (let i = 0; i < inputsInInterimForm.length; i++) {
        if(inputsInInterimForm[i].attributes.hasOwnProperty('disabled')) {
            inputsInInterimForm[i].disabled = false;
            console.log('찾음');
        }
        if(inputsInInterimForm[i].attributes.hasOwnProperty('readonly')) {
            $(inputsInInterimForm[i]).attr('readonly',false);
            console.log('되나');
        }
    }
    console.log(inputsInInterimForm);
    event.target.innerHTML = '제출';
    event.target.setAttribute('onclick',`interimFormModalSubmit(this, ${id})`);
    event.preventDefault();
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
                console.log("hi",error);
            }
        });
        return true;
    }

    return false;
}