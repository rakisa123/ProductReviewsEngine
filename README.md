# JPA One to Many Relationship Example with Spring Boot, Maven, and H2 database

## Things covered during the development:
– SpringBoot project
– Create Models
– Create JPA Repositories
– Configure Datasource & Spring JPA
– Implement a test Client
– Run & Check results

## Prerequisites
- JDK 1.8 or later
- Maven 3 or later
- STS/Eclipse/Intellij IDE

## Stack
- Spring Data JPA
- Spring Boot
- H2 Database

## Source Code

## Build & Run
ProductReviews service uses H2 database. Connection properties (and other properties) are located in application.yml


Build service from the command line:
```sh
mvn clean package
```

Start service from the command line:
```sh
java -jar ProductReviews-1.jar
```

Actuator endpoints: (exposed on port 8081)
These endpoints will monitor and let the health, metrics of the service
```sh
http://localhost:8081/manage/health
http://localhost:8081/manage/metrics
```

Sample API calls: (exposed on port 8080)

CREATE Product: POST request
A product can be created by providing its name:
```sh
URL: localhost:8080/products
Body:
{
	"name": "Iphone X"
}
```

Fetch all products in the database repository:
```sh
http://localhost:8080/products
```

Fetch specific product by its Id:
To fetch all reviews and aggregated score for a given product, fetch the specific product info as below:
```sh
http://localhost:8080/products/1
```

CREATE Review: POST request
A review can be created associated to a give product:
```sh
URL: localhost:8080/reviews
Body:
{
	"comment": "iphone x looks great",
	"score": 4,
	"productId": 1,
	"userId": "rakisa"
}
```

API RESPONSE:
REVIEW:
```sh
"reviews": [
      {
        "id": 9,
        "score": 4.0,
        "description": "iphone case looks great",
        "dateAdded": null,
        "userId": "rakisa"
      }
    ]
```
PRODUCT:
```sh
{
    "id": 5,
    "name": "Iphone 6s case",
    "reviews": [
      {
        "id": 9,
        "score": 4.0,
        "description": "iphone case looks great",
        "dateAdded": null,
        "userId": "rakisa"
      }
    ],
    "aggregatedScore": 4.0
  }
```



