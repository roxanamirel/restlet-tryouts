package org.inria.restlet.mta.internals;

public class Tweet {
	
	private String description;
	
	public Tweet(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Tweet(String description, int id) {
		super();
		this.description = description;
	}
	
}
