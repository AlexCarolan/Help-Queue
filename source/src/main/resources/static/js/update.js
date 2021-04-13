window.onload = populate;

function populate() {
    var id = localStorage.getItem('UserID');
    var idTicket = localStorage.getItem('TicketID');
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

    populateForm(idTicket);

}

function populateForm(id) {

    const Http = new XMLHttpRequest();
    const url='http://localhost:8080/tickets/' + id;
    Http.open("GET", url);
    Http.send();


    Http.onreadystatechange = function() {
        if(Http.readyState == 4) {
            if (Http.status == 200) {
                var response = JSON.parse(Http.response);
                document.getElementById('title').value = response.title;
                document.getElementById('description').value = response.description;
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

function updateTicket() {

    id = localStorage.getItem('TicketID');

    const Http = new XMLHttpRequest();
    const url='http://localhost:8080/tickets/' + id;
    Http.open("GET", url);
    Http.send();

    var element = document.getElementById("ticket-queue");

    Http.onreadystatechange = function() {
        if(Http.readyState == 4) {
            if (Http.status == 200) {
                 sendUpdateRequest(id, JSON.parse(Http.response));
            } else {
                alert("ERROR: Issue closing ticket");
            }
        }
    }
}

function sendUpdateRequest(id, oldTicket) {

    var creator = {};
    creator['id'] = oldTicket.creator.id;

    var body = {};
    body['title'] = document.getElementById('title').value;
    body['creator'] = creator;
    body['description'] = document.getElementById('description').value;
    body['created'] = oldTicket.created;
    body['status'] = oldTicket.status;

    const Http = new XMLHttpRequest();
    const url='http://localhost:8080/tickets/' + id;
    Http.open("PUT", url, true);
    Http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    Http.send(JSON.stringify(body));

    Http.onreadystatechange = function() {
        if(Http.readyState == 4) {
            if (Http.status == 200) {
                returnQueue();
            } else {
                alert("ERROR: Unable to update ticket");
            }
        }
    }
}

