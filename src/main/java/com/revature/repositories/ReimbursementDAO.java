package com.revature.repositories;

import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO {
	//-----------Begin add by aguzman
    public void InsertReimbursement(Reimbursement NewReimbursement) {
		
    	try(Connection conn = ConnectionFactory.getConnection()){
			
		String sql = "INSERT INTO ers_reimbursment (reimb_submited, reimb_amount, reimb_description, reimb_author, "
				     + "reimb_status_id, reimb_type_id) VALUES (Now(), ?, ?, ?, ?, ?);"; 
		
		PreparedStatement ps = conn.prepareStatement(sql); //PreparedStatements for SQL  
		
		ps.setDouble(1, NewReimbursement.getAmount());
		//ps.setString(2, NewReimbursement.getFsubmited());
		//ps.setString(3, newReimbursement.getFresolver());
		ps.setString(2, NewReimbursement.getDreimbur());
		//ps.setInt(5, newReimbursement.getReceipt());
		ps.setInt(3, NewReimbursement.getNauthor());
		//ps.setInt(7, newReimbursement.getNresolver());
		ps.setInt(4, NewReimbursement.getNstatus());
		ps.setInt(5, NewReimbursement.getNtype());
		ps.executeUpdate(); 
		//send confirmation to the console if successfully.
		System.out.println("Reimbursment  created Successfuly");
		
		
	} catch(SQLException e) {
		System.out.println("Add Reimbursement failed...");
		e.printStackTrace();
	}
		
    }
    
    public List<Reimbursement> getByIdUid(int reimbId, int userId){

    	 
		try(Connection conn = ConnectionFactory.getConnection()){ 
			//Initialize an empty ResultSet object that will store the results of our SQL query
			ResultSet rs = null;
			//String sql = "SELECT * FROM ers_reimbursment where reimb_author= ? and reimb_id = ?;";///reim_author= ? and
	
			//PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setInt(1, userId);
			//ps.setInt(2, reimbId);
			String sql;
			if(reimbId == 0 && userId >0) {
				 sql = "SELECT * FROM ers_reimbursment where reimb_author= ?;";///reim_author= ? and
				
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				//ps.setInt(2, reimbId);
				rs = ps.executeQuery();
			}else if (reimbId > 0 && userId ==0) {
				 sql = "SELECT * FROM ers_reimbursment where reimb_id = ?;";///reim_author= ? and
				PreparedStatement ps = conn.prepareStatement(sql);
				//ps.setInt(1, userId);
				ps.setInt(1, reimbId);
				rs = ps.executeQuery();
			}else if(reimbId > 0 && userId >0) {
				 sql = "SELECT * FROM ers_reimbursment where reimb_author= ? and reimb_id = ?;";///reim_author= ? and
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				ps.setInt(2, reimbId);
				rs = ps.executeQuery();
			}

			//rs = ps.executeQuery();
			
			System.out.println(" execute query..");
			//create an empty ArrayList to be filled with the data from the database
			List<Reimbursement> reimbList = new ArrayList<>();

			//while there are results in the resultset...
			while(rs.next()) {
				
				//Use the all args constructor to create a new Employee object from each returned row from the DB
				Reimbursement e = new Reimbursement(
						//we want to use rs.get for each column in the record
						rs.getInt("reimb_id"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"),
						rs.getDouble("reimb_amount"),
						rs.getString("reimb_submited"),
						rs.getString("reimb_resolved"),
						rs.getString("reimb_description"),
						rs.getInt("reimb_receipt"),
						rs.getInt("reimb_type_id")

						);

				//and populate the ArrayList with each new reimbursement object
				reimbList.add(e); 
				
			}
			return reimbList;
		} catch (SQLException e) {
			System.out.println("Something went wrong selecting Reimbursement!");
			e.printStackTrace();
		}
        return null;
    }
    //----------------end add by aguzman
	/**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
    public Optional<Reimbursement> getById(int id) {
 
		try(Connection conn = ConnectionFactory.getConnection()){ 
			//Initialize an empty ResultSet object that will store the results of our SQL query
			ResultSet rs = null;
			String sql = "SELECT * FROM ers_reimbursment where  reimb_id = ? ;";///reim_author= ? and
	
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			//ps.setInt(1, userId);

			rs = ps.executeQuery();
			
			System.out.println(" execute query..");
			//create an empty ArrayList to be filled with the data from the database
			//List<Reimbursement> reimbList = new ArrayList<>();
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
				Reimbursement e = new Reimbursement(
						//we want to use rs.get for each column in the record
						rs.getInt("reimb_id"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"),
						rs.getDouble("reimb_amount"),
						rs.getString("reimb_submited"),
						rs.getString("reimb_resolved"),
						rs.getString("reimb_description"),
						rs.getInt("reimb_receipt"),
						rs.getInt("reimb_type_id")

						);

				//and populate the ArrayList with each new reimbursement object
				Optional<Reimbursement> reimbopt = Optional.of(e);
				return reimbopt;
				
			} //end while
		} catch (SQLException e) {
			System.out.println("Something went wrong selecting Reimbursement!");
			e.printStackTrace();
		}
        return Optional.empty();
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
		try(Connection conn = ConnectionFactory.getConnection()){ 
			
			ResultSet rs = null; //Initialize an empty ResultSet object that will store the results of the SQL query
			//write the query that we want to send to the database, and assign it to a String
			String sql = "SELECT * FROM ers_reimbursment where reimb_status_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			int nst = status.ordinal();
			System.out.println("the status number is ..." + nst);
			ps.setInt(1, nst);
			rs = ps.executeQuery();   //statement.executeQuery(sql);
			//create an empty ArrayList to be filled with the data from the database
			List<Reimbursement> reimbList = new ArrayList<>();
			//while there are results in the resultset...
			while(rs.next()) {
				
				//Use the all args constructor to create a new Reimbursement object from each returned row from the DB
				Reimbursement e = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"),
						rs.getDouble("reimb_amount"),
						rs.getString("reimb_submited"),
						rs.getString("reimb_resolved"),
						rs.getString("reimb_description"),
						rs.getInt("reimb_receipt"),
						rs.getInt("reimb_type_id")
						);

				reimbList.add(e); //populate the ArrayList with each new Reimbursement object
			} // end while
				
			return reimbList;
		} catch (SQLException e) {
			System.out.println("Something went wrong selecting users!");
			e.printStackTrace();
		}

        return Collections.emptyList();
    }
//----------------------------------------------
    public List<Reimbursement> getAll() {
		try(Connection conn = ConnectionFactory.getConnection()){ 
			//Initialize an empty ResultSet object that will store the results of our SQL query
			ResultSet rs = null;
			//write the query that we want to send to the database, and assign it to a String
			String sql = "SELECT * FROM ers_reimbursment ;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();   //statement.executeQuery(sql);
			System.out.println(" execute query..");
			//create an empty ArrayList to be filled with the data from the database
			List<Reimbursement> reimbList = new ArrayList<>();
			//while there are results in the resultset...
			while(rs.next()) {
				
				//Use the all args constructor to create a new Employee object from each returned row from the DB
				Reimbursement e = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"),
						rs.getDouble("reimb_amount"),
						rs.getString("reimb_submited"),
						rs.getString("reimb_resolved"),
						rs.getString("reimb_description"),
						rs.getInt("reimb_receipt"),
						rs.getInt("reimb_type_id")
						);
				
				//and populate the ArrayList with each new User object
				reimbList.add(e); //e is the new User object we created above
		
			}
						
			return reimbList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong selecting users!");
			e.printStackTrace();
		}

		return null;   	

       //return Collections.emptyList();
    }


//----------------------------------------------    
    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) {
    	// added by aguzman
    	try(Connection conn = ConnectionFactory.getConnection()){
			
		String sql = "Update ers_reimbursment  SET reimb_resolved = Now(), reimb_resolver = ?, reimb_status_id = ?"+
		             " WHERE reimb_status_id = ?;"; 
		
		PreparedStatement ps = conn.prepareStatement(sql); //PreparedStatements for SQL  
		int pstat = Status.PENDING.ordinal();
		//System.out.println("PENDING IS...." + pstat);
		//System.out.println("DENIED IS....."+ Status.DENIED.ordinal());
		//System.out.println("APPROVED IS....."+ Status.APPROVED.ordinal());
		ps.setInt(1, unprocessedReimbursement.getNresolver());
		ps.setInt(2, unprocessedReimbursement.getNstatus());
		ps.setInt(3, pstat);
		
		ps.executeUpdate(); 
		//send confirmation to the console if successfully.
		System.out.println("Reimbursment  Updated Successfuly");
		
		
		
	} catch(SQLException e) {
		System.out.println("Reimbursement Updated failed...");
		e.printStackTrace();
	}
   //end added by aguzman
    	return null;
    }
}
