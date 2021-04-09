package com.napster.persistence.core;

public enum DbConnection {
	cmsm("mcp.cmsm.connection.url", "oracle.driver"),
	ctr("ctr.connection.url", "oracle.driver")
	;

	private String propertyString;
	private String propertyDriverString;

	private DbConnection(String propertyString, String propertyDriverString) {
		this.propertyString = propertyString;
		this.propertyDriverString = propertyDriverString;
	}

	String getPropertyString() {
		return this.propertyString;
	}

	String getPropertyDriverString() {
		return propertyDriverString;
	}

}
