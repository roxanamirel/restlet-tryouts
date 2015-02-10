package org.inria.restlet.to.backend;

import org.inria.restlet.to.database.Database;
import org.inria.restlet.to.database.impl.InMemoryDatabase;

public class Backend {
	/** Database. */
	private Database database_;

	public Backend() {
		database_ = new InMemoryDatabase();
	}

	public Database getDatabase() {
		return database_;
	}

}
