package org.inria.restlet.to.client;

import java.io.IOException;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;

public class TrainClient implements Runnable {
	private static final String URL = "http://localhost:8124/destinations";
	private static final int MAX_DEST = 4;
	private static final int MAX_TICKET = 20;
	public static final int DELETED_STATION_ID = 1;

	@Override
	public void run() {
		Random rand = new Random();
		int destId = Math.abs(rand.nextInt()) % MAX_DEST;
		while (destId == DELETED_STATION_ID)
		{
			destId = Math.abs(rand.nextInt()) % MAX_DEST;
		}
		int ticketsAmount = Math.abs(rand.nextInt()) % MAX_TICKET;

		ClientResource trainClient = new ClientResource(URL + "/" + destId);

		JSONObject jsonDest = new JSONObject();

		try {
			jsonDest.put("amount", ticketsAmount);
			JsonRepresentation rep = new JsonRepresentation(jsonDest);
			trainClient.get().write(System.out);
			// become available
			System.out.println("\nTRAIN with destination id = " + destId
					+ " has added " + ticketsAmount + " tickets");
			trainClient.post(rep).write(System.out);
			System.out.println();

		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}

	}

}
