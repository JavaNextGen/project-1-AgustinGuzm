const url = "http://localhost:3000/" //putting our base URL in a variable for cleaner code below
//eventually, we'll use this in our fetch requests and make calls to our server by appending endpoints

//add eventListeners to our buttons to give them functionality
//document.getElementById("re_stat").addEventListener("click", gestatreimb);
document.getElementById("getuidReimbButton").addEventListener("click", gestatreimb);
//document.getElementById("restButton").addEventListener("click", gestatreimb);


//remember, async returns a promise (which fetch request return)
async function gestatreimb() {

    //we will send a fetch request to get our employee data
    //by default, fetch requests send GET requests
    let e_id  = document.getElementById("reimb_id").value;
    let e_uid = document.getElementById("reimb_uid").value;
    let response = await fetch(url + "reimb/" + e_id +"/" + e_uid , {credentials: "include"});
    //logging the response in the console just to see the response object
    console.log(response);

    //control flow for is the request is successful
    if(response.status === 200){

        let data = await response.json(); //parse the JSON data from the response into a JS object

        //logging the actual REIMBURSEMENT data that has been parsed from JSON -> JS
        console.log(data);
        //For every REIMBURSEMENT object we got back (stored in the data variable), put it in the table
        for(let reimb of data){

            //create a table row
            let row = document.createElement("tr");

            //create a data cell for each reimbursement field
            let cell = document.createElement("td");
            //fill the cell with the appropriate reimbursement data
            cell.innerHTML = reimb.id;
            //add the td element (data cell) to the tr element (table row)
            row.appendChild(cell);

            let cell2 = document.createElement("td");
            cell2.innerHTML = reimb.nauthor;
            row.appendChild(cell2);

            let cell3 = document.createElement("td");
            let d_reimb = reimb.fsubmited;
            let fdreimb = d_reimb.substring(5,7) + "/" + d_reimb.substring(8,10) + "/" + d_reimb.substring(0,4);
            cell3.innerHTML = fdreimb;
            //cell3.innerHTML = reimb.fsubmited;
            row.appendChild(cell3);

            let cell4 = document.createElement("td");
    
            cell4.innerHTML = reimb.amount;
            row.appendChild(cell4);

            let cell5 = document.createElement("td");
            cell5.innerHTML = reimb.nstatus;
            row.appendChild(cell5);
            
            let cell6 = document.createElement("td");
            cell6.innerHTML = reimb.ntype;
            row.appendChild(cell6);  

            //append the tr called row that we created in the for loop to the table body
            //a new row will be appended for every reimbursement object that comes in
            document.getElementById("reimbstatBody").appendChild(row);
        }

    }


}