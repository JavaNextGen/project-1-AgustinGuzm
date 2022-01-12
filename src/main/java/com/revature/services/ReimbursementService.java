package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;

import java.util.Collections;
import java.util.List;

//add by aguzman
import com.revature.repositories.ReimbursementDAO;
// end by aguzman
/**
 * The ReimbursementService should handle the submission, processing,
 * and retrieval of Reimbursements for the ERS application.
 *
 * {@code process} and {@code getReimbursementsByStatus} are the minimum methods required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create Reimbursement</li>
 *     <li>Update Reimbursement</li>
 *     <li>Get Reimbursements by ID</li>
 *     <li>Get Reimbursements by Author</li>
 *     <li>Get Reimbursements by Resolver</li>
 *     <li>Get All Reimbursements</li>
 * </ul>
 */
public class ReimbursementService {
	//----------add by aguzman
	ReimbursementDAO eDAO = new ReimbursementDAO(); //So that mean, I can use the methods from the ReimbursementDAO
	public void addReimbursement(Reimbursement NewReimbursement) {
		
		//Take in the Reimbursement object sent from the menu and send it 
		//to the ReimbursementDAO to be inserted into the database
		
		//call the DAO method that inserts the new Reimbursement
		eDAO.InsertReimbursement(NewReimbursement);
	}
	public void updReimbursement(Reimbursement unprocessedReimbursement) {
 
    	eDAO.update(unprocessedReimbursement);

	}
	//----------end add by aguzman
    /**
     * <ul>
     *     <li>Should ensure that the user is logged in as a Finance Manager</li>
     *     <li>Must throw exception if user is not logged in as a Finance Manager</li>
     *     <li>Should ensure that the reimbursement request exists</li>
     *     <li>Must throw exception if the reimbursement request is not found</li>
     *     <li>Should persist the updated reimbursement status with resolver information</li>
     *     <li>Must throw exception if persistence is unsuccessful</li>
     * </ul>
     *
     * Note: unprocessedReimbursement will have a status of PENDING, a non-zero ID and amount, and a non-null Author.
     * The Resolver should be null. Additional fields may be null.
     * After processing, the reimbursement will have its status changed to either APPROVED or DENIED.
     */
    public Reimbursement process(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver) {

    	return null;
    }

    /**
     * Should retrieve all reimbursements with the correct status.
     */
    public List<Reimbursement> getReimbursementsByStatus(Status status) {
    	 return eDAO.getByStatus(status);
        //return Collections.emptyList();
    }
    //--------add by aguzman
    public List<Reimbursement> getByIdUid(int reimbId, int userId){
    	return eDAO.getByIdUid(reimbId, userId);
    }
    public List<Reimbursement> getAll(){
    	return eDAO.getAll();
    }
    //--------end add by aguzman
}
