package com.revature.models;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Date;

import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.UserDAO;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class UserSubMenu {

	public void DisplayUserSubMenu(int id) {

		ReimbursementDAO eDAO = new ReimbursementDAO();
		ReimbursementService es = new ReimbursementService();
		
		boolean displayUsersubMenu = true; //we are going to use this to toggle whether the menu continues after user input
		Scanner scanu = new Scanner(System.in); 
	
		//Display the main menu as long as the displayMainMenu boolean == true
		while(displayUsersubMenu) { 
			System.out.println("-------------------------------------------------------");
			System.out.println("--              Select one Option                    --");
			System.out.println("-------------------------------------------------------");
			//User Sub  Menu options
			System.out.println("1 -> View One Past Tickect");
			System.out.println("2 -> Add Reimbursement request");
			System.out.println("3 -> Return to Main Menu");
			
			//parse user input after user choose a menu option, and put it into a String variable
			String input = scanu.nextLine();
			
			switch(input) {
			
			case "1": {
				System.out.println("Loading View Past Tickect.");
				System.out.println("Enter the ticket number...");
				int idre = scanu.nextInt();
				scanu.nextLine();
				List<Reimbursement> reimb = eDAO.getByIdUid(idre, id);
				 for(Reimbursement e : reimb) {
					 System.out.println(e);
				 }
				System.out.println("******* Detail of reimbursemet *****");
				System.out.println("Number of Request.......... "+ reimb.get(0).getId());
				System.out.println("Amount .................... $"+ reimb.get(0).getAmount());
				System.out.println("Detail of request........... "+ reimb.get(0).getDreimbur() );
				System.out.println("Date of Request............. "+ reimb.get(0).getFsubmited());
				int nstat = reimb.get(0).getNstatus();
				switch(nstat) {
				case 1:
					System.out.println("Estatus.................... "+ Status.APPROVED);
					System.out.println("Date of Aprovated.......... "+ reimb.get(0).getFresolver());
					break;
				case 2:	
					System.out.println("Estatus.................... "+ Status.DENIED);
					break;
					
				case 3:
					System.out.println("Estatus.................... "+ Status.PENDING);
					break;
				}

				scanu.nextLine();
				break; 
			}
			case "2" :{
				System.out.println("Add Reimbursement request");
				System.out.println("Number of Receipt...............................");
				int nreciept = scanu.nextInt();
				scanu.nextLine();
				System.out.println("Description of Reimbursement...................,");
				String desr = scanu.nextLine();
				System.out.println("Amount......................................,...");
				Double amoun = scanu.nextDouble();
				scanu.nextLine();
				System.out.println("Type: 1-Hotel   2-Parking  3-Transportation.....");
				int typer = scanu.nextInt();
				
				Date freq = new Date();
				String sfrequ = freq.toString();
				Reimbursement NewReimbursement = new Reimbursement(0, 3,id, 0, amoun,sfrequ,null, desr, nreciept,typer);
				es.addReimbursement(NewReimbursement);
				
				break;
			}
			case "3" :{
				System.out.println("Exit Sub Menu Users");
				displayUsersubMenu = false;
				//scanu.close();
				break;
			}
			
			//this default block will catch any user inputs that don't match a valid menu option
			default: {
				System.out.println("Invalid selection... try again :'( ");
				break;
			}
			
			
			} //End of switch

		} //End of while loop

	}// End DisplayUserSubMenu Method
	
	public void DisplayfinaSubMenu(int idf) {
		
		ReimbursementDAO eDAO = new ReimbursementDAO();
		ReimbursementService es = new ReimbursementService();
		
		boolean displayUsersubMenu = true; 
		Scanner scanf = new Scanner(System.in); 
	
		//Display the main menu as long as the displayMainMenu boolean == true
		while(displayUsersubMenu) { 
			System.out.println("-------------------------------------------------------");
			System.out.println("--              Select one Option                    --");
			System.out.println("-------------------------------------------------------");
			//User Sub  Menu options
			System.out.println("1 -> View One Past Tickect");
			System.out.println("2 -> Add Reimbursement request");
			System.out.println("3 -> Aprobe All reimbursment");
			System.out.println("4 -> Exit from Manager Finacial Submenu");
			
			//parse user input after user choose a menu option, and put it into a String variable
			String input = scanf.nextLine();
			
			switch(input) {
			
			case "1": {
				
				System.out.println("Loading View Past Tickect.");
				System.out.println("Enter the ticket number...");
				int idre = scanf.nextInt();
				scanf.nextLine();
				List<Reimbursement> reimb = eDAO.getByIdUid(idre, idf);
				 for(Reimbursement e : reimb) {
					 System.out.println(e);
				 }
				System.out.println("******* Detail of reimbursemet *****");
				System.out.println("Number of Request.......... "+ reimb.get(0).getId());
				System.out.println("Amount .................... $"+ reimb.get(0).getAmount());
				System.out.println("Detail of request............"+ reimb.get(0).getDreimbur() );
				System.out.println("Date of Request............ "+ reimb.get(0).getFsubmited());
				int nstat = reimb.get(0).getNstatus();
				switch(nstat) {
				case 1:
					System.out.println("Estatus.................... "+ Status.APPROVED);
					System.out.println("Date of Aprovated.......... "+ reimb.get(0).getFresolver());
					break;
				case 2:	
					System.out.println("Estatus.................... "+ Status.DENIED);
					break;
					
				case 3:
					System.out.println("Estatus.................... "+ Status.PENDING);
					break;
				}

				scanf.nextLine();
				break; 	
			}
			
			case "2" :{
				System.out.println("Load Add Reimbursement request");
				System.out.println("Add Reimbursement request");
				System.out.println("Number of Receipt...............................");
				int nreciept = scanf.nextInt();
				scanf.nextLine();
				System.out.println("Description of Reimbursement...................,");
				String desr = scanf.nextLine();
				System.out.println("Amount......................................,...");
				Double amoun = scanf.nextDouble();
				scanf.nextLine();
				System.out.println("Type: 1-Hotel   2-Parking  3-Transportation.....");
				int typer = scanf.nextInt();
				
				Date freq = new Date();
				String sfrequ = freq.toString();
				Reimbursement NewReimbursement = new Reimbursement(0, 3,idf, 0, amoun,sfrequ,null, desr, nreciept,typer);
				es.addReimbursement(NewReimbursement);
				
				break;
			}
			case "3" :{
				int id = 0; int nstatus; int nauthor = 0 ; int nresolver; double amount = 0;
	              //String fsubmited; String Fresolver; String Dreimbur; int receipt; int ntype;
	              nstatus=3;
	              nresolver=idf;
				System.out.println("Aprobe all Reimbursement Pending");
				Reimbursement UpdateReimbursement = new Reimbursement(id, nstatus,nauthor, nresolver, amount, "", "", "",0,0);
				es.updReimbursement(UpdateReimbursement);

				break;
			}
			case "4" :{
				System.out.println("Exit Sub Menu Users");
				
				displayUsersubMenu = false;
				break;
			}
			
			//this default block will catch any user inputs that don't match a valid menu option
			default: {
				System.out.println("Invalid selection... try again :'( ");
				break;
			}
			
			
			} //End of switch

		} //End of while loop	
	}
	
}  // End UserSubMenu class
