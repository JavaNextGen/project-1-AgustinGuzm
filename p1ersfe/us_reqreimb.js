const url = "http://localhost:3000/" //putting our base URL in a variable for cleaner code below
//eventually, we'll use this in our fetch requests and make calls to our server by appending endpoints

//add eventListeners to our buttons to give them functionality
//document.getElementById("re_stat").addEventListener("click", gestatreimb);
document.getElementById("postReimbButton").addEventListener("click", postatreimb);
//document.getElementById("restButton").addEventListener("click", gestatreimb);


//remember, async returns a promise (which fetch request return)
async function postatreimb() {

        //"fsubmited": now(),
        //"Dreimbur": "Hotel Palacite",
        //"receipt": 0,
        //"nauthor": 2,
        //"nresolver": 0,
        //"nstatus": 1,
        //"ntype": 1,
        //"id": 8,
        //"amount": 555.55
        //Reimbursement(0, 3,id, 0, amoun,sfrequ,null, desr, nreciept,typer)
    //we will send a fetch request to get our employee data
    //by default, fetch requests send GET requests
    let reim_usr = document.getElementById("reimb_usn").value;
    let reim_rcp = document.getElementById("reimb_rcp").value;
    let reim_des = document.getElementById("reimb_des").value;
    let reim_mnt = document.getElementById("reimb_mnt").value;
    let reim_typ = document.getElementById("reimb_typ").value;
    
    let reimbobj = {
        fsubmited: "",
        Dreimbur: reim_des,
        receipt: reim_rcp,
        nauthor: reim_usr,
        nresolver: 0,
        nstatus: 0,
        ntype: reim_typ,
        amount:reim_mnt  
    }

    console.log(reimbobj);
    //fetch request to the server
    //remember the second parameter fetch can take? It's essentially for configuring our fetch request
    //fetch sends a GET by default, but this seconds parameter can change that and more!
    let response = await fetch (url + "reimb", {

        method: "POST", //send a POST request (would be a GET if we didn't do this...)
        body: JSON.stringify(reimbobj), //turn our user object into JSON
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
        document.getElementById("formReq").innerText="Reimbursement sent successfully";
    } else {
        document.getElementById("formReq").innerText="Reimbursement does not sent";
    }
}