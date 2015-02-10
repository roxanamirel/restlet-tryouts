package org.inria.restlet.to.client;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;

public class RestletClient {
	private RestletClient() {
		super();
	}

	private void work() throws Exception {
		String URL = "http://localhost:8124/destinations";
		ClientResource client = new ClientResource(URL);

		System.out.println("Getting all destinations");
		client.get().write(System.out);
		System.out.println();

		System.out.println("\nGetting a specific destination");
		ClientResource client2 = new ClientResource(URL + "/0");
		client2.get().write(System.out);

		
		System.out.println("\n\nDecreasing ticket number");
		// create the data for the request
		JSONObject jsonDest = new JSONObject();
		jsonDest.put("amount", -2);
		JsonRepresentation rep = new JsonRepresentation(jsonDest);
		// send a POST requests to decrease tickets
		System.out.println("\n Modified destination:");
		client2.post(rep).write(System.out);
		

		System.out.println("\n\nIncreasing ticket number");

		JSONObject jsonDest2 = new JSONObject();
		jsonDest2.put("amount", 2);
		JsonRepresentation rep2 = new JsonRepresentation(jsonDest2);

		// send a POST requests to increase tickets
		System.out.println("\n Modified destination:");
		client2.post(rep2).write(System.out);
		
		System.out.println("\n\nDeleting destination with id = " + TrainClient.DELETED_STATION_ID);
		ClientResource client3 = new ClientResource(URL + "/" + TrainClient.DELETED_STATION_ID);
		client3.delete().write(System.out);
		
		System.out.println("\n\nGetting all destinations");
		client.get().write(System.out);
		System.out.println();
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            Les arguments de la ligne de commande.
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		RestletClient m = new RestletClient();
		m.work();
		System.out.println("\n\nINCOMING TRAINS!!!\n");
		for (int i = 0; i <= 5; i++) {
			TrainClient trainClient = new TrainClient();
			trainClient.run();
		}

	}

}
