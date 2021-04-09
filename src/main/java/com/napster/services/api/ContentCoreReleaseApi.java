package com.napster.services.api;

import com.napster.properties.PropertiesManager;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class ContentCoreReleaseApi {
	public static final String releaseId = "releaseId";

	public static RequestSpecification getApi() {
		return RestAssured.given()
				.baseUri(PropertiesManager.getProperty("contentcore.url"))
				.port(Integer.parseInt(PropertiesManager.getProperty("contentcore.port")))
				.basePath("/release-core-service/api/releases/{releaseId}");
	}
}
