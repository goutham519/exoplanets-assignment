# Exoplanet Application

This is a demo project for an Exoplanet Spring Boot application. The application consists of a REST controller (`ExoplanetController`) and a service (`ExoplanetService`) that retrieves exoplanet details from an external API and performs some calculations on the data.

## Prerequisites

Before running the application, ensure that you have the following installed:

- Java Development Kit (JDK) 17
- Apache Maven

## Getting Started

To run the Exoplanet application, follow these steps:

1. Clone the repository or download the source code.

2. You can start the application by running ExoplanetApplication

   The application will start running on `http://localhost:8080`.

## API Endpoint

The Exoplanet application exposes a single API endpoint:

- GET `/exoplanets/requested-details`: Retrieves exoplanet details with details.

  Example Response:
   ```
   {
       "Total Orphan Planets": 5,
       "Planet with hottest star": "Planet X",
       "Discovery Timeline": {
           "2009": {
               "small": 2,
               "medium": 1
           },
           "2011": {
               "large": 3
           },
           "No Year": {
               "small": 1
           }
       }
   }
   ```

## Running the Tests

The application includes JUnit tests for the `ExoplanetController` and `ExoplanetService` classes.
