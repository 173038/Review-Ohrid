$(document).ready(function () {
    $("#submit").click(function () {
        let user = {
            email: $("#email").val(),
            password: $("#password").val(),
        };
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/auth/login-successful",
            data: {email: user.email, password: user.password},
            //contentType: "application/JSON",
            success: function (data) {
                let obj = JSON.stringify(data);
                window.sessionStorage.setItem("loggedUser", JSON.parse(obj).email);
                window.sessionStorage.setItem("loggedUserId", JSON.parse(obj).id);
                window.location = "http://localhost:8080/home";
            },
            error: function (xhr, status, error) {
                let errorMessage = xhr.responseJSON.message;
                $("#errorMessage").html(errorMessage);
            }
        });
    });
});