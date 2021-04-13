window.onload = populate;

function populate() {
    var id = localStorage.getItem('UserID');
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
                //Response received
                var response = JSON.parse(Http.response);
                var element = document.getElementById("user-tag");
                element.innerHTML = "User - " + response.name.charAt(0).toUpperCase() + response.name.slice(1);

                if (response.admin) {
                    element.innerHTML = "User - " + response.name.charAt(0).toUpperCase() + response.name.slice(1) + " (Admin)";
                }
            }
        }
    }
}

function logout() {
    localStorage.setItem("UserID", 0);
    window.location.href = "index.html";
}

function returnQueue() {
    window.location.href = "queue.html";
}

function createTicket() {

    var title = document.getElementById('title').value;
    title = title.charAt(0).toUpperCase() + title.slice(1);
    var description = document.getElementById('description').value;
    description = description.charAt(0).toUpperCase() + description.slice(1);

    if (title == "" || description == "") {
        alert("Title or description field is empty");
        return;
    }

    var creator = {};
    creator['id'] = localStorage.getItem('UserID');;

    var body = {};
    body['title'] = title;
    body['creator'] = creator;
    body['description'] = description;
    body['created'] = Date.now();
    body['status'] = "OPEN";

    const Http = new XMLHttpRequest();
    const url='http://localhost:8080/tickets/create';
    Http.open("POST", url, true);
    Http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    Http.send(JSON.stringify(body));

    Http.onreadystatechange = function() {
        if(Http.readyState == 4) {
            if (Http.status == 201) {
                returnQueue();
            } else {
                alert("ERROR: Unable to create ticket");
            }
        }
    }
}
