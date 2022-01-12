package com.revature.repositories;

import com.revature.util.ConnectionFactory;
import com.revature.models.Role;
import com.revature.models.User;
import java.util.Optional;
import javax.management.relation.RoleList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

//-------------------------------------------------------------
//---- begin code by AGUZMAN
	
//----------------insert new user in DB
	public void InsertUSer(User Newuser) {
	
		try(Connection conn = ConnectionFactory.getConnection()){
		
		//Create a SQL statement using parameters to insert a new User in ers_users
		String sql = "INSERT INTO ers_users (ers_username, ers_pasword, user_first_name, user_last_name, user_email, user_role_id) " //creating a line break for readability
				    + "VALUES (?, ?, ?, ?, ?, ?); "; //these are parameters!! We have to specify the value of each "?"
		
		PreparedStatement ps = conn.prepareStatement(sql); //we use PreparedStatements for SQL commands with variables
		
		//use the PreparedStatement objects' methods to insert values into the query's ?s
		//the values will come from the User object we send in.
		ps.setString(1, Newuser.getUsername());
		ps.setString(2, Newuser.getPassword());
		ps.setString(3, Newuser.getFirstName());
		ps.setString(4, Newuser.getLastName());
		ps.setString(5, Newuser.getEmail());
		ps.setInt(6, Newuser.getNrole());

		//this executeUpdate() method actually sends and executes the SQL command we built
		ps.executeUpdate(); //we use executeUpdate() for inserts, updates, and deletes. 
		//we use executeQuery() for selects
		
		//send confirmation to the console if successfully.
		System.out.println("User " + Newuser.getUsername() + " created. Successfuly");
		
		
	} catch(SQLException e) {
		System.out.println("Add User failed...");
		e.printStackTrace();
	}
	
	
}
//---------------------select user from DB
	public List<User> getUser (String username, String password) {
		String usr_typed = username;
		String pas_typed = password;
		try(Connection conn = ConnectionFactory.getConnection()){ 
			//Initialize an empty ResultSet object that will store the results of our SQL query
			ResultSet rs = null;
			//write the query that we want to send to the database, and assign it to a String
			String sql = "SELECT * FROM ers_users where ers_username = ?"
			             +" and  ers_pasword= ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, usr_typed.toUpperCase());
			ps.setString(2, pas_typed.toUpperCase());
			
			//EXECUTE THE QUERY, by putting the results of the query into our ResultSet object
			//The Statement object has a method that takes Strings to execute as a SQL query
			rs = ps.executeQuery();   //statement.executeQuery(sql);
			System.out.println(" execute query..");
			//create an empty ArrayList to be filled with the data from the database
			List<User> userList = new ArrayList<>();
			Role rol1 =  Role.EMPLOYEE;
			Role rol2 = Role.FINANCE_MANAGER;
			//String nr = rol1.name(rol1.ordinal());
			int numr = rol1.ordinal();
			numr= rol1.EMPLOYEE.ordinal()+1;
			int numr2 = rol1.FINANCE_MANAGER.ordinal()+1;
			//rol1.toString();
			System.out.println("The num of rol Employee is...."+ numr + "and finance manager is..." +numr2);
			//while there are results in the resultset...
			while(rs.next()) {
				
				//Use the all args constructor to create a new Employee object from each returned row from the DB
				User e = new User(
						//we want to use rs.get for each column in the record
						rs.getInt("ers_users_id"),
						rs.getString("ers_username"),
						rs.getString("ers_pasword"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id")
						);
				
				//and populate the ArrayList with each new User object
				userList.add(e); //e is the new User object we created above
		
			}
						
			//when there are no more results in rs, the while loop will break
			//then, return the populated ArrayList of Employees
			return userList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong selecting users!");
			e.printStackTrace();
		}

		return null;
	}
	
	public List<User> getAllUser(){
		try(Connection conn = ConnectionFactory.getConnection()){ 
			//Initialize an empty ResultSet object that will store the results of our SQL query
			ResultSet rs = null;
			//write the query that we want to send to the database, and assign it to a String
			String sql = "SELECT * FROM ers_users ;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setString(1, usr_typed.toUpperCase());
			//ps.setString(2, pas_typed.toUpperCase());
			
			//EXECUTE THE QUERY, by putting the results of the query into our ResultSet object
			//The Statement object has a method that takes Strings to execute as a SQL query
			rs = ps.executeQuery();   //statement.executeQuery(sql);
			System.out.println(" execute query..");
			//create an empty ArrayList to be filled with the data from the database
			List<User> userList = new ArrayList<>();
			Role rol1 =  Role.EMPLOYEE;
			Role rol2 = Role.FINANCE_MANAGER;
			//String nr = rol1.name(rol1.ordinal());
			int numr = rol1.ordinal();
			numr= rol1.EMPLOYEE.ordinal()+1;
			int numr2 = rol1.FINANCE_MANAGER.ordinal()+1;
			//rol1.toString();
			System.out.println("The num of rol Employee is...."+ numr + "and finance manager is..." +numr2);
			//while there are results in the resultset...
			while(rs.next()) {
				
				//Use the all args constructor to create a new Employee object from each returned row from the DB
				User e = new User(
						//we want to use rs.get for each column in the record
						rs.getInt("ers_users_id"),
						rs.getString("ers_username"),
						rs.getString("ers_pasword"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id")
						);
				
				//and populate the ArrayList with each new User object
				userList.add(e); //e is the new User object we created above
		
			}
						
			//when there are no more results in rs, the while loop will break
			//then, return the populated ArrayList of Employees
			return userList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong selecting users!");
			e.printStackTrace();
		}

		return null;
		
	}
//---- End code by AGUZMAN
//------------------------------------------------------------
	
	
    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    public Optional<User> getByUsername(String username) {
    	try(Connection conn = ConnectionFactory.getConnection()){ 
			//Initialize an empty ResultSet object that will store the results of our SQL query
			ResultSet rs = null;
			//write the query that we want to send to the database, and assign it to a String
			String sql = "SELECT * FROM ers_users where ers_username = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);//.toUpperCase());
		
			rs = ps.executeQuery();   //statement.executeQuery(sql);
			System.out.println(" execute query..");

				
				//create an empty ArrayList to be filled with the data from the database
				//List<User> userList = new ArrayList<>();
				Role rol1 =  Role.EMPLOYEE;
				Role rol2 = Role.FINANCE_MANAGER;
				//String nr = rol1.name(rol1.ordinal());
				int numr = rol1.ordinal();
				numr= rol1.EMPLOYEE.ordinal()+1;
				int numr2 = rol1.FINANCE_MANAGER.ordinal()+1;
				//rol1.toString();
				//while there are results in the resultset...
				while(rs.next()) {
					
					//Use the all args constructor to create a new Employee object from each returned row from the DB
					User e = new User(
							//we want to use rs.get for each column in the record
							rs.getInt("ers_users_id"),
							rs.getString("ers_username"),
							rs.getString("ers_pasword"),
							rs.getString("user_first_name"),
							rs.getString("user_last_name"),
							rs.getString("user_email"),
							rs.getInt("user_role_id")
							);
					Optional<User> usropt = Optional.of(e);
					return usropt;
			
				}
	
			
		} catch (SQLException e) {
			System.out.println("Something went wrong selecting users!");
			e.printStackTrace();
		}
    	
       return Optional.empty();
    }

    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     *
     * Note: The userToBeRegistered will have an id=0, and username and password will not be null.
     * Additional fields may be null.
     */
    public User create(User userToBeRegistered) {
    	
        return userToBeRegistered;
    }
}
