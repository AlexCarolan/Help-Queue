window.onload = populate;

function populate() {
    var id = localStorage.getItem('UserID')
    if (id == null  || id < 1){
        window.location.href = "index.html";
    }
}

function logout() {
    localStorage.setItem("UserID", 0);
    window.location.href = "index.html";
}