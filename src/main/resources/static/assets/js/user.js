// === main.html ===

const searchForm = document.getElementById('searchForm');
const searchInput = document.getElementById('searchInput');
const searchResultsDiv = document.getElementById('searchResults');

searchInput.addEventListener('input', function (event) {
    const query = searchInput.value.trim(); // 검색어 가져오기

    if (query === '') {
        // 검색어가 비어 있는 경우 결과를 지우고 함수 종료
        clearSearchResults();
        return;
    }

    // AJAX 요청 생성
    const xhr = new XMLHttpRequest();
    xhr.open('GET', '/user/search?query=' + query, true);

    // 요청 완료 시 동작 정의
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 400) {
            // 성공적으로 응답이 도착한 경우
            const data = JSON.parse(xhr.responseText);
            displaySearchResults(data);
        } else {
            // 에러가 발생한 경우
            console.error('요청 실패');
        }
    };

    // 요청 실패 시 동작 정의
    xhr.onerror = function () {
        console.error('네트워크 오류');
    };

    // 요청 전송
    xhr.send();
});

// 검색 결과를 표시하는 함수
function displaySearchResults(results) {
    searchResultsDiv.innerHTML = ''; // 결과를 표시하기 전에 이전 결과 삭제

    if (results.length === 0) {
        searchResultsDiv.innerHTML = '검색 결과가 없습니다.';
    } else {
        const ul = document.createElement('ul');
        results.forEach(function (result) {
            const li = document.createElement('li');
            li.textContent = result;
            li.addEventListener('click', function () {
                // 클릭한 검색어에 대한 페이지로 이동
                window.location.href = '/user/page?id=' + encodeURIComponent(result);
            });
            ul.appendChild(li);
        });
        searchResultsDiv.appendChild(ul);
    }
}


// 검색 결과를 지우는 함수
function clearSearchResults() {
    searchResultsDiv.innerHTML = '';
}

// === mypage.html ===

function showEditArea(editAreaId) {
    document.getElementById(editAreaId).style.display = "block";
    // 수정, 삭제 버튼 숨김
    document.querySelector("#" + editAreaId + " .question-actions").style.display = "none";
    // 질문 또는 답변 내용 숨김
    document.querySelector("#" + editAreaId + " .question-content").style.display = "none";
}

function cancelEdit(editAreaId) {
    // 수정 영역 숨김
    document.getElementById(editAreaId).style.display = "none";
    // 수정, 삭제 버튼 표시
    document.querySelector("#" + editAreaId + " .question-actions").style.display = "block";
    // 질문 또는 답변 내용 표시
    document.querySelector("#" + editAreaId + " .question-content").style.display = "block";
}

function saveQuestion(questionId) {
    const updatedQuestion = document.getElementById("editedContent_question_" + questionId).value;
    $.ajax({
        url: "/user/updateq?questionId=" + questionId,
        type: "POST",
        data: { updatedQuestion: updatedQuestion },
        success: function (data) {
            alert("수정되었습니다.");
            window.location.href = window.location.href;
        },
        error: function (xhr, status, error) {
            // 오류 처리
            alert("수정에 실패했습니다.");
        }
    });
    // 수정된 내용을 서버로 전송하는 로직을 작성합니다.
    // 여기에 코드를 추가하세요.
}

function deleteQuestion(questionId) {
    const confirmation = confirm("질문을 삭제하시겠습니까?");
    if (confirmation) {
        // 확인을 클릭한 경우
        $.ajax({
            url: "/user/deleteq?questionId=" + questionId,
            type: "POST",
            success: function (data) {
                alert("삭제되었습니다.");
                window.location.href = window.location.href;
            },
            error: function (xhr, status, error) {
                // 오류 처리
                alert("삭제에 실패했습니다.");
            }
        });
    }
}

function saveAnswer(answerId) {
    // 수정된 내용을 서버로 전송하는 로직을 작성합니다.
    // 여기에 코드를 추가하세요.
    const updatedAnswer = document.getElementById("editedContent_answer_" + answerId).value;
    $.ajax({
        url: "/user/updatea?answerId=" + answerId,
        type: "POST",
        data: { updatedAnswer: updatedAnswer },
        success: function (data) {
            alert("수정되었습니다.");
            window.location.href = window.location.href;
        },
        error: function (xhr, status, error) {
            // 오류 처리
            alert("수정에 실패했습니다.");
        }
    });
}

function deleteAnswer(answerId, questionId) {
    const confirmation = confirm("답변을 삭제하시겠습니까?");
    if (confirmation) {
        // 확인을 클릭한 경우
        $.ajax({
            url: "/user/deletea?answerId=" + answerId + "&questionId=" + questionId,
            type: "POST",
            success: function (data) {
                alert("삭제되었습니다.");
                window.location.href = window.location.href;
            },
            error: function (xhr, status, error) {
                // 오류 처리
                alert("삭제에 실패했습니다.");
            }
        });
    }
};

// === pagesetting.html ===


function checkPasswordMatch() {
    const currentPassword = document.getElementById("currentPassword").value;
    const newPassword = document.getElementById("newPassword").value;
    const confirmedPassword = document.getElementById("confirmedPassword").value;
    const passwordMatchMessage = document.getElementById("passwordMatchMessage");


    // 새 비밀번호와 확인 비밀번호가 같은지 확인하고 메시지 표시
    if (currentPassword === newPassword && currentPassword !== "") {
        passwordMatchMessage.innerHTML = "현재 비밀번호와 신규 비밀번호는 같을 수 없습니다.";
        passwordMatchMessage.style.color = "red";
        submitButton.disabled = true; // 현재 비밀번호와 새 비밀번호가 같으면 버튼 비활성화
        return;
    }

    // 새 비밀번호와 확인 비밀번호가 같은지 확인하고 메시지 표시
    if (newPassword === confirmedPassword) {
        // 비밀번호가 조건을 충족하는지 확인
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

