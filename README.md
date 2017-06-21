
# ***NOTE: This project is no longer used or maintained !***

# Alfresco REST API BCK (DEPRECATED)

The REST API BCK starts an Alfresco instance and runs a set of Postman collections (using the newman command line utility) against the repository.

### Running the BCK

To execute the tests simply run: 

`mvn clean install`.

The results can be found in the target folder. Each collection outputs a file containing the JUnit results, a log of the data that was transferred over the wire and a HTML report.

#### Overriding repository version

To select the version of the repository to execute the tests against override the alfresco.platform.version property, for example to run the tests against 5.2.a-EA use:

`mvn install -Dalfresco.platform.version=5.2.a-EA`

#### Including newer collections

By default, the collections executed will only test the APIs avaialble in releases between 4.2 and 5.1.

To include collections that test the APIs added since 5.1 activate the appropriate profile when executing the maven command. 

Profiles currently available are:

- 5.2

For example, to run all collections against the 5.2.a Early Access Community release execute the following command:

`mvn clean install -Dalfresco.platform.version=5.2.a-EA -P5.2`
