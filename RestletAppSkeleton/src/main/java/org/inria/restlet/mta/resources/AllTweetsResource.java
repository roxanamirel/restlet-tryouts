package org.inria.restlet.mta.resources;

import java.util.ArrayList;
import java.util.Collection;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.Tweet;
import org.inria.restlet.mta.internals.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class AllTweetsResource extends ServerResource {

	/** Backend. */
	private Backend backend_;

	public AllTweetsResource() {
        super();
		backend_ = (Backend) getApplication().getContext().getAttributes()
				.get("backend");
	}

	@Get("json")
	public Representation getTweets() throws JSONException {
		Collection<User> users = backend_.getDatabase().getUsers();
		Collection<JSONObject> jsonTweets = new ArrayList<JSONObject>();

		for (User user : users) {
			JSONObject current = new JSONObject();
			for (Tweet tweet : user.getTweets()) {
				current.put("description_" + user.getId()+ "_"
						+ user.getTweets().indexOf(tweet),
						tweet.getDescription());
				jsonTweets.add(current);

			}
			

		}
		JSONArray jsonArray = new JSONArray(jsonTweets);
		return new JsonRepresentation(jsonArray);
	}
}
