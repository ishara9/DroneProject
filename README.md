# DroneProject


### How to build

#### requirements

- Java 8
- Java IDE (e.g. IntelliJ IDEA)


### How to run

- Clone this repo(https://github.com/ishara9/DroneProject.git)
- Open Project from Java IDE as a maven project.

### How to test instructions 

- Run **DroneProjectApplication.class** main method
- Data required to test APIs will be preloaded to in-memory database 
with the DroneConfiguration
- Use Postman or any REST Api test client to run following API calls

### API calls

#### Register a drone (Task 1)
- localhost:8080/api/v1/drone
- METHOD POST
- BODY:
```yaml
  {
  "serialNumber": "1CE041230E",
  "model": "HEAVY_WEIGHT",
  "weightLimit": 400.0,
  "batteryCapacity": 100.0,
  "state": "IDLE"
  }
```

#### Load a drone with medication items by drone Id (Task 2)
- localhost:8080/api/v1/drone/3?ids=1&2
- METHOD PUT

#### Checking loaded Medication  items for a given drone (Task 3):
- localhost:8080/api/v1/medication/1
- METHOD GET

#### Checking available drones for load (Task 4):
- localhost:8080/api/v1/drone?state=available
- METHOD GET

#### Check batter level of a drone by drone Id (Task 5):
- localhost:8080/api/v1/drone/4/battery
- METHOD GET

#### Find All Drones:
- localhost:8080/api/v1/drone
- METHOD GET

#### Get All Medication:
- localhost:8080/api/v1/medication/
- METHOD GET







