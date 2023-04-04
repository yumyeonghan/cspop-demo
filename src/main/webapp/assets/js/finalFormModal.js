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
    event.target.type = 'submit';
    event.target.removeAttribute('onclick');
    event.preventDefault();
    console.log(event);
}

function getFinalForm() {
    $.ajax({
        type: 'get',    
        url: '/api/userStatus/modifyFinalForm/1',
        
        success: (data) => {
            console.log(data);
        },
        error: (error) => {
            console.log(error);
        }
    });
}