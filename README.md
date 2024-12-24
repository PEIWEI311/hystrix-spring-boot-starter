# Hystrix Spring Boot Starter

A custom Spring Boot starter that integrates with Netflix Hystrix to provide declarative circuit breaker and fallback logic using annotations. This library allows developers to use Hystrix features in a clean and easy-to-understand way.

## Features

- Simplified integration with Netflix Hystrix for circuit breaker and fallback logic.
- Declarative configuration using the `@DoHystrix` annotation.
- Dynamic timeout and fallback configurations.
- Built-in support for AOP-based interception of annotated methods.

## Project Structure
- **`annotation/DoHystrix.java`**: Defines the custom annotation for circuit breaker and fallback logic.
- **`valve/IValveService.java`**: Interface for circuit breaker service.
- **`valve/impl/HystrixValveImpl.java`**: Implementation of the circuit breaker logic using Hystrix.
- **`DoHystrixPoint.java`**: Spring AOP aspect for intercepting methods annotated with `@DoHystrix`.

