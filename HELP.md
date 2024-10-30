# Read Me First
The following was discovered as part of building this project:

* The original package name 'hr.vsite.message-app-rest-api.message-app-rest-api' is invalid and this project uses 'hr.vsite.messageapprestapi' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.4/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.4/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.4/reference/htmlsingle/#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### How and where to use tests
In general, it's recommended to use a combination of different types of tests, including unit tests, integration tests, and end-to-end tests, to ensure comprehensive test coverage of your Spring Boot application.

Here are some guidelines for when to use each type of test:

Unit tests: Unit tests are used to test individual units or components of your application in isolation. These tests should be used to test the smallest possible piece of code, such as a single method or function. Unit tests are typically faster and easier to write and run than other types of tests. Use unit tests to ensure that your code works as expected and catches any bugs early in the development process.

Integration tests: Integration tests are used to test how different units or components of your application work together. These tests should be used to test how your application behaves in different scenarios, such as when it interacts with a database or third-party API. Integration tests are typically slower and more complex than unit tests. Use integration tests to ensure that your application works correctly in a real-world environment.

End-to-end tests: End-to-end tests are used to test your entire application, from the front-end to the back-end. These tests should be used to test the user experience and ensure that all the different parts of your application work together seamlessly. End-to-end tests are typically the slowest and most complex type of test, but they are essential to ensure that your application works as expected for your users.

In general, you should start with unit tests and then move on to integration tests as you build out your application. End-to-end tests should be added once your application is more mature and stable. However, it's important to use your judgment and adapt your testing strategy to the specific needs of your application.
