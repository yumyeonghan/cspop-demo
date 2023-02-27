/*
* 각 페이지의 login 여부 Check
* */
$(function userCheck() {
    let userCheckButton = $(`#userCheck`)
    let button = userId === "NotLogin"
        ? `<a href="/api/login"><button class="btn btn-primary btn-sm float-right">Login</button></a>`
        : `<button class="btn btn-primary btn-sm float-right" onclick="logout()">Logout</button>`;
    userCheckButton.append(button);
})