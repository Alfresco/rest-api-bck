# Alfresco REST API BCK

The REST API BCK starts an Alfresco instance and runs a set of Postman collections (using the newman command line utility) against the repository.

### Running the BCK

To execute the tests simply run "mvn install".

The results can be found in the target folder. Each collection outputs a file containing the JUnit results, a log of the data that was transferred over the wire and a HTML report.

#### Overriding repository version

To select the version of the repository to execute the tests against override the alfresco.platform.version property, for example to run the tests against 5.1.d use "mvn clean install -Dalfresco.platform.version=5.1.d"