package com.revature.models;
import java.util.Scanner;
/**
 * This concrete User class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>First Name</li>
 *     <li>Last Name</li>
 *     <li>Email</li>
 *     <li>Phone Number</li>
 *     <li>Address</li>
 * </ul>
 *
 */
public class User extends AbstractUser {

     String FirstName;//
     String LastName;//
     String Email;//
     int nrole;
    
    public User() {
        super();    
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractUser} class.
     * If other fields are needed, please create additional constructors.
     */
    
    
    
    public User(int id, String username, String password, Role role) {
        super(id, username, password, role);
    	this.setId(id);
    	this.setUsername(username);
    	this.setPassword(password);
    	//this.setNrole(role);
    }
    
    public User(int id, String username, String password, String UsrFname, String UsrLname, String UsrEmail, int nrole) {
    	super();
    	//super(id, username, password, role);
    	
    	this.setId(id);
    	this.setUsername(username);
    	this.setPassword(password);
    	this.setFirstName(UsrFname);
    	this.setLastName(UsrLname);
    	this.setEmail(UsrEmail);
    	this.setNrole(nrole);	
    }


	//--------------------------------------------------Setters and Getters
	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
public int getNrole() {
		return nrole;
	}

	public void setNrole(int nrole) {
		this.nrole = nrole;
	}

	//------

	
	//--------------------------------------------------toString
	
	@Override
	public String toString() {
		return "User [FirstName=" + FirstName + ", LastName=" + LastName + ", Email=" + Email + ", nrole=" + nrole
				+ ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getEmail()="
				+ getEmail() + ", getNrole()=" + getNrole() + ", hashCode()=" + hashCode() + ", getId()=" + getId()
				+ ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword() + ", getRole()=" + getRole()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}
	
@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((Email == null) ? 0 : Email.hashCode());
		result = prime * result + ((FirstName == null) ? 0 : FirstName.hashCode());
		result = prime * result + ((LastName == null) ? 0 : LastName.hashCode());
		result = prime * result + nrole;
		return result;
	}

@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (Email == null) {
			if (other.Email != null)
				return false;
		} else if (!Email.equals(other.Email))
			return false;
		if (FirstName == null) {
			if (other.FirstName != null)
				return false;
		} else if (!FirstName.equals(other.FirstName))
			return false;
		if (LastName == null) {
			if (other.LastName != null)
				return false;
		} else if (!LastName.equals(other.LastName))
			return false;
		if (nrole != other.nrole)
			return false;
		return true;
	}
    
}
