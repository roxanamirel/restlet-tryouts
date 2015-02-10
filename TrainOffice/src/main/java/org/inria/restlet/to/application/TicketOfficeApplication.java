package org.inria.restlet.to.application;

import org.inria.restlet.to.resources.DestinationResource;
import org.inria.restlet.to.resources.DestinationsResource;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class TicketOfficeApplication extends Application {

	public TicketOfficeApplication(Context context) {
		super(context);
	}

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach("/destinations", DestinationsResource.class);
		router.attach("/destinations/{destId}", DestinationResource.class);

		return router;
	}
}
