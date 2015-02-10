package org.inria.restlet.to.resources;

import java.util.ArrayList;
import java.util.Collection;

import org.inria.restlet.to.backend.Backend;
import org.inria.restlet.to.internals.Destination;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class DestinationsResource extends ServerResource {

	/** Backend. */
	private Backend backend_;

	/**
	 * Constructor. Call for every single destination request.
	 */
	public DestinationsResource() {
		backend_ = (Backend) getApplication().getContext().getAttributes()
				.get("backend");
	}

	/**
	 * 
	 * Returns the list of all the users
	 * 
	 * @return JSON representation of the users
	 * @throws JSONException
	 */
	@Get("json")
	public Representation getDestinations() throws JSONException {
		Collection<Destination> destinations = backend_.getDatabase()
				.getDestinations();
		Collection<JSONObject> jsonDestinations = new ArrayList<JSONObject>();

		for (Destination destination : destinations) {
			JSONObject current = new JSONObject();
			current.put("id", destination.getId());
			current.put("description", destination.getDescription());
			current.put("no_of_tickets", destination.getNoOfTickets());
			current.put("url", getReference() + "/" + destination.getId());

			jsonDestinations.add(current);
		}
		
		JSONArray jsonArray = new JSONArray(jsonDestinations);
		return new JsonRepresentation(jsonArray);
	}
	
}
