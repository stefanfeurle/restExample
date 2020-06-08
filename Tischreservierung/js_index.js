var user = {firstName:"", lastName:"", email: "", password: "",  phoneNumber: "", creationDate: "", creationTime: "", token: "", isLogin: false};
var user1 = {email:"", password:""};
var user2 = {token: null, isLogin: false};
var restaurants = [];
var restaurant = {companyBookNumber:"", kindOfRestaurant:"", name:"", email:"", homepage:"", phoneNumber:"", postCode: 0, town:"", street:"", houseNumber: 0}
var reservations = [];
var reservation = {numberOfPeople: null, companyBookNumberRestaurant: null, emailUser: null, date: null, time: null, id: null};
var reservationPost= {numberOfPeople: null, companyBookNumberRestaurant: null, emailUser:null, date: null, time: null};
var javaToken = "";



function loginPage() {
    window.document.location.href = "login.html";
}

function restaurantsPage() {    
    window.document.location.href = "restaurant.html";    
}

function tableReservationPage() {
    if(user.token !== "" && user.password !== "" && user.email !== "") {
        window.document.location.href = "reservation.html";
    } else {
        alert("Login please!!"); 
    }    
}

function reservationPage() {
    window.document.location.href = "reservationPage.html";
}

function newReservationPage() {
    window.document.location.href = "newReservationPage.html";
}

function login() {
    user1.email = document.getElementById("username").value;
    user1.password = document.getElementById("password").value;
    getToken();
    if(javaToken === "" || javaToken === 'null' || javaToken === null) {
        alert("Falscher Username oder Passwort!!");
    } else {
        getUser();
        localStorage.setItem("user", JSON.stringify(user));
        window.document.location.href = "reservation.html";
    }
}

function getToken() {
    var myJson = JSON.stringify(user1);
    var xhttp = new XMLHttpRequest();
    /*xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if(this.responseText !== null && this.responseText.length > 0){
            javaToken = JSON.parse(this.responseText);
          } else {
              alert("Falscher Username oder Passwort!!");
          }
        }
      };*/
    xhttp.open("POST", "http://localhost:8080/reservation/user", false);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(myJson);

    if(xhttp.status == 200){
        if(xhttp.responseText !== null || xhttp.responseText.length > 0){
            javaToken = JSON.parse(xhttp.responseText).token;
          } 

          if (javaToken === null || javaToken.length <= 0) {
            alert("Falscher Username oder Passwort!!");
        } else {
            localStorage.setItem("token", JSON.stringify(javaToken));
        }
    }
}

function getUser() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/reservation/reservation/" + javaToken, false);
    xhttp.send();

    if (xhttp.status == 200) {
        if(xhttp.responseText !== null && xhttp.responseText.length > 0){
            user = JSON.parse(xhttp.responseText);
        }
    }
    console.log(user.firstName + " " + user.lastName);    
}

function printUserName() {    
    user = JSON.parse(localStorage.getItem("user")); 
    console.log(user);
    if(user !== null && user !== undefined) {
        var userName = user.firstName + "_" + user.lastName.toUpperCase();
        document.getElementById("nameUser").innerHTML = userName;
    }
    var timer = setInterval(myTimer ,100);
    function myTimer() {
        javaToken = JSON.parse(localStorage.getItem("token")); 
        if (javaToken === null && user === null) {
            window.document.location.href = "index.html";
        }
    }
}

function logout() {
    user2.token = JSON.parse(localStorage.getItem("token")); 
    var myJson = JSON.stringify(user2);
    var xhttp = new XMLHttpRequest();
    xhttp.open("PUT", "http://localhost:8080/reservation/user/" + user2.token, false);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(myJson);

    if(xhttp.status == 200){
        if(xhttp.responseText !== null || xhttp.responseText.length > 0){
            var answer = xhttp.responseText;
         }
        if (answer === null || answer === "logout failed") {
            alert("Logout failed");
          } else {
             localStorage.clear();
             javaToken = null;
             user1 = null;
             user = null;
             user2.token = null;
             window.document.location.href = "index.html";
          }
        }
    }

function getRestaurant(id) {    
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/reservation/restaurant/" + restaurants[id].companyBookNumber, false);
    xhttp.send();

    if (xhttp.status == 200) {
        if(xhttp.responseText !== null && xhttp.responseText.length > 0){
            restaurant = JSON.parse(xhttp.responseText);
        }
    }
    console.log(restaurant.name);    
}

function printRestaurant(id) {
    getRestaurant(id);
    document.getElementById("restaurantContainer").innerHTML = "Restaurants";
    var output = "<main><h3>" + restaurant.name + "</h3>" + restaurant.kindOfRestaurant + "<br>" 
    + restaurant.postCode + " " + restaurant.town + "<br>" + restaurant.street + " " + restaurant.houseNumber 
    + "<br>" + restaurant.email + "<br>" + restaurant.homepage + "<br>" + restaurant.phoneNumber + "</main>";
    document.getElementById("restaurant1").innerHTML = output;    
    console.log(restaurant.name);
}


function getRestaurants(){
    var xhttp = new XMLHttpRequest();    
    /*xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          if (this.responseText !== null && this.responseText.length > 0){
          restaurants = JSON.parse(this.responseText);
           } else {
            alert("Restaurants nicht gefunden!");
           }
        }
    };*/
    xhttp.open("GET", "http://localhost:8080/reservation/restaurant", false);
    xhttp.send();
    if (xhttp.status == 200) {
        if(xhttp.responseText !== null && xhttp.responseText.length > 0){
            restaurants = JSON.parse(xhttp.responseText);
        }
    }
}

function printRestaurants() {
    getRestaurants();
    var id = 0;
    var output = "";
    if (restaurants !== null) {
        for (x of restaurants) {
            console.log(x)
            output = output + "<button onclick='printRestaurant(" + id + ")'><h2>"  + x.name + "</h2><br>(" + x.postCode +
             " " + x.town + ", " + x.street + " " + x.houseNumber + ", " + x.phoneNumber + "</button><br>";
             id++;
        }
    }   
    document.getElementById("restaurant1").innerHTML = output;
}

function printReservations() {
    printUserName();
    document.getElementById("container").style.left = "4%";
    getReservations();
    getRestaurants();
    var id = 0;
    var output = "<h3>Deine Reservierung(en)</h3>";
    if (reservations !== null) {
        for (x of reservations) {
           for (y of restaurants) {
               if (x.companyBookNumberRestaurant === y.companyBookNumber) {
                   restaurant = y;
               }
           }
            console.log(x)
            output = output + "<button onclick='printReservation(" + id + ")'><h2>Reservierungsnummer: "  + x.id 
            + "</h2>Reservierungszeit: " + x.date + " " + x.time + "<br>Anzahl der Personen: " + x.numberOfPeople 
            + "<br>" + restaurant.kindOfRestaurant + " " + restaurant.name + "</button><br>";
             id++;
        }
    }   
    document.getElementById("reservations1").innerHTML = output;
}

function getReservations(){
    javaToken = JSON.parse(localStorage.getItem("token"));
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/reservation/reservation/all/" + javaToken, false);
    xhttp.send();
    if (xhttp.status == 200) {
        if(xhttp.responseText !== null && xhttp.responseText.length > 0){
            reservations = JSON.parse(xhttp.responseText);
        }
    }
}

function getReservation(NumberOfReservation) {    
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/reservation/reservation/" + NumberOfReservation + "/" + javaToken, false);
    xhttp.send();

    if (xhttp.status == 200) {
        if(xhttp.responseText !== null && xhttp.responseText.length > 0){
            reservation = JSON.parse(xhttp.responseText);
        }
    }
    console.log(reservation.id);    
}

function printReservation(id) {
    NumberOfReservation = reservations[id].id;
    getReservation(NumberOfReservation);
    for (x of restaurants) {
        if (x.companyBookNumber === reservation.companyBookNumberRestaurant) {
            restaurant = x;
            break;
        }
    }
    document.getElementById("reservationContainer").innerHTML = "Deine_Reservierungen";
    document.getElementById("delete1").innerHTML = "Reservierung_STORNO"
    var output = "<main><h3>Reservierungsnummer: " + reservation.id + "</h3>" + "Reservierungszeit: " + reservation.date 
    + " " + reservation.time + "<br>Anzahl der Personen: " + reservation.numberOfPeople 
    + "<br>" + restaurant.kindOfRestaurant + " " + restaurant.name +"</main>";
    document.getElementById("reservations1").innerHTML = output;    
    console.log(restaurant.name);
}

function deleteReservation1() {
    document.getElementById("delete2").innerHTML = "STORNO_bestätigen";
    alert("Sind sie sich sicher, dass sie diese Reservierung stornieren wollen! Dann bestätigen sie die Storno nochmals!");
}

function deleteReservation2() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "http://localhost:8080/reservation/reservation/" + reservation.id + "/" + javaToken, false);
    xhttp.send();

    if (xhttp.status == 200) {
        if(xhttp.responseText !== null && xhttp.responseText.length > 0){
            storno = xhttp.responseText;
        }
    }
    if ( storno === "delete successful") {
        alert("Reservierung gelöscht!");
        window.document.location.href = "reservationPage.html";
    }  else {
        alert("Delete failed! Try again!");
        window.document.location.href = "reservationPage.html";
    }
}

function newReservations() {
    getRestaurants();
    printUserName();
    var output = "<option value='--'>Bitte wählen:</option>";
    for (x of restaurants) {
        output = output + "<option value='" + x.companyBookNumber + "'>" + x.name + "</option>";
    }
    document.getElementById("restaurant").innerHTML = output;
}

function newReservation() {
    var correctInput = true;
    reservationPost.companyBookNumberRestaurant = document.getElementById("restaurant").value;
    var hour = document.getElementById("hour").value;
    var min = document.getElementById("min").value;
    reservationPost.time = hour + ":" + min;
    var year = document.getElementById("year").value;
    var month = document.getElementById("month").value;
    var day = document.getElementById("day").value;
    reservationPost.date = year + "-" + month + "-" + day;
    reservationPost.numberOfPeople = parseInt(document.getElementById("countPerson").value);
    user = JSON.parse(localStorage.getItem("user"));
    reservationPost.emailUser = user.email;
    
    if (reservationPost.companyBookNumberRestaurant === "--" || hour === "--" || min === "--" || year === "--" || month === "--" || day === "--"){
        correctInput = false;
    }
    if (correctInput === true) {
        postReservation();
        window.document.location.href = "reservationPage.html";
    } else {
        alert("Falsche Eingabe!!");
    }
}

function postReservation() {
    javaToken = JSON.parse(localStorage.getItem("token"));
    var myJson = JSON.stringify(reservationPost);
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8080/reservation/reservation/" + javaToken, false);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(myJson);

    if(xhttp.status == 200){
        if(xhttp.responseText !== null || xhttp.responseText.length > 0){
            reservation  = JSON.parse(xhttp.responseText);
         }        
    }
    if (reservation.id === null) {
        alert("Die Reservierung schlug fehl!!")
    } else {
        var alertVar = "Reservierungsnummer: " + reservation.id;
        alert(alertVar);
    }
}


