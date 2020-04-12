# Spring Boot + Apache HttpClient 

## 1. How to start
```
$ mvn test
```
The spring-boot endpoint sleeps for 7 secs. One of the unit tests will wait; the other should timeout after 5 secs. This makes use of HttpClient's Socket tineout.
