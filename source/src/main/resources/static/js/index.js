function login() {

    var id = document.getElementById('userid').value;

    if (isNaN(id) || id < 1) {
        alert("ERROR: User ID value not valid");
        return;
    }

    const Http = new XMLHttpRequest();
    const url='http://localhost:8080/users/' + id;
    Http.open("GET", url);
    Http.send();

    Http.onreadystatechange = function() {
        if(Http.readyState == 4) {
            if (Http.status != 200) {
                alert("ERROR: User not found");
            }
          console.log(Http.responseText)
        }
    }

}

function create() {

    var name = document.getElementById('name').value;
    var isAdmin = document.getElementById('admin').checked;

    var body = {};
    body['name'] = name;
    body['admin'] = isAdmin;

    const Http = new XMLHttpRequest();
    const url='http://localhost:8080/users/create';
    Http.open("POST", url, true);
    Http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    Http.send(JSON.stringify(body));

    Http.onreadystatechange = function() {
        if(Http.readyState == 4) {
            if (Http.status == 201) {
                alert("OK: User created");
            } else {
                //alert("ERROR: Unable to create user");
                alert(http.responseText);
            }
        }
    }

}