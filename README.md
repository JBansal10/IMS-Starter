Coverage: 83.7%
# Inventory Management System (IMS)

This following document contains the necessary code to work with the IMS program, included is a functioning customer, order and items entity. They each have their own DAO and controller as well as a main class each. This was coded in Java using Eclipse with testing done through Junit and Mockito. The build tool used was Maven. A local MYSQL server was used to connect this IMS to the database. 

Through command line, this IMS program is used. This is used to create customers, items and orders. Customers require a first name and a last name, items require a name, amount of stock available and a price while the orders require a customer id and item id with the date being input automatically. 

## Getting Started
Be sure to fork the repository and then download the code as a zip file or clone the code down to your machine. Extract the zip file and you should have a folder with all the files required available. Through eclipse, you will be able to import the folder by going to File, Import, Import existing Maven Project. This should provide access to the project in eclipse.
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

These are the required tools for eclipse, java, maven and MYSQL:
Install Java 8 development kit from https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html 

Install Maven from https://maven.apache.org/download.cgi

Install Eclipse from https://www.eclipse.org/

Install MySQL Workbench from https://www.mysql.com/products/workbench/

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

Explain what these tests test, why and how to run them

```
Give an example
```

### Integration Tests 
Explain what these tests test, why and how to run them

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
