# Java programmer test exercise
# Functional description.
Implement RESTful service with one GET method using the following signature “GET
http://&lt;host&gt;/weather/“
The service should detect location of call’s originator using REST call to any free public
geolocation service (). Using received geolocation data, the service performs a call to free
public weather info provider to retrieve weather info for the specified location. Weather
data and location data should be returned in service response.

# Non-functional requirements.
Unit tests coverage should be more than 80%.
When calling external services, our service should handle situations when 3rd-party
service doesn’t respond in a timely manner or is not available.
Responses from services might be cached using internal cache (any java caching library)
Processed weather data should be stored in a local database to be used later for weather
data analytics. Design database structure to allow query weather history for specific
location and to list queries for specific IP address.
Provide a mechanism for incremental changes in DB structure using any existing
technology.
Non-functional requirements list is prioritised, so top items provide more value when being
implemented.
An applicant should provide both source code and executable jar file, which should contain
embedded web-container with RESTful service inside.