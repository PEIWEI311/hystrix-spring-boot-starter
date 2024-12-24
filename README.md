# Hystrix Spring Boot Starter

## Overview

The **Hystrix Spring Boot Starter** is a custom middleware library designed to integrate Netflix Hystrix into Spring Boot projects effortlessly. It provides an annotation-based approach to configure circuit breaking, fallback mechanisms, and request isolation for methods within your application.

By using this library, developers can focus on their core logic while seamlessly enabling Hystrix features with minimal configuration.

------

## Features

- Annotation-Driven Configuration:

  ​	Use `@DoHystrix` to enable Hystrix capabilities such as circuit breaking and fallback for individual methods.

- Customizable Timeout and Fallback Logic:

  - Easily define timeout values and JSON-based fallback responses.

- AOP Integration:

  - Automatically intercepts annotated methods using Spring AOP.

- Hystrix Command Encapsulation:

  - Leverages HystrixCommand internally to handle fault tolerance and request isolation.

## How to Use

1. **Add the Dependency**: Include the library in your Spring Boot project.

2. **Annotate Methods**: Use `@DoHystrix` on any method where you want to enable circuit breaking or fallback logic.

   Example:

   ```java
   @DoHystrix(returnJson = "{\"error\":\"Service unavailable\"}", timeoutValue = 2000)
   public String fetchData() {
       // Simulate an external call
       return "Success";
   }
   
   ```

   

3. **Configure Your Application**: Ensure your Spring Boot application scans the appropriate packages containing the middleware.

   

## Example Scenarios

1. **Circuit Breaking**: Automatically stops calling failing services after the timeout threshold is exceeded.
2. **Fallback Mechanism**: Returns predefined fallback data in JSON format if the service is unavailable.
3. **Request Isolation**: Ensures independent service calls using HystrixCommand.

------



## Dependencies

This project depends on the following libraries:

- **Spring Boot**: For seamless integration with Spring projects.
- **Hystrix**: For implementing circuit breaker patterns and request isolation.
- **FastJSON**: For parsing JSON fallback responses.
