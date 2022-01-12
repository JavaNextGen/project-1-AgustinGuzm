package com.revature.cotroller;

import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.services.ReimbursementService;
import io.javalin.http.Handler;

public class ReimbursementControler {
   ReimbursementService ces = new ReimbursementService();
	public Handler getReimbursementHandler_list = (ctx) -> {
        if(ctx.req.getSession() != null) {
        	
        	List<Reimbursement> allreimb = ces.getAll();
            Gson gson = new Gson();
            String JSONreimb = gson.toJson(allreimb);
            ctx.result(JSONreimb);
            ctx.status(200);
        }else {
            ctx.result("oh no you failed to get the employees!! ");
            ctx.status(404);
        }
    };
    
	public Handler getReimbursementHandler_id = (ctx) -> {
        if(ctx.req.getSession() != null) {
        	String e_id = ctx.pathParam("e_st");
        	System.out.println("Value of status is..."+e_id);
        	Status e_st = Status.valueOf(e_id);
        	List<Reimbursement> Reimbp = ces.getReimbursementsByStatus(e_st);
            Gson gson = new Gson();
            String JSONReimbp = gson.toJson(Reimbp);
            ctx.result(JSONReimbp);
            ctx.status(200);
        }else {
            ctx.result("oh no you failed to get the User!! ");
            ctx.status(404);
        }
    };   
    
	public Handler getReimbursementHandler_stat = (ctx) -> {
        if(ctx.req.getSession() != null) {
        	String e_id = ctx.pathParam("e_st");
        	System.out.println("Value of status is..."+e_id);
        	Status e_st = Status.valueOf(e_id);
        	List<Reimbursement> Reimbp = ces.getReimbursementsByStatus(e_st);
            Gson gson = new Gson();
            String JSONReimbp = gson.toJson(Reimbp);
            ctx.result(JSONReimbp);
            ctx.status(200);
        }else {
            ctx.result("oh no you failed to get the User!! ");
            ctx.status(404);
        }
    };

	
	
	public Handler getReimbIdUidHandler_RidUid = (ctx) -> {
        if(ctx.req.getSession() != null) {
        	String e_id = ctx.pathParam("e_id");
        	String e_uid =ctx.pathParam("e_uid");
        	int n_id = Integer.parseInt(e_id);
        	int n_uid = Integer.parseInt(e_uid);
        	//Status e_st = Status.APPROVED;
        	List<Reimbursement> Reimbp = ces.getByIdUid(n_id, n_uid);
            Gson gson = new Gson();
            String JSONReimbp = gson.toJson(Reimbp);
            ctx.result(JSONReimbp);
            ctx.status(200);
        }else {
            ctx.result("oh no you failed to get the User!! ");
            ctx.status(404);
        }
    };

	public Handler InsertReimbursement = (ctx) -> {
	    if(ctx.req.getSession() != null) {
	            String body = ctx.body();
	            Gson gson = new Gson();
	            Reimbursement reimb = gson.fromJson(body, Reimbursement.class);
	            ces.addReimbursement(reimb);
	            ctx.result("oh you Inserted the Reimbursement!!");
	            ctx.status(201);

	    }else {
	            ctx.result("oh no you failed insert the Reimbursement!! ");
	            ctx.status(404);
	        }

	    };
	    

	    public Handler updateReimbursement = (ctx) -> {
	        if(ctx.req.getSession() != null) {
	        	//int e_id = Integer.parseInt(ctx.pathParam("e_id")) ;
	        	String body = ctx.body();
	            Gson gson = new Gson();
	            Reimbursement reimb = gson.fromJson(body, Reimbursement.class);
	            ces.updReimbursement(reimb);
	            ctx.result("oh you update the Reimbursement!!");
	            ctx.status(200);

	        }else {
	            ctx.result("oh no you failed to update the Reimbursement!! ");
	            ctx.status(200);
	        }

	    }; 

	    
    
}
