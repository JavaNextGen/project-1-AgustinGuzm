const url = "http://localhost:3000/" ;//putting our base URL in a variable for cleaner code below
//eventually, we'll use this in our fetch requests and make calls to our server by appending endpoints

//add eventListeners to our buttons to give them functionality
//document.getElementById("re_stat").addEventListener("click", gestatreimb);
document.getElementById("pstnubtn").addEventListener("click", postauser);
//document.getElementById("restButton").addEventListener("click", gestatreimb);


//remember, async returns a promise (which fetch request return)
async function postauser() {
    //"FirstName": "VITO2",
    //"LastName": "CORLEONE3",
    //"Email": "vito@vito.com",
    //"nrole": 2,
    //"id": 9,
    //"username": "VITO",
    //"password": "CORLEONE"

    
    //we will send a fetch request to get our employee data
    //by default, fetch requests send GET requests
    let usr_fnam = document.getElementById("nusr_fname").value;
    let usr_lnam = document.getElementById("nusr_lname").value;
    let usr_unam = document.getElementById("nusr_uname").value;
    let usr_pass = document.getElementById("nusr_pass").value;
    let usr_emai = document.getElementById("nusr_emai").value;
    //let usr_type = document.getElementById("ty_emp").value;
    let usr_type = document.querySelector('input[name = "ty_emp"]:checked').value;
    //if document.getElementById("nuser_empl").checked
    //alert(usr_type);
    let userobj = {
        FirstName: usr_fnam,
        LastName: usr_lnam,
        username: usr_unam,
        password: usr_pass,
        Email: usr_emai,
        nrole: usr_type 

    }

    console.log(userobj);
    //fetch request to the server
    //remember the second parameter fetch can take? It's essentially for configuring our fetch request
    //fetch sends a GET by default, but this seconds parameter can change that and more!
    let response = await fetch (url + "user", {

        method: "POST", //send a POST request (would be a GET if we didn't do this...)
        body: JSON.stringify(userobj), //turn our user object into JSON
        credentials: "include"
        //this last line will ensure that the cookie is captured (so that we can have a user session)
        //future fetches will also require this "include" value to send the cookie back
    });
    //Reimbursement(0, 3,id, 0, amoun,sfrequ,null, desr, nreciept,typer)
    // the response in the console just to see the response object
    console.log(response);
        //control flow based on successful/unsuccessful login
    if(response.status === 201) {
        //wipe our login row and welcome the user 
        document.getElementById("formReq").innerText="User crated successfully";
    } else {
        document.getElementById("formReq").innerText="User does not sent";
    }
}