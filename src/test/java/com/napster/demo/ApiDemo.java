package com.napster.demo;

import com.napster.services.api.ContentCoreReleaseApi;
import com.napster.services.clients.ContentCoreClient;
import com.napster.services.clients.ContentServerClient;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiDemo {

	@Test
	void getReleaseFromContentcoreServiceUsingFullRestAssured() {
		given(ContentCoreClient.releaseApi)
			.pathParam(ContentCoreReleaseApi.releaseId, "187768943")
		.when().get().then()
			.statusCode(200)
			.assertThat().body("response.hiRes", equalTo(false));
	}

	@Test
	void getReleaseFromContentcoreService() {
		ContentCoreClient.getRelease( "187768943")
			.then()
			.statusCode(200)
			.assertThat().body("response.hiRes", equalTo(false));
	}

	@Test
	void getInvalidReleaseFromContentcoreService() {
		ContentCoreClient.getRelease("187768943Z")
			.then()
			.statusCode(400)
			.log().all();
	}

	@Test
	void getReleaseFromContentserverService() {
		ContentServerClient.getRelease("101", "Alb.187768943")
			.then()
			.statusCode(200)
			.assertThat().body("album.hiRes", equalTo("false"))
			.log().body();
	}

	@Test
	void getReleaseFromContentserverServiceWithQueryParameters() {
		ContentServerClient.getReleaseApi("101", "Alb.187768943")
			.queryParam("filterRightsKey", 0)
			.queryParam("devOptions", "skipFastLane")
			.get().then()
			.statusCode(200)
			.assertThat().body("album.hiRes", equalTo("false"));
	}

}
