package com.napster.services.api;

import com.napster.properties.PropertiesManager;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class ContentServerReleaseApi {
	public static final String catalogId = "catalogId";
	public static final String releaseId = "releaseId";

	public static RequestSpecification getApi() {
		return RestAssured.given()
				.baseUri(PropertiesManager.getProperty("contentserver.url"))
				.port(Integer.parseInt(PropertiesManager.getProperty("contentserver.port")))
				.basePath("/rhapsodydirectmetadata/contentserver/v2/catalogs/{catalogId}/albums/{releaseId}");
	}
}
