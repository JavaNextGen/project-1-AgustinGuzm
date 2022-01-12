package com.revature.models;


import java.time.LocalDate; // import the LocalDate class
/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 * </ul>
 *
 */

public class Reimbursement extends AbstractReimbursement {
	String fsubmited;
	String Fresolver;
	String Dreimbur;
	int receipt;
	int nauthor;
	int nresolver;
	int nstatus;
	int ntype;
    public Reimbursement() {
        super();
        LocalDate datrem = LocalDate.now(); // Create a date object
	    System.out.println(datrem); // Display the current date
    }
//---------------------------------------
//---------------------------------------    
    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */
    public Reimbursement(int id, Status status, User author, User resolver, double amount) {
        super(id, status, author, resolver, amount);
        this.setId(id);
        this.setStatus(status);
        this.setAuthor(author);
        this.setResolver(resolver);
        this.setAmount(amount);
    }
    
    public Reimbursement(int id, int nstatus, int nauthor, int nresolver, double amount,
    		              String fsubmited, String Fresolver, String Dreimbur, int receipt, int ntype) {
        //String UsrSubmi,String Finresol,
    	//super(id, status, author, resolver, amount);
        this.setId(id);
        this.setNstatus(nstatus);
        this.setNauthor(nauthor);
        this.setNresolver(nresolver);
        this.setAmount(amount);
        this.setFsubmited(fsubmited);
        this.setFresolver(Fresolver);
        this.setDreimbur(Dreimbur);
        this.setReceipt(receipt);
        this.setNtype(ntype);
    }
	public int getNtype() {
		return ntype;
	}
	public void setNtype(int ntype) {
		this.ntype = ntype;
	}
	public int getNauthor() {
		return nauthor;
	}
	public void setNauthor(int nauthor) {
		this.nauthor = nauthor;
	}
	public int getNresolver() {
		return nresolver;
	}
	public void setNresolver(int nresolver) {
		this.nresolver = nresolver;
	}
	public int getNstatus() {
		return nstatus;
	}
	public void setNstatus(int nstatus) {
		this.nstatus = nstatus;
	}
	public String getFsubmited() {
		return fsubmited;
	}
	public void setFsubmited(String fsubmited) {
		this.fsubmited = fsubmited;
	}
	public String getFresolver() {
		return Fresolver;
	}
	public void setFresolver(String fresolver) {
		Fresolver = fresolver;
	}
	public String getDreimbur() {
		return Dreimbur;
	}
	public void setDreimbur(String dreimbur) {
		Dreimbur = dreimbur;
	}
	public int getReceipt() {
		return receipt;
	}
	public void setReceipt(int receipt) {
		this.receipt = receipt;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((Dreimbur == null) ? 0 : Dreimbur.hashCode());
		result = prime * result + ((Fresolver == null) ? 0 : Fresolver.hashCode());
		result = prime * result + ((fsubmited == null) ? 0 : fsubmited.hashCode());
		result = prime * result + nauthor;
		result = prime * result + nresolver;
		result = prime * result + nstatus;
		result = prime * result + ntype;
		result = prime * result + receipt;
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
		Reimbursement other = (Reimbursement) obj;
		if (Dreimbur == null) {
			if (other.Dreimbur != null)
				return false;
		} else if (!Dreimbur.equals(other.Dreimbur))
			return false;
		if (Fresolver == null) {
			if (other.Fresolver != null)
				return false;
		} else if (!Fresolver.equals(other.Fresolver))
			return false;
		if (fsubmited == null) {
			if (other.fsubmited != null)
				return false;
		} else if (!fsubmited.equals(other.fsubmited))
			return false;
		if (nauthor != other.nauthor)
			return false;
		if (nresolver != other.nresolver)
			return false;
		if (nstatus != other.nstatus)
			return false;
		if (ntype != other.ntype)
			return false;
		if (receipt != other.receipt)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursement [fsubmited=" + fsubmited + ", Fresolver=" + Fresolver + ", Dreimbur=" + Dreimbur
				+ ", receipt=" + receipt + ", nauthor=" + nauthor + ", nresolver=" + nresolver + ", nstatus=" + nstatus
				+ ", ntype=" + ntype + ", getNtype()=" + getNtype() + ", getNauthor()=" + getNauthor()
				+ ", getNresolver()=" + getNresolver() + ", getNstatus()=" + getNstatus() + ", getFsubmited()="
				+ getFsubmited() + ", getFresolver()=" + getFresolver() + ", getDreimbur()=" + getDreimbur()
				+ ", getReceipt()=" + getReceipt() + ", hashCode()=" + hashCode() + ", getId()=" + getId()
				+ ", getStatus()=" + getStatus() + ", getAuthor()=" + getAuthor() + ", getResolver()=" + getResolver()
				+ ", getAmount()=" + getAmount() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ "]";
	} 
 
}
