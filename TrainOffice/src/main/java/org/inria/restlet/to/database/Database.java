package org.inria.restlet.to.database;

import java.util.Collection;

import org.inria.restlet.to.internals.Destination;

public interface Database {

	Destination getDestination(int destId);

	Collection<Destination> getDestinations();

	Destination deleteDestination(int id);

	int updateTicket(int destinationId, int amount);
}
