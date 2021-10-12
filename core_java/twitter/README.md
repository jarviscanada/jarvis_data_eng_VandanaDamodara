#Twitter CLI Application

## Introduction
The Twitter Command Line Interface (CLI) Application is capable of posting, showing, and deleting a post on Twitter using the command line. Libraries such as HTTP and OAuth 1.0 were used to send/receive/authenticate requests using the Twitter Rest APIs. The Jackson library was used to convert JSON objects to Tweet objects. Moreover, Apache Maven was used to handle project dependencies, testing was done using JUnit4 and Mockito, and the app was deployed using Docker.The app follows the MVC architecture and utilizes the Twitter RESTful API using the Apache HTTPComponents library to connect to Twitter.
Targeted User: The product can be used by anyone who would like to post | show | delete a tweet using command line interface. It will be especially useful for people who post regularly on Twitter.

Technologies: Git | Docker | Java SE 8 | Apache Maven | Twitter Rest API

## Quick Start
- Packaging via Maven
```$ mvn clean package```

- Deployment via Docker
  ``` Usage: TwitterApp post|show|delete [options]
  $ docker pull vandana1/twitter
  $ docker run --rm \
  -e consumerKey = YOUR_VALUE \
  -e consumerSecret = YOUR_VALUE \
  -e accessToken = YOUR_VALUE \
  -e tokenSecret = YOUR_VALUE \
   vandana1/twitter "USAGE" ````

## Design
### UML diagram

### The Components
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

### Models

This app uses a simplified version of the Tweet Model as follows:

- Tweet Model
- Entity Model
- HashTag Model
- UserMention Model
- Coordinates

The Objects are modelled following this sample JSON output.
````{
  "created_at":"Mon Feb 18 21:24:39 +0000 2019",
  "id":1097607853932564480,
  "id_str":"1097607853932564480",
  "text":"test with loc223",
  "entities":{
     "hashtags":[
        {
           "text":"documentation",
           "indices":[
              211,
              225
           ]
        },
        {
           "text":"parsingJSON",
           "indices":[
              226,
              238
           ]
        },
        {
           "text":"GeoTagged",
           "indices":[
              239,
              249
           ]
        }
     ],
     "user_mentions":[
        {
           "name":"Twitter API",
           "indices":[
              4,
              15
           ],
           "screen_name":"twitterapi",
           "id":6253282,
           "id_str":"6253282"
        }
     ]
  },
  "coordinates":{
     "coordinates":[
        -75.14310264,
        40.05701649
     ],
     "type":"Point"
  },
  "retweet_count":0,
  "favorite_count":0,
  "favorited":false,
  "retweeted":false
 }
 ````

### Spring

The biggest challenge in the project had to do with dependency management. With each class having its own dependencies, it can become rather inefficient to create each component and set up their dependencies manually.
It utilizes the Spring IoC container to remove the dependency relationships between the components, by injecting the dependencies via the @ComponentScan approach.
Spring Framework wa used so that all the dependencies can be managed by the concept of Inversion of Control (IOC). The main classes were annotated @Component [TwitterHttpHelper] [TwitterCLIApp], @Controller [TwitterController],@Service [TwitterService], and @Repository [TwitterDao] as well as placing @Autowire before all the constructors to indicate that the class is a Bean and to notify the IoC container to inject any dependencies through the constructor.
@SpringBootApplication was used in TwitterCLISpringBoot to completely automate the process of finding the Beans and injecting the dependencies into the IOC container.

## Test

Development implemented both Unit testing and Integration testing.
Mockito facilitated unit testing by creating and configuring Mock objects for classes with external dependencies that are being tested.
JUnit facilitated integration testing to ensure that all components functioned together as expected.

## Deployment
The Twitter CLI Application was deployed using Docker to Docker Hub using the following steps:

- Build the package
```mvn clean package```

- Build the image
```docker build username/nameOfApplication```

- Push the built image to Docker Hub
```docker push username/nameOfApplication```

## Improvements

- Have a local database to store all the tweets that were posted/retrieved/deleted from all the users.

- Retrieve by hashtags

- Implement a feature that will allow users to post tweets at a preferred time/date
