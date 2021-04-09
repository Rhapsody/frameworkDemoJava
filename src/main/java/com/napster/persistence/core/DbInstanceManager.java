package com.napster.persistence.core;

import java.util.HashMap;
import java.util.Map;

public class DbInstanceManager {
	private static Map<DbUser, DbInstance> dbInstances;

	public static synchronized DbInstance getInstance(DbUser dbUser) {
		if (dbInstances == null) {
			dbInstances = new HashMap<>();
		}
		if (dbInstances.containsKey(dbUser)) {
			return dbInstances.get(dbUser);
		} else {
			DbInstance dbInstance = new DbInstance(dbUser.getDbConnection(), dbUser.getUser(), dbUser.getPassword(), dbUser.getDbDriver());
			dbInstances.put(dbUser, dbInstance);
			return dbInstance;
		}
	}
}
