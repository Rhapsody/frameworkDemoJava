package com.napster.persistence.core;

import com.napster.properties.PropertiesManager;

public enum DbUser {
	/*
	CMSM DB
	 */
	album(DbConnection.cmsm, "album", "album"),
	album_data(DbConnection.cmsm, "album_data", "album_data"),
	app_build(DbConnection.cmsm, "app_build", "app_build"),
	artist(DbConnection.cmsm, "artist", "artist"),
	artist_data(DbConnection.cmsm, "artist_data", "artist_data"),
	availability(DbConnection.cmsm, "availability", "availability"),
	cms(DbConnection.cmsm, "cms", "cms"),
	cms_lookup(DbConnection.cmsm, "cms_lookup", "cms_lookup"),
	cms_web(DbConnection.cmsm, "cms_web", "cms_web"),
	content_encoding(DbConnection.cmsm, "content_encoding", "content_encoding"),
	encoding(DbConnection.cmsm, "encoding", "encoding"),
	loader(DbConnection.cmsm, "loader", "loader"),
	lookup(DbConnection.cmsm, "lookup", "lookup"),
	media(DbConnection.cmsm, "media", "media"),
	publisher(DbConnection.cmsm, "publisher", "publisher"),
	radio(DbConnection.cmsm, "radio", "radio"),
	rights(DbConnection.cmsm, "rights", "rights"),
	stage_prod(DbConnection.cmsm, "stage_prod", "stage_prod"),
	track(DbConnection.cmsm, "track", "track"),
	utility(DbConnection.cmsm, "utility", "utility"),
	workflow(DbConnection.cmsm, "workflow", "workflow"),
	qa_user(DbConnection.cmsm, "qa_user", "qa_user"),

	/*
	CTR DB
	 */
	partner(DbConnection.ctr, "partner", "partner"),
	ri_tools(DbConnection.ctr, "ri_tools", "ri_tools")
	;

	private DbConnection db;
	private String user;
	private String password;

	private DbUser(DbConnection db, String user, String password) {
		this.db = db;
		this.user = user;
		this.password = password;
	}

	String getDbConnection() {
		return PropertiesManager.getProperty(this.db.getPropertyString());
	}

	String getDbDriver() {
		return PropertiesManager.getProperty(this.db.getPropertyDriverString());
	}

	String getUser() {
		return this.user;
	}

	String getPassword() {
		return this.password;
	}
}
