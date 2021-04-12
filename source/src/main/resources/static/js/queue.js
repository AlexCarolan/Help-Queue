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
                //Response received
                var response = JSON.parse(Http.response);
                var element = document.getElementById("user-tag");
                element.innerHTML = "User - " + response.name;

                if (response.admin) {
                    element.innerHTML = "User - " + response.name + " (Admin)";
                }

                addTickets(response);
            }
        }
    }

}

function addTickets(user) {
    const Http = new XMLHttpRequest();
    const url='http://localhost:8080/tickets/all';
    Http.open("GET", url);
    Http.send();

    var element = document.getElementById("ticket-queue");

    Http.onreadystatechange = function() {
        if(Http.readyState == 4) {
            if (Http.status == 200) {
                var response = JSON.parse(Http.response);
                for(var i = 0; i < response.length; i++) {
                    var ticket = response[i];
                    element.appendChild(ticketBuilder(ticket, user));
                }
            }
        }
    }
}

function ticketBuilder(ticket, user) {
    var ticketHolder = document.createElement("div");
    ticketHolder.classList.add("ticket-holder");

    var ticketTitle = document.createElement("h4");
    ticketTitle.classList.add("ticket-offset");
    ticketTitle.appendChild(document.createTextNode(ticket.title));
    ticketHolder.appendChild(ticketTitle);

    var ticketTitle = document.createElement("p");
    ticketTitle.classList.add("ticket-subtext");
    ticketTitle.appendChild(document.createTextNode(ticket.description));
    ticketHolder.appendChild(ticketTitle);

    var ticketTitleCreator = document.createElement("h4");
    ticketTitleCreator.classList.add("ticket-offset");
    ticketTitleCreator.appendChild(document.createTextNode("Creator"));
    ticketHolder.appendChild(ticketTitleCreator);

    var ticketCreator= document.createElement("p");
    ticketCreator.classList.add("ticket-subtext");
    ticketCreator.appendChild(document.createTextNode(ticket.creator.name));
    ticketHolder.appendChild(ticketCreator);

    var ticketOpenedTitle = document.createElement("h4");
    ticketOpenedTitle.classList.add("ticket-offset");
    ticketOpenedTitle.appendChild(document.createTextNode("Date Opened"));
    ticketHolder.appendChild(ticketOpenedTitle);

    var ticketOpened = document.createElement("p");
    ticketOpened.classList.add("ticket-subtext");
    ticketOpened.appendChild(document.createTextNode(iso8601ToJsDate(ticket.created)));
    ticketHolder.appendChild(ticketOpened);

    var ticketStatusTitle = document.createElement("h4");
    ticketStatusTitle.classList.add("ticket-offset");
    ticketStatusTitle.appendChild(document.createTextNode("Status"));
    ticketHolder.appendChild(ticketStatusTitle);

    if (ticket.status == "OPEN") {
        var ticketStatus = document.createElement("p");
        ticketStatus.classList.add("tag");
        ticketStatus.classList.add("ticket-open");
        ticketStatus.appendChild(document.createTextNode("Open"));
        ticketHolder.appendChild(ticketStatus);

        if (user.admin || user.id == ticket.creator.id) {
            var ticketUpdate = document.createElement("button");
            ticketUpdate.classList.add("btn");
            ticketUpdate.classList.add("btn-primary");
            ticketUpdate.classList.add("ticket-button");
            ticketUpdate.appendChild(document.createTextNode("Update"));
            ticketHolder.appendChild(ticketUpdate);

            var ticketClose = document.createElement("button");
            ticketClose.classList.add("btn");
            ticketClose.classList.add("btn-warning");
            ticketClose.classList.add("ticket-button");
            ticketClose.appendChild(document.createTextNode("Close"));
            ticketClose.addEventListener("click", function(){closeTicket(ticket.id)});
            ticketHolder.appendChild(ticketClose);
        }

    } else if (ticket.status == "CLOSED") {
        var ticketStatus = document.createElement("p");
        ticketStatus.classList.add("tag");
        ticketStatus.classList.add("ticket-closed");
        ticketStatus.appendChild(document.createTextNode("Closed"));
        ticketHolder.appendChild(ticketStatus);

        if (user.admin || user.id == ticket.creator.id) {
            var ticketDelete = document.createElement("button");
            ticketDelete.classList.add("btn");
            ticketDelete.classList.add("btn-danger");
            ticketDelete.classList.add("ticket-button");
            ticketDelete.appendChild(document.createTextNode("Delete"));
            ticketDelete.addEventListener("click", function(){deleteTicket(ticket.id)});
            ticketHolder.appendChild(ticketDelete);
        }
    }


    return ticketHolder;
}

function logout() {
    localStorage.setItem("UserID", 0);
    window.location.href = "index.html";
}

function iso8601ToJsDate(iso8601){
    var ms = Date.parse(iso8601)
    var dateTime = new Date(ms);
    return dateTime.toLocaleString('en-GB', { timeZone: 'UTC' });
}

function deleteTicket(id) {
    const Http = new XMLHttpRequest();
    const url='http://localhost:8080/tickets/' + id;
    Http.open("DELETE", url);
    Http.send();

    Http.onreadystatechange = function() {
        if(Http.readyState == 4) {
            if (Http.status != 200) {
                alert("ERROR: Ticket not found");
            } else {
                alert("Ticket deleted");
                location.reload();
            }
        }
    }
}

function closeTicket(id) {
    const Http = new XMLHttpRequest();
    const url='http://localhost:8080/tickets/' + id;
    Http.open("GET", url);
    Http.send();

    var element = document.getElementById("ticket-queue");

    Http.onreadystatechange = function() {
        if(Http.readyState == 4) {
            if (Http.status == 200) {
                 sendCloseRequest(id, JSON.parse(Http.response));
            } else {
                alert("ERROR: Issue closing ticket");
            }
        }
    }
}

function sendCloseRequest(id, oldTicket) {

    var creator = {};
    creator['id'] = oldTicket.creator.id;

    var body = {};
    body['title'] = oldTicket.title;
    body['creator'] = creator;
    body['description'] = oldTicket.description;
    body['created'] = oldTicket.created;
    body['status'] = "CLOSED";

    const Http = new XMLHttpRequest();
    const url='http://localhost:8080/tickets/' + id;
    Http.open("PUT", url, true);
    Http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    Http.send(JSON.stringify(body));

    Http.onreadystatechange = function() {
        if(Http.readyState == 4) {
            if (Http.status == 200) {
                alert("Ticket closed");
                location.reload();
            } else {
                alert("ERROR: Unable to create user");
            }
        }
    }
}
