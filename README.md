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