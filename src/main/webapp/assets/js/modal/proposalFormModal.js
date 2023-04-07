function clickProposalFormModify(event, id) {
    let inputsInProposalForm = $('#proposalFormModal input');
    for (let i = 0; i < inputsInProposalForm.length; i++) {
        if(inputsInProposalForm[i].attributes.hasOwnProperty('disabled')) {
            inputsInProposalForm[i].disabled = false;
            console.log('찾음');
        }
        if(inputsInProposalForm[i].attributes.hasOwnProperty('readonly')) {
            $(inputsInProposalForm[i]).attr('readonly',false);
            console.log('되나');
        }
    }
    console.log(inputsInProposalForm);
    event.target.innerHTML = '제출';
    event.target.setAttribute('onclick',`proposalFormModalSubmit(this, ${id})`);
    event.preventDefault();
    console.log(event);
}

function getProposalForm(id) {
    $.ajax({
        type: 'get',
        url: '/api/userStatus/modifyProposalForm?proposalFormId='+id,

        success: (data) => {
            console.log(data);
            $('#proposalFormModify .modal-body').empty();
            $('#proposalFormModify .modal-body').append(data);

        },
        error: (error) => {
            console.log(error);
        }
    });
}

function proposalFormModalSubmit(target, id) {

    if(confirm('제출 하시겠습니까?')){
        // Get form
        var form = $('#proposalFormModal')[0];
        // Create an FormData object
        var data = new FormData(form);
        $.ajax({
            type: 'post',
            enctype: 'multipart/form-data',
            url: '/api/userStatus/modifyProposalForm?proposalFormId='+id,
            data: data,
            processData: false,
            contentType: false,
            cache:false,
            success: (data) => {
                console.log(data);
                getProposalForm();
                target.innerHTML='수정'
                target.setAttribute('onclick',`clickProposalFormModify(event, ${id})`);
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