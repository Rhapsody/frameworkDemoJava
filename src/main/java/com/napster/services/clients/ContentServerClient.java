package com.napster.services.clients;

import com.napster.services.api.ContentServerReleaseApi;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ContentServerClient {
	public static RequestSpecification releaseApi = ContentServerReleaseApi.getApi();

	public static RequestSpecification getReleaseApi(String catalogId, String releaseId) {
		return releaseApi
				.pathParam(ContentServerReleaseApi.catalogId, catalogId)
				.pathParam(ContentServerReleaseApi.releaseId, releaseId);
	}

	public static Response getRelease(String catalogId, String releaseId) {
		return releaseApi
				.pathParam(ContentServerReleaseApi.catalogId, catalogId)
				.pathParam(ContentServerReleaseApi.releaseId, releaseId)
				.get();
	}
}
