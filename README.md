# Automation Framework

The new framework will carry over some elements from the old
framework and introduce some new things.  Aside from removing some of the older
technologies in the old framework, we also want to keep things more modular,
meaning we won't have a single, large project like rds-test-common to hold
everything.  Instead, the "common" project will just be a wrapper to pull
together all core modules that we want to expose to all projects. The new framework
will be used for all CMS 3.0 testing.

* Properties: Continue to use the PropertyManager class from old framework.
* Test framework: Continue to use TestNG, but supplement with AssertJ.  
* API: Switch from Jersey to RestAssured, but continue to provide wrapper classes
  to minimize required code in tests.
* Persistence: Continue to use Spring behind wrapper classes for working with databases.
* Build: Switch from Maven to Gradle


## Technologies
* Language: Java
* API Framework: RestAssured
* Test Framework: TestNG
* Assert Framework: AssertJ
* Persistence Libraries: Spring

## Details

### API Framework
Three levels of wrapping will be provided for RestAssured.
* Minimum level provides a RequestSpecification populated with the base URL of the
API under test.  From there, use standard RestAssured coding to configure other
  parts of the request.
  
Example
```java
given(ContentCoreClient.releaseApi)
    .pathParam(ContentCoreReleaseApi.releaseId, "187768943")
.when().get().then()
    .statusCode(200);
```

* Mid level provides a RequestSpecification populated with the base URL of the
  API under test along with any path parameters.  Use this for requests where
  you need to also specify other things like headers or query parameters.
  
Example:
```java
ContentCoreClient.getReleaseApi( "187768943")
    .get().then()
    .statusCode(200);
```

* Maximum level provides a Response object after making the API call.
Use this in cases where no manual configuration is needed.
  
Example:
```java
ContentCoreClient.getRelease( "187768943")
    .then()
    .statusCode(200);
```

### Database Access
Database access will follow a similar pattern as before.  The old OracleInstanceManager
has been revised to a generic DbInstanceManager that can work with any RDBMS.  The
helper methods have been renamed to make more sense regarding what they do.  As before, "Data" classes will be used 
to organize commonly used and related queries together.

Example:
```java
long releaseId = 187768943;
Release release = DbInstanceManager.getInstance(DbUser.album)
    .getRecordAsObject(Release.class, "select * from album.release where id = %d", releaseId);

```