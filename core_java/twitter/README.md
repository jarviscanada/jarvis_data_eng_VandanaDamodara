#Twitter CLI Application

## Introduction
The Twitter Command Line Interface (CLI) Application is capable of posting, showing, and deleting a post on Twitter using the command line. Libraries such as HTTP and OAuth 1.0 were used to send/receive/authenticate requests using the Twitter Rest APIs. The Jackson library was used to convert JSON objects to Tweet objects. Moreover, Apache Maven was used to handle project dependencies, testing was done using JUnit4 and Mockito, and the app was deployed using Docker.The app follows the MVC architecture and utilizes the Twitter RESTful API using the Apache HTTPComponents library to connect to Twitter.
Targeted User: The product can be used by anyone who would like to post | show | delete a tweet using command line interface. It will be especially useful for people who post regularly on Twitter.
Technologies: Git | Docker | Java SE 8 | Apache Maven | Twitter Rest API

## Quick Start
- Packaging via Maven
```$ mvn clean package```
- 
- Deployment via Docker
  ```Usage: TwitterApp post|show|delete [options]
  $ docker pull vandana1/twitter
$ docker run --rm \
-e consumerKey = YOUR_VALUE \
-e consumerSecret = YOUR_VALUE \
-e accessToken = YOUR_VALUE \
-e tokenSecret = YOUR_VALUE \
vandana1/twitter "USAGE" ````

## Design
### UML diagram

###The Components
TwitterCliApp
- The view in MVC responsible for collecting the CLI arguments, calling the appropriate functions then returning the VIEW of the corresponding results in pretty JSON.

TwitterController
- The controller in MVC responsible for controlling the flow of the program [HTTP requests] and acts as an interface between the View and Model components. It calls Service Component based on user input and option to retrieve the corresponding business logic, acquire the results, and pass it to the VIEW for rendering.

TwitterService
- The business logic of the app responsible for determining how data is created, altered, showed, or stored using the MODEL objects. It also provides a set of rules to validate objects and how each interacts with the other. With this particular app, it is responsible for validating the tweet's text message and coordinates.

TwitterDao
- Related to model in MVC, it is responsible for all data-related logic such as how data is being transferred between the View and Controller components. More importantly, it is responsible for modeling the data by utilizing the actual Model objects, to and from Twitter and utilizes the HttpHelper for its transport.

TwitterHttpHelper
- As the name suggests, it handles the HTTP communication using the "apache.http" library between the app and the Twitter API. It is also responsible for authorization using the "oauth" library and for the actual transport [sending and receiving] of data.
- 

