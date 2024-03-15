// search.js

function search() {
    var searchQuery = document.getElementById('searchInput').value;
    fetch('/search?query=' + searchQuery)
      .then(response => response.json())
      .then(data => {
        // 서버로부터 받은 데이터를 처리하여 화면에 표시
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }
  