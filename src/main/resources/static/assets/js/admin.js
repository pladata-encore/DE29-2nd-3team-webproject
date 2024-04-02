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
    xhr.open('GET', '/admin/search?query=' + query, true);

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
                window.location.href = '/admin/page?id=' + encodeURIComponent(result);
            });
            ul.appendChild(li);
        });
        searchResultsDiv.appendChild(ul);
    }
}

function deleteQuestion(questionId) {
    const confirmation = confirm("질문을 삭제하시겠습니까?");
    if (confirmation) {
        // 확인을 클릭한 경우
        $.ajax({
            url: "/admin/deleteq?questionId=" + questionId,
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

function deleteAnswer(answerId, questionId) {
    const confirmation = confirm("답변을 삭제하시겠습니까?");
    if (confirmation) {
        // 확인을 클릭한 경우
        $.ajax({
            url: "/admin/deletea?answerId=" + answerId + "&questionId=" + questionId,
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

// 검색 결과를 지우는 함수
function clearSearchResults() {
    searchResultsDiv.innerHTML = '';
}

