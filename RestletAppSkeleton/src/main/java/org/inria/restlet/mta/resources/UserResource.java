package org.inria.restlet.mta.resources;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.Tweet;
import org.inria.restlet.mta.internals.User;
import org.restlet.resource.ServerResource;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;

/**
 * 
 * Resource exposing a user.
 * 
 * @author msimonin
 * @author ctedeschi
 * 
 */
public class UserResource extends ServerResource {

	/** Backend. */
	private Backend backend_;

	/** Utilisateur géré par cette resource. */
	private User user_;

	/**
	 * Constructor. Call for every single user request.
	 */
	public UserResource() {
		backend_ = (Backend) getApplication().getContext().getAttributes()
				.get("backend");
	}

	@Get("json")
	public Representation getUser() throws Exception {
		String userIdString = (String) getRequest().getAttributes().get(
				"userId");
		int userId = Integer.valueOf(userIdString);
		user_ = backend_.getDatabase().getUser(userId);

		JSONObject userObject = new JSONObject();
		userObject.put("name", user_.getName());
		userObject.put("age", user_.getAge());
		userObject.put("id", user_.getId());
		
		
		for (Tweet tweet : user_.getTweets()) {
			userObject.put("tweet_id" + user_.getTweets().indexOf(tweet), tweet.getDescription());
        }

		return new JsonRepresentation(userObject);
	}

	@Delete("json")
	public Representation deleteUser() throws Exception {
		String userIdString = (String) getRequest().getAttributes().get(
				"userId");
		// Delete the user
		User user = backend_.getDatabase().deleteUser(Integer.parseInt(userIdString));

		// generate result
		JSONObject resultObject = new JSONObject();
		resultObject.put("name", user.getName());
		resultObject.put("age", user.getAge());
		resultObject.put("id", user.getId());
		
		JsonRepresentation result = new JsonRepresentation(resultObject);

		return result;

	}

}
