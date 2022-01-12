package com.revature.services;

import java.util.List;
import java.util.Optional;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {

	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */
//--------------------------------------------------------------
// --  begin code added by aguzman.
//	
	UserDAO eDAO = new UserDAO();
	
	public void AddUser(User username) {
		 eDAO.InsertUSer(username);
		
	}
	public List<User> getAllUser(){
		return eDAO.getAllUser();
		
	}
	public List<User> getUserUidPass(String username, String password ){
		return eDAO.getUser(username, password);
		//return null;
	}
// --  end code added by aguzman.	
//--------------------------------------------------------------	
	public Optional<User> getByUsername(String username) {
		return eDAO.getByUsername(username);
		//return Optional.empty();
	}
}
