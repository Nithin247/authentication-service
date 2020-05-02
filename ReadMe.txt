product-read-service

This is a spring boot application which is configured with a basic authentication service using spring security.

This uses Embedded TOmcat server for connecting to the database.

This has three endpoints.

HealthCheckController - to make sure the service is up and running
ProductController - which will fetch the list of available products from database
ConfigController - which will fetch the configuration details

WebSecurityConfig class will do the basic authentication depending on the endpoint being called.

Response object has the parameters that identify response status, response code, response object and a field for error message in case of errors.