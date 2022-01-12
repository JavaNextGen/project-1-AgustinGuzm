package com.revature.cotroller;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import io.javalin.http.Handler;
import com.revature.models.*;
import com.revature.services.*;
public class UserController {
	UserService ces = new UserService();
	public Handler getUserHandlerp = (ctx) -> {
        if(ctx.req.getSession() != null) {
        	String e_id = ctx.pathParam("e_id");
        	Optional<User> Userp = ces.getByUsername(e_id);
            Gson gson = new Gson();
            String JSONUser = gson.toJson(Userp);
            ctx.result(JSONUser);
            ctx.status(200);
        }else {
            ctx.result("oh no you failed to get the User!! ");
            ctx.status(404);
        }
    };
    
	public Handler getUserHandler_USrPAs = (ctx) -> {
        if(ctx.req.getSession() != null) {
        	String e_nm = ctx.pathParam("e_nm");
        	String e_ps = ctx.pathParam("e_ps");
            List<User> UserV = ces.getUserUidPass(e_nm, e_ps);
            Gson gson = new Gson();
            String JSONUsers = gson.toJson(UserV);
            ctx.result(JSONUsers);
            ctx.status(200);
        }else {
            ctx.result("oh no you failed to get the Users!! ");
            ctx.status(404);
        }
    };
    
	public Handler getUserHandler = (ctx) -> {
        if(ctx.req.getSession() != null) {
            List<User> allUser = ces.getAllUser();
            Gson gson = new Gson();
            String JSONUsers = gson.toJson(allUser);
            ctx.result(JSONUsers);
            ctx.status(200);
        }else {
            ctx.result("oh no you failed to get the Users!! ");
            ctx.status(404);
        }
    };
        
    public Handler insertUser = (ctx) -> {
        if(ctx.req.getSession() != null) {
        	String body = ctx.body();
            Gson gson = new Gson();
            User users = gson.fromJson(body, User.class);
            ces.AddUser(users);
            ctx.result("oh you Added the User!!");
            ctx.status(201);

        }else {
            ctx.result("oh no you failed to get the User!! ");
            ctx.status(404);
        }

    };
}
