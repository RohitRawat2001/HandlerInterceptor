# Approach to achieve Interceptor

In Spring Boot, Interceptors are used to intercept incoming HTTP requests and outgoing HTTP responses. They allow you to apply custom pre-processing and post-processing logic to requests and responses, such as logging, authentication, modifying request/response headers, and more.

```
# Approach          ||   	 Scope    ||   	Use Case
HandlerInterceptor ||	Spring MVC	  ||    Pre/post-processing of HTTP requests.
Spring AOP          ||	Any Spring Bean   ||	Logging, security, transactions.
Filters             ||	Servlet level      ||	Generic request/response handling.
```
