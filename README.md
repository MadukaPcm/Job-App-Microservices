# JOBAPP - MICROSERVICE

## Spring-boot 3.4.1 used with java 17.

## Microservice conversion web-services from JobApp, Available via : https://github.com/MadukaPcm/JobApp

## The System have three web-services:
  1. Job service
  2. Company service
  3. Review service
  
## ZipKin server for Distributed Tracing (Using docker file).
  1. Docx => https://zipkin.io/pages/quickstart
  2. Commands.
     - docker run -d -p 9411:9411 openzipkin/zipkin (run it in detached mode)
     - docker run -d --name rabbitmq-server -p 5672:5672 -p 15672:15672 rabbitmq:4-management
  
## Useful links for Spring-OAuth2 with JWT and API-Gateway.
  1. OAuth2  => https://www.geeksforgeeks.org/spring-boot-oauth2-with-jwt 
  2. API-Gateway  => https://www.geeksforgeeks.org/api-gateway-authentication-and-authorization-in-spring-boot
