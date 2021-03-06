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
* Persistence: Continue to use Spring behind wrapper classes for working with databases
  and other technologies.
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
* Minimum level: Use this for maximum customization of request. It provides a RequestSpecification populated with the base URL of the
API under test, but otherwise requires standard RestAssured patterns.  Use standard RestAssured coding to configure other
  parts of the request and make the request.
  
Example
```java
given(ContentCoreClient.releaseApi)
    .pathParam(ContentCoreReleaseApi.releaseId, "187768943")
.when().get().then()
    .statusCode(200);
```

* Mid level: Use this to further simplify API requests.  Provides a RequestSpecification populated with the base URL of the
  API under test along with any path parameters.  Use this instead of the max level wrapper for requests where
  you need to also specify other things like headers or query parameters.
  
Example:
```java
ContentCoreClient.getReleaseApi( "187768943")
    .get().then()
    .statusCode(200);
```

* Maximum level: Use this for simple API requests.  Provides a Response object after making the API call.
Use this in cases where no manual configuration is needed.  This requires the
  least amount of code in a test.
  
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
to organize commonly used and related queries together.  The helper methods
have been revised to take a parameterized query string as would be used with String.format,
so there's no longer a need to use that on the query string before passing it into the helper
method.

Example:
```java
long releaseId = 187768943;
Release release = DbInstanceManager.getInstance(DbUser.album)
    .getRecordAsObject(Release.class, "select * from album.release where id = %d", releaseId);

```