package com.napster.demo;

import com.napster.persistence.core.DbInstanceManager;
import com.napster.persistence.core.DbUser;
import com.napster.persistence.domain.oracle.album.Release;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DbDemo {

	@Test
	void getSingleValueDemo() {
		long releaseId = 187768943;
		String expectedUpc = "232308534436";
		String actualUpc = DbInstanceManager.getInstance(DbUser.album)
				.getRecordForSingleValue(String.class, "select upc from album.release where id = %d", releaseId);

		assertThat(actualUpc).isEqualTo(expectedUpc);
	}

	@Test
	void getSingleObjectDemo() {
		long releaseId = 187768943;
		Release release = DbInstanceManager.getInstance(DbUser.album)
				.getRecordAsObject(Release.class, "select * from album.release where id = %d", releaseId);

		assertThat(release.getUpc()).isEqualTo("232308534436");
	}

}
