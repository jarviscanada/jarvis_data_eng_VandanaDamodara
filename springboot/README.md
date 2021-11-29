Table of contents
* [Introduction](#Introduction)
* [Quick Start](#Quick Start)
* [Implemenation](#Implemenation)
* [Test](#Test)
* [Deployment](#Deployment)
* [Improvements](#Improvements)

# Introduction

- This is a SpringBoot Trading Application for Jarvis which provides a new trading platform that replaces the legacy trading app. Because the previous app had monolithic architecture, which was hard to maintain and scale, this new system will provide microservice architecture and SpringBoot framework for efficiency.
- This application is a RESTful API and can be consumed by various clients such as web/mobile applications. HTTP endpoints are used to help manage trader profiles, accounts, and trade securities.

- Technologies Used: Java, SpringBoot, REST API, Apache Tomcat, PostgreSQL, MVP, Docker, Maven, Swagger-Ui, Postman

# Quick Start

- Prequiresites: Docker, CentOS 7
- Pull images from docker hub
``` docker pull vandana1/trading-app ```
```    docker pull vandana1/trading-psql ```

- Create a docker network
``` docker network create trading-net ```

- Start containers
``` docker run --name trading-psql-dev \```
``` -e POSTGRES_PASSWORD=password \ ```
``` -e POSTGRES_DB=jrvstrading \ ```
``` -e POSTGRES_USER=postgres \ ```
``` --network trading-net \ ```
``` -d -p 5432:5432 kimbrian94/trading-psql ```

``` IEX_PUB_TOKEN="your_token" ```

``` docker run --name trading-app-dev \ ```
``` -e "PSQL_HOST=trading-psql-dev" \ ```
``` -e "PSQL_PORT=5432" \ ```
``` -e "PSQL_DB=jrvstrading" \ ```
``` -e "PSQL_USER=postgres" \ ```
``` -e "PSQL_PASSWORD=password" \ ```
``` -e "IEX_PUB_TOKEN=${IEX_PUB_TOKEN}" \ ```
``` --network trading-net \ ```
``` -d -p 8080:8080 -t kimbrian94/trading-app ```

- Trading-app with SwaggerUI
http://localhost:8080/swagger-ui.html
  ![my image](./asset/swagger.png)


# Implementation

## Architecture
![my image](./asset/trading.png)

- Controller layer: In the controller layer, user inputs are parsed and appropriate methods are called based on the REST API endpoint. The endpoints are annotated in the class and method-level for the Tomcat WebServlet to redirect and call to.
- Service layer: In the service layer, the business logic is encapsulated. User input passed as arguments are validated and exceptions are handled if any error occurs.
- DAO layer: In the dao layer, HttpClient and DataSource(JDBC) objects are managed to connect to Web service or Database. CRUD operations and complex queries are contained.
- SpringBoot: SpringBoot groups all controller/service/dao layers into one maintainable system. As part of the Springboot framework, Apache Tomcat/WebServlet listens for HTTP Requests and calls the appropriate controller method based on the endpoints. Also, the IoC container maintains and inject dependencies ie. @Component, @Service, etc
- PSQL and IEX: PSQL is the persistent data storage that consists of all the data tables. DataStore(JDBC) object communicates and runs CRUD operations on them. IEX is a web service that uses REST API to provide stock information in JSON format so that the users can consume and deserialize for their uses. This is a SpringBoot Trading Application for Jarvis which provides a new trading platform that replaces the legacy trading app. Because the previous app had monolithic architecture, which was hard to maintain and scale, this new system will provide microservice architecture and SpringBoot framework for efficiency.
- This application is a RESTful API and can be consumed by various clients such as web/mobile applications. HTTP endpoints are used to help manage trader profiles, accounts, and trade securities.


## REST API Usage

### Swagger
SwaggerUI allows the clients and developers to interactively access the Springboot Application in a web browser without needing to deal with the implementation logic.

### Quote Controller
Stock market information is collected from the IEX cloud database and cached in the PSQL database. This controller allows the users to get access to this information, add them to their daily watch list, and update them

- GET /dailyList: Lists all the quotes that are available to be traded from your dailyList
- GET /iex/ticker/{ticker}: Obtain the most recent stock information for the given ticker (stock)
- PUT /quote/iexMarketData: Updates stock information for all the quotes in your daily list
- PUT /quote: Modify the quote in your dailyList manually
- POST /tickerId/{tickerId}: Obtain the stock information for the given tickerId and add it to your dailyList.

### Trader Controller
This controller is responsible for managing the trader's profile and account information, it can also be used to deposit/withdraw money

- POST /trader/: Allows the user to create a profile and account for trader manually

- POST /trader/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}: Similar to /trader/, except this time the trader's profile and account information is passed using the URL same as /trader/, except that the trader object is passed in the URL instead of the request body

- PUT /trader/deposit/traderId/{traderId}/amount/{amount}: Deposit money from the account associated with traderId

- PUT /trader/withdraw/traderId/{traderId}/amount/{amount}: Withdraws money from the account associated with traderId

- DELETE /trader/traderId/{traderId}: Delete a trader by their traderId

# Test

The application was tested using JUnit 4 Integration tests for the Controller, Service, and Data Access Layer. The code coverage was more than 60%.

# Deployment

The Springboot Application and PostgreSQL Database was dockerized, so it's can be easily deployed to any server.

step 1:

Dockerfile was used to package and build the Springboot Application [trading-app] and PostgreSQL Database [trading-psl] images
The trading-psl image was created using postgres:9.6-alpine, this helped us initialize the database with the required tables
The trading-app image was built with maven:3.6-jdk-8-slim and packaged using openjdk:8-alpine.

Step 2:

Containers: trading-psql-dev and trading-app-dev are created using the docker run command

Step 3:

A docker network trading-net has to be created so that the two containers can communicate with each-other
Note:

You would need your IEX_PUB_TOKEN and PSQL username/password/database name [jrvstrading] to effectively run the trading-psql-dev and trading-app-dev containers

# Improvements
- I would implement a feature that would allow the traders to get the ten top most tending stocks in the market.
- Notification or message to the user whenever there is a significant change in market data of daily list.
- Implement alternative stock exchanges so that traders can have access to a variety of markets