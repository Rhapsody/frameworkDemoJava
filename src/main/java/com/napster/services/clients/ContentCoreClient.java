package com.napster.services.clients;

import com.napster.services.api.ContentCoreReleaseApi;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ContentCoreClient {
	public static RequestSpecification releaseApi = ContentCoreReleaseApi.getApi();

	public static RequestSpecification getReleaseApi(String releaseId) {
		return releaseApi
				.pathParam(ContentCoreReleaseApi.releaseId, releaseId);
	}

	public static Response getRelease(String releaseId) {
		return releaseApi
				.pathParam(ContentCoreReleaseApi.releaseId, releaseId)
				.get();
	}
}
