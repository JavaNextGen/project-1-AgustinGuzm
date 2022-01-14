package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserDAO;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
/**
 * The AuthService should handle login and registration for the ERS application.
 *
 * {@code login} and {@code register} are the minimum methods required; however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Retrieve Currently Logged-in User</li>
 *     <li>Change Password</li>
 *     <li>Logout</li>
 * </ul>
 */
public class AuthService {

    /**
     * <ul>
     *     <li>Needs to check for existing users with username/email provided.</li>
     *     <li>Must throw exception if user does not exist.</li>
     *     <li>Must compare password provided and stored password for that user.</li>
     *     <li>Should throw exception if the passwords do not match.</li>
     *     <li>Must return user object if the user logs in successfully.</li>
     * </ul>
     */
	/* cometed by aguzman
    public User login(String username, String password) {
    	
        return null;
    }
     ** end of comented
     */

    /**
     * <ul>
     *     <li>Should ensure that the username/email provided is unique.</li>
     *     <li>Must throw exception if the username/email is not unique.</li>
     *     <li>Should persist the user object upon successful registration.</li>
     *     <li>Must throw exception if registration is unsuccessful.</li>
     *     <li>Must return user object if the user registers successfully.</li>
     *     <li>Must throw exception if provided user has a non-zero ID</li>
     * </ul>
     *
     * Note: userToBeRegistered will have an id=0, additional fields may be null.
     * After registration, the id will be a positive integer.
     */
    public User register(User userToBeRegistered) {	
    	
        return null;
    }

    /**
     * This is an example method signature for retrieving the currently logged-in user.
     * It leverages the Optional type which is a useful interface to handle the
     * possibility of a user being unavailable.
     */
    public Optional<User> RetrieveCurrentUser() {
    	
        return Optional.empty();
    }
    
	//hardcoding username/password - which you WON'T do in P1
	
	//Typically, you'll want to validate username/password against some username/password in the DATABASE.
	//So in your P1, you'd be sending the data sent into the LoginDTO... 
	//and most likely doing some DAO method that uses those values to check for matching values in the DB.
	//soooooo we probably need a DAO method that has something like: 
		//"select * from users where username = ? and password = ?" and then insert the values of the DTO for parameters
	
	//public boolean login(String username, String password) {
	public int login(String username, String password) {	
		UserDAO eDAO = new UserDAO();	
		/*if (eDAO.getUser(username, password) != null) {
			return true;
		} */
		int nruser =0;
		int idusr =0;
		List<User> Users = eDAO.getUser(username,password);
		
		int nuser = Users.size();
		if (nuser == 1){
			  User pr = Users.get(0);
			  nruser  = pr.getNrole();
			  idusr   = pr.getId();
			  return nruser;
		}else {
			return 0;
		}

	
		/*if(username.equals("user") && password.equals("password")) {
			return true; //true indicates successful login
		}*/
		//return false; //unsuccessful login
		
		
	}

}
