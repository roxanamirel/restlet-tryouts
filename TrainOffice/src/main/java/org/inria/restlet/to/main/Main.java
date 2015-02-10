package org.inria.restlet.to.main;

import org.inria.restlet.to.application.TicketOfficeApplication;
import org.inria.restlet.to.backend.Backend;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.data.Protocol;

public class Main {
	/** Hide constructor. */
	private Main() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            Les arguments de la ligne de commande.
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// Create a component
		Component component = new Component();
		Context context = component.getContext().createChildContext();
		component.getServers().add(Protocol.HTTP, 8124);

		// Create an application
		Application application = new TicketOfficeApplication(context);

		// Add the backend into component's context
		Backend backend = new Backend();
		context.getAttributes().put("backend", backend);
		component.getDefaultHost().attach(application);

		// Start the component
		component.start();
	}
}
