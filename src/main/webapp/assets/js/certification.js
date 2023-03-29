function uploadExcel(event) {
    event.preventDefault();
    // Get form         
    var form = $('#certificationForm')[0];  	    
    // Create an FormData object          
    var data = new FormData(form);
    $.ajax({
        type: 'post',
        enctype: 'multipart/form-data',
        url: '/api/certification/certification_management.read',
        data: data,
        processData: false,    
        contentType: false,
        dataTypes: "json",
        cache:false,
        success: (data) => {
            // console.log(data);
            alert('업로드 성공');
            location.reload(); // 새로고침
        },
        error: (error) => {
            console.log("hi",error);

            if(error.status === 500) {
                alert("형식은 같으나 내용이 일치하지 않는 파일입니다.");
            }
            else{
                alert(error.responseJson.errorMessage);//
            }
        }
    });
}
