# Projektni zadatak iz predmeta IT355 - Veb sistemi 2

## Tema: **Rent-a-car**

Sistem će biti kreiran u vidu RESTfull veb servisa. Konzument REST API-ja će biti aplikacija napisana za Android OS, za
potrebe projekta iz predmeta CS330.

## Korišćene tehnologije

* Java 8
* Spring Boot 2.5.4
* MySQL 8
* Hibernate

## API ENDPOINTS

### Auth

| Method | URL | Description | Request Example |
| -------|-----|------------ | ----------------|
| POST | /api/auth/login | Login | [JSON](#login) |
| POST | /api/auth/register | Register | [JSON](#register) |

### Users

| Method | URL | Description | Request Example |
| -------|-----|------------ |-----------------|
| GET | /api/users/{userId} | Get user by ID| / |
| POST | /api/users | Create a new user | [JSON](#addUser) |
| POST | /api/users/giveadmin | Add admin privileges | [JSON](#giveAdmin)|
| POST | /api/users/takeadmin | Revoke admin privileges | [JSON](#takeAdmin)|
| PUT | /api/users | Update user | [JSON](#updateUser) |
| DELETE | /api/users | Delete user | [JSON](#deleteUser)|

### Cars

| Method | URL | Description | Request Example |
| -------|-----|------------ |-----------------|
| GET | /api/cars | Get all cars| / |
| GET | /api/cars/{carId} | Get car by ID| / |
| GET | /api/cars/available| Get all available cars| ?start="2021-08-09"&end="2021-08-13" |
| POST | /api/cars/ | Create a new car| [JSON](#carRequest) |
| PUT | /api/cars/| Update existing car| [JSON](#carRequest)|
| DELETE | /api/cars/{carId} | Delete existing car| / |

### Reservations

| Method | URL | Description | Request Example |
| -------|-----|------------ |-----------------|
| GET | /api/reservations | Get all reservations| / |
| GET | /api/reservations/{resId} | Get reservation by ID| / |
| POST | /api/reservations | Create a new reservation| [JSON](#resRequest) |
| PUT | /api/reservations| Update existing reservation| [JSON](#resRequest)|
| DELETE | /api/reservations/{resId} | Delete existing reservation| / |

## Izgled JSON zahteva

### Auth

##### <a id="register">Register -> /api/auth/register</a>

```json
{
  "username": "example@gmail.com",
  "password": "examplePassword"
}
```

##### <a id="login">Login -> /api/auth/login</a>

```json
{
  "username": "example@gmail.com",
  "password": "examplePassword"
}
```

### Users

##### <a id="addUser">Create a new user -> /api/users</a>

```json
{
  "username": "example@gamail.com",
  "password": "password",
  "firstName": "Michael",
  "lastName": "Jordan",
  "phone": "+381642901230",
  "address": {
    "city": "Nis",
    "street": "Bulevar Nemanjica",
    "apartment": "bb",
    "zipcode": "18000"
  }
}
```

##### <a id="giveAdmin">Give admin privileges -> /api/users/giveadmin</a>

```json
{
  "username": "example@gmail.com"
}
```

##### <a id="takeAdmin">Revoke admin privileges -> /api/users/takeadmin</a>

```json
{
  "username": "example@gmail.com"
}
```

##### <a id="updateUser">Update existing user -> /api/users</a>

```json
{
  "username": "example@gamail.com",
  "password": "password",
  "firstName": "Michael",
  "lastName": "Jordan",
  "phone": "+381642901230",
  "address": {
    "city": "Nis",
    "street": "Bulevar Nemanjica",
    "apartment": "bb",
    "zipcode": "18000"
  }
}
```

##### <a id="deleteUser">Delete an user -> /api/users</a>

```json
{
  "username": "example@gmail.com"
}
```

or

```json
{
  "id": "123"
}
```

### Cars

##### <a id="carRequest">Create or update the car -> /api/cars</a>

```json
{
  "maker": "BMW",
  "model": "520D",
  "plates": "NI121TI",
  "hp": 195,
  "gears": 6,
  "seats": 5,
  "fuel": "Diesel",
  "transmission": "Manual",
  "price": 45
}
```

### Reservations

##### <a id="#resRequest">Create or update the reservation -> /api/reservations</a>

```json
{
  "startingDate": "2021-07-06",
  "endingDate": "2021-07-08",
  "car": {
    "id": 3
  },
  "user": {
    "id" : 2
  }
}
```