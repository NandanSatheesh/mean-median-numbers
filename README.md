# mean-median-numbers
[![forthebadge](https://forthebadge.com/images/badges/powered-by-responsibility.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)  
Just another Spring Boot Project

## API Endpoints
1. `POST` api/v1.0/number?list=1,2,3,4,5  
Response - 
```
----------------------------------------------------- 
 Status : 200
 Method :: POST
 Parameters :: list :: 1,2,3,4,5 
 Request Url :: /api/v1.0/number
 Remote address :: 17.117.0.1
 Handler :: HandlerExecutionChain with [com.example.numbers.controller.NumbersController#addListOfNumbers(List)] and 2 interceptors
 Response :: {"status":"200 OK","message":"Success","data":"Item Added to List"}
 Body :: [unknown] 
-----------------------------------------------------

```
2. `GET` api/v1.0/number/mean_and_median
```
----------------------------------------------------- 
 Status : 200
 Method :: GET
 Parameters :: 
 Request Url :: /api/v1.0/number/mean_median
 Remote address :: 172.17.0.1
 Handler :: HandlerExecutionChain with [com.example.numbers.controller.NumbersController#getMeanAndMedian()] and 2 interceptors
 Response :: {"status":"200 OK","message":"Success","data":{"mean":3.0,"median":3.0}}
 Body :: [unknown] 
-----------------------------------------------------

```

## Setting up 
The project can be run locally using Docker.

1. Build it using 
```
docker build . -t mean-median:v1.0
``` 
2. Run it using 
```
docker run -p 8080:8080  mean-median:v1.0
```
## Few Extras
1. CI/CD - CircleCI/Heroku
2. Concurrency Load Tests with JMeter (upto 100 Threads)
