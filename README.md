## Product Reviews engine developed to handle the products and reviews operation

## Things covered during the development
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

## Installation

1. Download and Install Spring Tool Suite (STS) as an ID

2. Install Project Lombok on your IDE (if applicable)
      a. Go to https://projectlombok.org/
      b. Download the lombok.jar executable
      c. Double click or use lombok.jar to install it to your IDE
3. Install java https://java.com/en/download/help/windows_manual_download.xml 
4. Install MAVEN https://www.mkyong.com/maven/how-to-install-maven-in-windows/ 

## Stack
- Spring Data JPA
- Spring Boot
- H2 Database

## Source Code
```sh
https://github.com/rakisa123/ProductReviewsEngine.git
```

## AWS Hosting Endpoint
```sh
http://productreviews.us-west-1.elasticbeanstalk.com
```

## Swagger Documentation
```sh
http://localhost:8080/swagger-ui.html
http://productreviews.us-west-1.elasticbeanstalk.com/swagger-ui.html
```

## Build, Test and Run (Local Development)

Download and run the application locally through the STS IDE:-

1. git clone https://github.com/rakisa123/ProductReviewsEngine.git into your local directory of choice (using the command line or your Git tool of choice)
2. Open the STS IDE that was downloaded during the installation steps
3. Right click the package explorer and select Import...
4. Under Maven, choose Existing Maven Projects
5. For root directory, browse to the folder created after git cloning
6. Select Finish
7. Right-click the project in Package Explorer, select Run As -> Spring Boot App
8. To test that it's working, open your browser to http://localhost:8080/products

IMPORTANT NOTE: If the app fails to run, this is probably due to permissions with the log file location. This is easily fixed by finding logback.xml under src/main/resources and changing the LOG_PATH property to point to a location that's not system protected. If you wish you may use the following line as a replacement: (by default in the root folder with the name )
`org.productreviews.service.logpath`


## Build & Run
ProductReviews service uses H2 database. Connection properties (and other properties) are located in application.yml


Build service from the command line:
```sh
mvn clean package
```
Run service from the command line:
```sh
java -jar ProductReviews-1.jar
```

Actuator endpoints: (exposed on port 8081)
These endpoints will monitor and let the health, metrics of the service
```s
http://localhost:8081/manage/health
http://localhost:8081/manage/metrics
```

## Sample API calls (exposed on port 8080)

CREATE Product: POST request
A product can be created by providing its name:
```sh
# Tests
To run unit tests for this project:
mvn clean test
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

## API RESPONSE
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

## Tests
Unit Tests:
To run unit tests for this project:
```sh
mvn clean test
```
This will run all unit tests and generate reports which are uploaded to sonarQube or your own reporting server.

Integration Tests:
To run integration tests for this project:
```sh
mvn verify
```

