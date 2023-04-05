function clickProposalFormModify(event) {
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
    event.target.type = 'submit';
    event.target.removeAttribute('onclick');
    event.preventDefault();
    console.log(event);
}

function getProposalForm() {
    $.ajax({
        type: 'get',    
        url: '/api/userStatus/modifyProposalForm/1',
        
        success: (data) => {
            console.log(data);
        },
        error: (error) => {
            console.log(error);
        }
    });
}
