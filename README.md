MyTimetable API Client
======================

This project provides a very basic MyTimetable API client, which fetches the upcoming events for a specific user.
It is used by the MyTimetable building block for Blackboard Learn and the MyTimetable tool for Sakai.

Usage
-----

The API is a bit rough, and some of the Blackboard/Sakai components are leaking through, but it should be
good enough for general use. You need a MyTimetable 'elevated' API token and the URL of the MyTimetable API.

```java
Configuration config = new Configuration();
config.getApiEndpointUris().add("https://demo.eveoh.nl/api/v0/");
config.setApiKey("MY-API-KEY");

// Create the MyTimetable service class
// Object is thread-safe and can be used by multiple threads
MyTimetableService service = new MyTimetableServiceImpl(config);

upcomingEvents = service.getUpcomingEvents(username);

// Release after use (e.g., in ContextListener)
service.close();
```

Availability
------------

The library is available from our Maven repo. Example Gradle config:

```groovy
repositories {
    maven {
        url "https://maven.eveoh.nl/content/repositories/releases"
    }
}

dependencies {
    compile "nl.eveoh.mytimetable:mytimetable-api-client:1.1.4"
}
```

Logging
-------

Some logging is done using [slf4j](www.slf4j.org), you can use any compatible logger to get the messages. Internally
[Apache HttpClient](http://hc.apache.org/httpcomponents-client-ga/) is used, which uses commons-logging. Try the
`jcl-over-slf4j` package to push these messages to your slf4j logger.

Support and more info
---------------------

Visit our website, http://mytimetable.net, open a ticket (PR's welcome), or drop an email at info@eveoh.nl.

License
-------

    Copyright 2013 - 2016 Eveoh

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
