window.onload = populate;

function populate() {
    var id = localStorage.getItem('UserID')
//    if (id == null  || id < 1){
//        window.location.href = "index.html";
//    }

    const Http = new XMLHttpRequest();
    const url='http://localhost:8080/users/' + id;
    Http.open("GET", url);
    Http.send();

    Http.onreadystatechange = function() {
        if(Http.readyState == 4) {
            if (Http.status != 200) {
                alert("ERROR: User not found");
            } else {
                var response = JSON.parse(Http.response);
                var element = document.getElementById("user_tag");
                element.innerHTML = "User - " + response.name;

                if (response.admin) {
                    element.innerHTML = "User - " + response.name + " (Admin)";
                }
            }
        }
    }


}

function logout() {
    localStorage.setItem("UserID", 0);
    window.location.href = "index.html";
}