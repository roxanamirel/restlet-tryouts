package org.inria.restlet.to.internals;


public class Destination {
	
	private int id;
	private String description;
	private int noOfTickets;
	
	public Destination(int id, String description, int noOfTickets) {
		this.id = id;
		this.description = description;
		this.noOfTickets = noOfTickets;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNoOfTickets() {
		return noOfTickets;
	}

	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	public int getId() {
		return id;
	}
}
