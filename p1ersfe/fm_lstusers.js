const url = "http://localhost:3000/" //putting our base URL in a variable for cleaner code below
//eventually, we'll use this in our fetch requests and make calls to our server by appending endpoints

//add eventListeners to our buttons to give them functionality
document.getElementById("getmyReimbButton").addEventListener("click", getmyreimb);

//remember, async returns a promise (which fetch request return)
async function getmyreimb() {

    //we will send a fetch request to get our employee data
    //by default, fetch requests send GET requests
    let response = await fetch(url + "user");

    //logging the response in the console just to see the response object
    console.log(response);

    //control flow for is the request is successful
    if(response.status === 200){

        let data = await response.json(); //parse the JSON data from the response into a JS object

        //logging the actual employee data that has been parsed from JSON -> JS
        console.log(data);
        //
        //"FirstName": "AGUST",
        //"LastName": "GUZM",
        //"Email": "NOMAIL",
        //"nrole": 1,
        //"id": 1,
        //"username": "AGUZMAN",
        //"password": "GRAGRA"
    
        //For every Employee object we got back (stored in the data variable), put it in the table
        for(let user of data){

            //create a table row
            let row = document.createElement("tr");

            //create a data cell for each employee field
            let cell = document.createElement("td");
            //fill the cell with the appropriate employee data
            cell.innerHTML = user.id;
            //add the td element (data cell) to the tr element (table row)
            row.appendChild(cell);

            //we'll do this^ for every column in employees

            let cell2 = document.createElement("td");
            cell2.innerHTML = user.FirstName;
            row.appendChild(cell2);

            let cell3 = document.createElement("td");
            cell3.innerHTML = user.LastName;
            row.appendChild(cell3);

            let cell4 = document.createElement("td");
            cell4.innerHTML = user.username;
            row.appendChild(cell4);
            let cell5 = document.createElement("td");
            cell5.innerHTML = user.Email;
            row.appendChild(cell5);
            //append the tr called row that we created in the for loop to the table body
            //a new row will be appended for every employee object that comes in
            document.getElementById("userBody").appendChild(row);
        }

    }


}
