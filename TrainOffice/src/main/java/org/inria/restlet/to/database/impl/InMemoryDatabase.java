package org.inria.restlet.to.database.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.inria.restlet.to.database.Database;
import org.inria.restlet.to.internals.Destination;

public class InMemoryDatabase implements Database {

	private Map<Integer, Destination> destinations;

	public InMemoryDatabase() {
		this.destinations = new HashMap<Integer, Destination>();
		insertData(destinations);
	}

	@Override
	public Destination getDestination(int destId) {
		return destinations.get(destId);
	}
	
	@Override
	public Collection<Destination> getDestinations() {
		return destinations.values();
	}

	@Override
	public Destination deleteDestination(int id) {
		return destinations.remove(id);
	}

	@Override
	public synchronized int updateTicket(int destinationId, int amount) {
		Destination destination = destinations.get(destinationId);
		destination.setNoOfTickets(destination.getNoOfTickets() + amount);

		return destination.getNoOfTickets();
	}

	private void insertData(Map<Integer, Destination> destinations) {
		destinations.put(0, new Destination(0, "Rennes", 10));
		destinations.put(1, new Destination(1, "Paris", 5));
		destinations.put(2, new Destination(2, "Cluj-Napoca", 22));
		destinations.put(3, new Destination(3, "Grenoble", 99));
	}


}
