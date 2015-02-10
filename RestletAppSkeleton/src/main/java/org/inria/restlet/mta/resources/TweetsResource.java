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
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class TweetsResource extends ServerResource {

	/** Backend. */
    private Backend backend_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public TweetsResource()
    {
        super();
        backend_ = (Backend) getApplication().getContext().getAttributes()
            .get("backend");
    }
    
    @Get("json")
    public Representation getTweetsByUser() throws JSONException
    {
    	String userIdString = (String) getRequest().getAttributes().get("userId");
        User user = backend_.getDatabase().getUser(Integer.parseInt(userIdString));
        Collection<JSONObject> jsonTweets = new ArrayList<JSONObject>();

        for (Tweet tweet : user.getTweets()) {
        	JSONObject current = new JSONObject();
            current.put("description", tweet.getDescription());
            jsonTweets.add(current);
        }
        
        JSONArray jsonArray = new JSONArray(jsonTweets);
        return new JsonRepresentation(jsonArray);
    }
    
    
    @Post("json")
    public Representation createTweet(JsonRepresentation representation)
        throws Exception
    {
    	String userIdString = (String) getRequest().getAttributes().get("userId");
        User user = backend_.getDatabase().getUser(Integer.parseInt(userIdString));
    	
        JSONObject object = representation.getJsonObject();
        String description = object.getString("description");

        Tweet tweet = new Tweet(description);
        // Save the tweet
        user.getTweets().add(tweet);

        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("description", tweet.getDescription());
        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }
    

}
