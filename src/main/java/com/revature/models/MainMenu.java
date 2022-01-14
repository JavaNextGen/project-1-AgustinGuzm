package com.revature.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.revature.cotroller.AuthController;
import com.revature.cotroller.ReimbursementControler;
import com.revature.cotroller.UserController;
import com.revature.repositories.UserDAO;
import com.revature.services.UserService;
import com.revature.util.ConnectionFactory;


import io.javalin.Javalin;


public class MainMenu {

		UserDAO eDAO = new UserDAO();
		UserService es = new UserService();
		AuthController ac = new AuthController();
	//-----------Method to run as a console--------------------
	//All of the Main menu display options and control flow are contained within this method
	public void displayMainMenu() {
		Role role = null;
		//Instantiate a submenu object
		UserSubMenu UserSubmenu = new UserSubMenu();
		
		boolean displayMainMenu = true; //we are going to use this to toggle whether the menu continues after user input
	
		Scanner scann = new Scanner(System.in); 

		//Header Of output screen
		System.out.println("*******************************************************");
		System.out.println("**                                                   **");
		System.out.println("**              EMPLOYEE REIMBURSMENT SYSTEM         **");
		System.out.println("**                          ERS                      **");
		System.out.println("**                                                   **");
		System.out.println("*******************************************************");

		//Display the main menu as long as the displayMainMenu boolean == true
		while(displayMainMenu) {
			
			System.out.println("User Name...");
			String usernam = scann.nextLine(); // Ask for username
			System.out.println("User Password...");
			String upass = scann.nextLine(); // Ask for password
			
			List<User> Users = eDAO.getUser(usernam,upass);
			
			int nuser = Users.size();
			if (nuser == 1){
				System.out.println("User can continue at submenu..");
				 User pr = Users.get(0);
				 int nruser = pr.nrole;
				 int idusr  =pr.getId();
				 for(User e : Users) {
					 System.out.println(e);
				 }
				System.out.println("the ocurrences  is...."+nuser );
			switch(nruser) {
			case 1: {
				System.out.println("You are an Employee");
				//Use the Menu Class UserSubmenu() method to give the user the menu
				UserSubmenu.DisplayUserSubMenu(idusr);
				break; 
			}
			
			case 2 :{
				System.out.println("You are a Finance Manager");

				//Use the Menu Class UserSubmenu() method to give the Financial Manager the menu
				UserSubmenu.DisplayfinaSubMenu(idusr);
				break;
			}
			
			//this default block will catch any user inputs that don't match a valid role
			default: {
				System.out.println("Invalid Entrance...");
				break;
			}
			
			
			} //end of switch
			}else {
				System.out.println("User ARE NOT enrolled in the app..");
				System.out.println("Do you want Register in ERS (Y/N).");
				String newuser = scann.nextLine(); // Ask for password
				if (newuser.toUpperCase().trim().equals("Y")) {
					System.out.println("Enter your First Name....");
					String uFname = scann.nextLine();
					System.out.println("Enter your Last Name.....");
					String uLname = scann.nextLine();
					System.out.println("Enter your Email Address.");
					String UEmail = scann.nextLine();
					System.out.println("Select [1] to Employee or [2] to Financial Mng.");
					int urole = scann.nextInt();
					scann.nextLine();
					Role trole = role.EMPLOYEE;
					if(urole == 1) {
						trole = role.EMPLOYEE;
					} else {
						trole = role.FINANCE_MANAGER;
					}
					User enrolluser = new User(0, usernam, upass, uFname, uLname, UEmail, urole);
					eDAO.InsertUSer(enrolluser);
				} else {
					System.out.println("Thanks for visit.. See you soon....");
				}
			}
		} //end of while loop
		
		
	} //------end of console method

	//--------------Method to run as a service--------------------------------
	public void runasaservice() {
		UserController cec = new UserController();
		ReimbursementControler  crc = new ReimbursementControler();
		
		try (Connection conn = ConnectionFactory.getConnection()){
			System.out.println("We have the connection ");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Sorry...We can not reach the DB.!");
		}
		
		Javalin app = Javalin.create(
                config -> {
                    config.enableCorsForAllOrigins();//allows
                }
                ).start(3000);
		//==============  User modules ==========================
		app.get("/user", cec.getUserHandler);
		app.get("/user/{e_id}", cec.getUserHandlerp);
		app.get("/user/{e_nm}/{e_ps}", cec.getUserHandler_USrPAs);
		app.post("/user", cec.insertUser);

		//============== reimbursement modules ==================
		app.get("/reimb", crc.getReimbursementHandler_list);
		app.get("/reimb/{e_st}", crc.getReimbursementHandler_stat);
		app.get("/reimb/{e_id}/{e_uid}", crc.getReimbIdUidHandler_RidUid);
		app.post("/reimb", crc.InsertReimbursement);
		app.put("/reimbu", crc.updateReimbursement);

		//====================login ==================
		//handler ending in /login to send and inputted username/password to be validated
		app.post("/login", ac.loginHandler);
		
	} // ------End of runasaservice
}
