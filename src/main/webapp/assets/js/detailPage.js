$('table').on('click', 'tr', function(event) {
    let id = $(event.target).closest('tr').data('id');

        $.ajax({
            url: "/notice/view/detail/" + id,
            type: "get",
            contentType: "application/json; charset=utf-8",
            dataTypes: "json",
            success: (res) => {
                sessionStorage.setItem('res', res);
                window.location.href ="/api/graduation/detail"
            }, error: (res) => {
                alert(res.responseJSON.errorMessage);
            }
        })
});








