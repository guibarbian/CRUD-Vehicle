
# Crud-Vehicle 🚗🚕🚙

## Language 📄

This README is also available in other languages:
- 🇧🇷 [Português](README.md)

## The Project 📊

This is a simple vehicles CRUD application developed with SpringBoot.

## Used Technologies 🧭

- **SpringBoot** - Main Framework
- **Spring Data JPA** - For database interaction
- **H2 Database** - In-memory database for testing and development
- **Swagger** - For API documentation
- **Maven** - Dependency manager
- **JUnit** - For unit tests
- **MockMVC** - For integration tests

## Requirements

- Java 8 or above
- Maven
- IDE(recommended IntelliJ or VSCode)

## How to run it

1. Copy the repository
```bash
git clone https://github.com/guibarbian/CRUD-Vehicle
cd CRUD-Vehicle
```
2. Install the Dependencies
```bash
mvn install
```
3. Run the application
```bash
mvn spring-boot:run
```
The application will be executed in http://localhost:8080

You can use some API client like Postman or Insomnia to test the endpoints manually

# Endpoints

This API has the following Endpoints

| Method | Endpoint         | Description              |
|--------|------------------|--------------------------|
| GET    | `/vehicles`      | Lists all the vehicles   |
| GET    | `/vehicles/{id}` | Gets a vehicle by its ID |
| POST   | `/vehicles`      | Create a new vehicle     |
| PUT    | `/vehicles/{id}` | Updates a vehicle        |
| DELETE | `/vehicles/{id}` | Deletes a vehicle        |

## Creating a Vehicle

To create or update a vehicle, you must send a JSON body with the following attributes:
```json
{
  "type":"typeOfVehicle",
  "brand": "vehiclesBrand",
  "model": "vehiclesModel",
  "manufacturingYear": "yearInNumber"
}
```

⚠️ The only accepted "type"s are ⚠️
```
"car", "motorcycle" and "truck"
```

⚠️ And they must have a special attribute ⚠️

````json
{
  "door": "numberOfDoorsCarOnly",
  "hasElectricStart": "booleanBikeOnly",
  "maxCargo": "maximumCargoHandledTruckOnly"
}
````

# Developed with ⚙

- **IntelliJ IDEA**

# Author ✏

- Guilherme A. Barbian 


