# Mobile Integration Work Group Coding Test

##Overview


A sample application to demonstrate a basic understanding of application development.

It is a spring boot application written using Groovy.  I chose spring boot since I'm familiar with it and it's the framework MIW is using.  The the programming language I chose Groovy because I'm familiar with it and I find it much more succinct than Java.

To fill out the rest of the stack I picked:
- H2 In memory Database
- Spring JPA
- Spring security
- Spock for testing

### API endpoints

Only 3 endpoints are supported in the application

HTTP Method| End Point | Notes
------------|-------------|-------------
GET | /api/items | Lists all the items in in the store
GET | /api/items/\{id\} | Gets a single item
POST | /api/items/\{id\}/buy | Buys a single item and decrements the quantity available.  Nothing in the body is required.


### Data format

JSON was chosen as the data format because it is a flexible format that is easy for a human to read and a machine to parse.  None of the endpoints require anything in the body of the request.

Example for of a single item:
```
{
  "id": 1,
  "name": "+5 Dexterity Vest",
  "description": "+5 Dexterity Vest",
  "price": 10,
  "quantity": 20
}
```

### Authentication & Authorization

Currently the application is set up to use basic authentication with the default `user/password`.  Basic authentication is easy to get up and running for a simple api client and does not require a separate work flow to first authenticate. Using spring security we could easily change the authentication mechanism to use a different protocol such as a token based one.

Authorization is done using the Spring Security `@PreAuth` annotation.  I like the declarative way to express the need for security around the method.  The same thing could be accomplished use the antMatchers in the `SecurityConfiguration` class.



## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

To run the project you need to have Java 8 SDK installed.

http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

Java 8 is required for the new `LocalDateTime` class

### Installing

Installing is easy just clone the directory with git or download and expand it.


    git clone git@github.com:cwmccann/miw-demo.git


### Running the tests

To run the tests:

    ./gradlew test 


### Running the application

To start using the application run:


    ./gradlew bootRun


The to get a list items for sale:


    curl localhost:8080/api/items


To buy item 3 (Elixir of the Mongoose)

    curl -X POST -u user:password localhost:8080/api/items/3/buy

## Authors

* **Chris McCann** - *Initial work*