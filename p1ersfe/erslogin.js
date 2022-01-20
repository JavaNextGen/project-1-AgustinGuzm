const url = "http://localhost:3000/" //putting our base URL in a variable for cleaner code below
//eventually, we'll use this in our fetch requests and make calls to our server by appending endpoints

//add eventListeners to our buttons to give them functionality
//document.getElementById("getEmployeeButton").addEventListener("click", getEmployees);
document.getElementById("loginButton").addEventListener("click", loginFunction);

//this function will send the user-inputted login credentials to our server
async function loginFunction() {

    //gather the user inputs from the login inputs
    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;

    //we want to send the user/pass as JSON, so we need a JS object to send
    let user = {
        username:usern,
        password:userp
    }
    //This object will reflect our DTO in Java... This is the data we want to transfer!

    console.log(user)

    //fetch request to the server
    //remember the second parameter fetch can take? It's essentially for configuring our fetch request
    //fetch sends a GET by default, but this seconds parameter can change that and more!
    let response = await fetch (url + "login", {

        method: "POST", //send a POST request (would be a GET if we didn't do this...)
        body: JSON.stringify(user), //turn our user object into JSON
        credentials: "include"
        //this last line will ensure that the cookie is captured (so that we can have a user session)
        //future fetches will also require this "include" value to send the cookie back
    });

    console.log(response.status); //userful for debug :)

    //control flow based on successful/unsuccessful login
    if(response.status === 202 || response.status === 201 ) {
        //wipe our login row and welcome the user 
        document.getElementById("loginRow").innerText="Welcome!";
       // window.location.replace("fm_lstusers.html");
       if(response.status === 201){
           window.location.href = "us_header.html";
       }else{
           window.location.href = "fm_headder.html";
       }
    } else {
        document.getElementById("loginRow").innerText="Login failed! Refresh the page";
    }


}