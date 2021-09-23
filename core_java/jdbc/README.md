# Introduction

The JDBC Application can be used to perform queries using the Java Database Connectivity (JDBC) on a PostgreSQL database, which contains information on customer's orders from a retail store. Create Read Update Delete (CRUD) operations were effectively implemented in the design by using Data Access Objects and Data Transfer Objects.
Psql was instanced using Docker and dependencies were handled through Maven while the JDBC handled the connection between application and database>

##Technologies

Git | Java SE 8 | JDBC API | Apache Maven | PostgreSQL

#Implementation
## ER Diagram

![image](./assets/image.jpg)


## Design Patterns

The Data Access Object (DAO) and Data Transfer Object (DTO) pattern was used in this Application for the classes Customer and Order. By using the DAO pattern CRUD operations on the posgresql Database was performed successfully . The complete process of connecting to the database, passing the query, and returning the result can be taken care by this application instead of prompting the user to authenticate each step. The repository design pattern works in a similar way, except its more useful in situations where there are large amounts of classes utilizing heavy querying.
This application is very efficient for huge database.

# Test

I tested the application using sample data in the customer and orders table, and also performed some sample queries to test if the application is working.