# Practica Parcial 1

Our client has asked us to build an API that is capable of consuming different sources of weather data 
and centralizing them into a single response per location.

As part of the requirement, we must maintain the information of those who consume our API since at the 
end of each month a charge for integration services is generated by another system.

Additionally, our system returns the information in a more user-friendly format, giving meaning to the 
data it recovers from the meteorological system. 
For example, in addition to returning the Air Quality Index (AQI), we report whether the air is safe or not.

## Requirements

- The API must be able to consume the following sources of weather data:
  - [Locations](https://my-json-server.typicode.com/LCIV-2023/fake-weather/location)
  - [Wind](https://my-json-server.typicode.com/LCIV-2023/fake-weather/wind)
  - [Temperature](https://my-json-server.typicode.com/LCIV-2023/fake-weather/temperature)
  - [Air Quality](https://my-json-server.typicode.com/LCIV-2023/fake-weather/air_quality)
  - [Cloudiness](https://my-json-server.typicode.com/LCIV-2023/fake-weather/cloudiness)
- The API must be able to register the consumption of the API by the client.
  - The client must be registered in the system with a unique identifier using an email.
  - The app will request the favorite temperature unit of the client at the time of registration.
  - The client need to share the client_id and secret with the API to be able to consume it.
- The API must be able to return the information of all available locations.
- The API must be able to return the information of a specific location with this format:
  - The location name and id
  - The temperature in the client's favorite unit
  - The wind speed with the direction in a user-friendly format. Example: 10 km/h from North
    - The wild direction will be received in degrees considering north as 0. 
    Consequently, a wind blowing from the north has a wind direction referred to as 0° (360°); 
    a wind blowing from the east has a wind direction referred to as 90°, etc. Example: 180 = N to S or 0 = S to N.
  - The air quality index with a description of the air quality. Example: 50 - Good
    - Table of air quality index and description:
    
    | Index | Description |
    | ----- | ----------- |
    | 0 - 50 | Good |
    | 51 - 100 | Moderate |
    | 101 - 150 | Unhealthy for Sensitive Groups |
    | 151 - 200 | Unhealthy |
    | 201 - 300 | Very Unhealthy |
    | 301 - 500 | Hazardous |

  - The cloudiness it's expressed in okta. An okta is a unit of measurement used to describe the amount of 
  cloud cover at any given location such as a weather station. Sky conditions are estimated in terms of how 
  many eighths of the sky are covered in cloud, ranging from 0 oktas (completely clear sky) through to 8 oktas (completely overcast)
    - Table of cloudiness index and description:
    
    | Index | Description          |
    |-------|----------------------|
    | 0     | Clear sky            |
    | 1 - 3 | Few clouds           |
    | 4 - 6 | Sky half cludy       |
    | 7 - 8 | Sky completely cludy |

## Example of request & response expected

The api must be able to receive the request with the datetime parameter to return the information of the location.

### Register a new consumer api

#### Request

```http
 curl -X POST 'http://localhost:8080/weather/subscribe
 -H "Content-Type: application/json"
 -d '{"emal": "email@email.com", "temperature_unit": "C"}'
```

#### Response

```json
{
  "client_id": 1,
  "secret": "e58ed763-928c-4155-bee9-fdbaaadc15f3"
}
```

### Get locations

#### Request

```http
 curl -H client_id: 1 -H client_secret: 1 --location 
 --request GET 'http://localhost:8080/weather/locations'
```

#### Response

```json
[
  {
    "id": 1,
    "name": "Location 1",
    "latitude": "40.7128",
    "longitude": "74.0060"
  },
  {
    "id": 2,
    "name": "Location 2",
    "latitude": "50.7128",
    "longitude": "84.0060"
  },
  {
    "id": 3,
    "name": "Location 3",
    "latitude": "60.7128",
    "longitude": "94.0060"
  }
]
```



### Weather request by location with datetime filter

#### Request

```http
 curl -H client_id: 1 -H client_secret: 1 --location 
 --request GET 'http://localhost:8080/weather/location/1?datetime=2017-01-01T00:01:00.000Z'
 
```

#### Response

```json
{
  "location": {
    "id": 1,
    "name": "Location 1"
  },
  "temperature": {
    "value": 20,
    "unit": "C"
  },
  "wind": " 10 Km/h from North",
  "air_quality": {
    "index": 50,
    "description": "Good"
  },
  "cloudiness": {
    "index": 0,
    "description": "Clear sky"
  }
}
```

## Considerations

* The application must be developed using Spring Boot and Java 17.
* All tests must be done using JUnit 5 and Mockito.
* The application must be able to run in a Docker container.
* The application with the My SQl productive database must be able to run with Docker Compose.
# PracticaPrimerParcialLCIV
