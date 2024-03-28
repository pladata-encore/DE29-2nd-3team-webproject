// === regist.html ===

$(document).ready(function () {
    $("#userId").keyup(function () {
        var userId = $(this).val();
        if (userId.length < 8) {
            $("#duplicateUserIdError").show();
            return;
        }
        $.ajax({
            url: "/check-userid",
            type: "GET",
            data: { userId: userId },
            success: function (data) {
                if (data.isDuplicate) {
                    $("#duplicateUserIdError").show();
                    submitButton.disabled = true;
                } else {
                    $("#duplicateUserIdError").hide();
                }
            }
        });
    });
});

function checkPasswordMatch() {
    const newPassword = document.getElementById("password").value;
    const confirmedPassword = document.getElementById("confirmedPassword").value;
    const passwordMatchMessage = document.getElementById("passwordMatchMessage");

    // 새 비밀번호와 확인 비밀번호가 같은지 확인하고 메시지 표시
    if (newPassword === confirmedPassword && newPassword !== "") {
        const regex = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,16}$/;
        if (regex.test(newPassword)) {
            passwordMatchMessage.innerHTML = "사용 가능한 비밀번호 입니다.";
            passwordMatchMessage.style.color = "green";
            submitButton.disabled = false; // 비밀번호가 일치하고 조건을 충족하면 버튼 활성화
        } else {
            passwordMatchMessage.innerHTML = "비밀번호는 숫자, 문자, 특수문자를 포함한 8~16자여야 합니다.";
            passwordMatchMessage.style.color = "red";
            submitButton.disabled = true; // 비밀번호가 조건을 충족하지 않으면 버튼 비활성화
        }
    } else {
        passwordMatchMessage.innerHTML = "비밀번호가 일치하지 않습니다.";
        passwordMatchMessage.style.color = "red";
        submitButton.disabled = true; // 비밀번호가 일치하지 않으면 버튼 비활성화
    }
}


