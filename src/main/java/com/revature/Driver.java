package com.revature;
import com.revature.models.MainMenu;


public class Driver {

    public static void main(String[] args) {
		//Instantiate a menu object
		MainMenu Mainmenu = new MainMenu();

		//Use the MainMenu displayMainMenu() method to give the console mode
		//Mainmenu.displayMainMenu();
		
		// Use the MainMenu runasaservice() method to give the service mode
		Mainmenu.runasaservice();
    		
    }
}
