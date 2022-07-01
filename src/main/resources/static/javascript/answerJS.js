$(document).ready(function () {
    let userEmail=$("#userEmail").text();
    let userId=$("#userId").text();
    window.sessionStorage.setItem("loggedUser", userEmail);
    window.sessionStorage.setItem("loggedUserId", userId);

    $("#logout").click(function () {
        sessionStorage.removeItem("loggedUser");
        sessionStorage.removeItem("loggedUserId");
    })

    $(".CreatedDate").each(function () {
        let createdDate = $(this).text();
        let createdDateSplit = createdDate.split("T");
        $(this).text(createdDateSplit[0]);
    })

    $("#submitAnswer").click(function () {
        let answer = {
            answer: $("#answer").val(), email: sessionStorage.getItem("loggedUser"), id: $("#questionId").text()
        };
        let userId = sessionStorage.getItem("loggedUserId");
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/answers/answer-successful",
            data: JSON.stringify(answer),
            contentType: "application/JSON",
            success: function (data) {
                // alert("vo JS success")
                // function sleep(milliseconds) {
                //     return new Promise(resolve => setTimeout(resolve, milliseconds));
                // }

                // let message = "You have asked a question. Now wait someone to answer it :)";
                // $("#SuccessfullyAnsweredQuestion").html(message);

                document.getElementById('id01').style.display = 'none';
                $("#answer").val("");

                document.location.reload()

                // async function redirect() {
                //     await sleep(2000);
                //     // window.location = "http://localhost:8080/home";
                // }
                //
                // redirect();

                // function sleep(milliseconds) {
                //     return new Promise(resolve => setTimeout(resolve, milliseconds));
                // }
                //
                //
                //
                // let message = "You have answered the question. Thanks :)";
                // $("#SuccessfullyAnsweredQuestion").html(message);
                // document.getElementById('id01').style.display = 'none';
                // $("#answer").val("");
                // let id = $("#questionId").text();
                // // window.location = "http://localhost:8080/answers/" + userId + "/view-answer/" + id;
                // async function redirect() {
                //     await sleep(2000);
                //     window.location = "http://localhost:8080/answers/" + userId + "/view-answer/" + id;
                // }
                //
                // redirect();
            },
            error: function (xhr, status, error) {
                let errorMessage = xhr.responseJSON.message;
                $("#errorMessage").html(errorMessage);
            }
        });
    });

    $(".UpVoteButton").click(function () {
        let questionId = $("#questionId").text();
        let userAnswerStatus = {
            answerId: $(this).text(), userEmail: sessionStorage.getItem("loggedUser")
        }
        let userId = sessionStorage.getItem("loggedUserId");
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/answers/up-vote-answer",
            data: JSON.stringify(userAnswerStatus),
            contentType: "application/JSON",
            success: function (data) {
                window.location = "http://localhost:8080/answers/" + userId + "/view-answer/" + questionId;
            },
            error: function (xhr, status, error) {
            }
        })
    })

    $(".DownVoteButton").click(function () {
        let questionId = $("#questionId").text();
        let userAnswerStatus = {
            answerId: $(this).text(), userEmail: sessionStorage.getItem("loggedUser")
        }
        let userId = sessionStorage.getItem("loggedUserId");
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/answers/down-vote-answer",
            data: JSON.stringify(userAnswerStatus),
            contentType: "application/JSON",
            success: function (data) {
                window.location = "http://localhost:8080/answers/" + userId + "/view-answer/" + questionId;
            },
            error: function (xhr, status, error) {
            }
        })
    })

    $(".isDownVoted").parent().parent().find("button").attr("id", "isClicked");

    $(".isUpVoted").parent().parent().find("button").attr("id", "isClicked");
});



function deleteAnswer(answerId, userId, questionId) {
    let userAnswer = {
        userId: sessionStorage.getItem("loggedUserId"),
        answerId: answerId
    }
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/answers/delete-answer",
        data: JSON.stringify(userAnswer),
        contentType: "application/JSON",
        success: function (data) {
            document.location.reload()
        },
        error: function (data) {
            alert(data.responseJSON.message);
        }
    })
}
