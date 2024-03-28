// === messageRedirect.html ===

window.onload = function () {
    const message = "{{params.message}}";
    if (message) {
        alert(message);
    }

    const form = document.getElementById('redirectForm');
    if (form) {
        form.submit();
        return false;
    }

    const redirectUri = "{{params.redirectUri}}";
    window.location.href = redirectUri;
};