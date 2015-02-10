package org.inria.restlet.to.resources;

import org.inria.restlet.to.backend.Backend;
import org.inria.restlet.to.internals.Destination;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class DestinationResource extends ServerResource {

	/** Backend. */
	private Backend backend_;

	/**
	 * Constructor. Call for every single destination request.
	 */
	public DestinationResource() {
		backend_ = (Backend) getApplication().getContext().getAttributes()
				.get("backend");
	}

	@Get("json")
	public Representation getDestination() throws Exception {
		String destId = (String) getRequest().getAttributes().get("destId");
		int id = Integer.valueOf(destId);
		Destination destination = backend_.getDatabase().getDestination(id);

		JSONObject destObject = new JSONObject();
		destObject.put("description", destination.getDescription());
		destObject.put("no_of_ticket", destination.getNoOfTickets());
		destObject.put("id", destination.getId());

		return new JsonRepresentation(destObject);
	}
	@Delete("json")
	public Representation deleteDestination() throws Exception {
		String destId = (String) getRequest().getAttributes().get("destId");
		int id = Integer.valueOf(destId);
		Destination destination = backend_.getDatabase().deleteDestination(id);

		JSONObject destObject = new JSONObject();
		destObject.put("description", destination.getDescription());
		destObject.put("no_of_ticket", destination.getNoOfTickets());
		destObject.put("id", destination.getId());

		return new JsonRepresentation(destObject);
	}
	@Post("json")
	public Representation updateTicket(JsonRepresentation representation) throws Exception {
		String destId = (String) getRequest().getAttributes().get("destId");
		int id = Integer.valueOf(destId);
		Destination destination = backend_.getDatabase().getDestination(id);
		JSONObject object = representation.getJsonObject();
        String amountS = object.getString("amount");
        int amount = Integer.parseInt(amountS);
		int ticketsLeft = backend_.getDatabase().updateTicket(id, amount);

		JSONObject destObject = new JSONObject();
		destObject.put("tickets_left",ticketsLeft);
		destObject.put("description", destination.getDescription());
		destObject.put("id", destination.getId());

		return new JsonRepresentation(destObject);
	}
}
