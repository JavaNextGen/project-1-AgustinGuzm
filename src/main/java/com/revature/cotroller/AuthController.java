package com.revature.cotroller;
import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.services.AuthService;

import io.javalin.http.Handler;

public class AuthController {
	
	AuthService as = new AuthService();

	public Handler loginHandler = (ctx) -> {
		
		//what's the request body? (which we get from ctx.body) it's the data that gets sent in with a request
		//GET requests will have empty request bodies, but POST requests, which send data, will have some data.
		String body = ctx.body(); //turn the body (data) of the POST request into a Java String
		
		Gson gson = new Gson(); //create a new Gson object to make Java <-> JSON conversions
		
		LoginDTO LDTO = gson.fromJson(body, LoginDTO.class); //turn that JSON String into a LoginDTO object
		
		
		//control flow to determine what happens in the event of successful/unsuccessful login
		//invoke the login() method of the AuthService using the username and password from the LoginDTO
		int userval = as.login(LDTO.getUsername(), LDTO.getPassword());
		//if(as.login(LDTO.getUsername(), LDTO.getPassword())) {
		if (userval >0	) {
			//create a user session so that they can access the applications other functionalities
			ctx.req.getSession(); //req is a "Request Object", we establish sessions through it
			
			ctx.res.setHeader("Set-Cookie", "key=value; HttpOnly; SameSite=None; Secure");
			//return a successful status code 
			ctx.status(202); //202 - accepted. (but you could use any 200 level status code)
			
			//send a message relaying the success
			//String nr= Integer.toString(userval);
			if (userval == 1) {
			ctx.result("Login Successful! :)");
			ctx.status(201);
			}else {
				ctx.result("Login Successful! :)");
			}
			System.out.println(userval);
			//ctx.result(nr);
		} else {
			
			ctx.status(401); //"unauthorized" status code
			ctx.result("Login Failed! :(");
			
		}
		
	};

}
