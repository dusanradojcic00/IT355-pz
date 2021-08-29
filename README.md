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

| Method | URL | Description |
| -------|-----|------------ |
| POST | /api/auth/login | Login |
| POST | /api/auth/register | Register |

### Cars

| Method | URL | Description |
| -------|-----|------------ |
| POST | /api/auth/login | Login |
| POST | /api/auth/register | Register |

### Reservations

| Method | URL | Description |
| -------|-----|------------ |
| POST | /api/auth/login | Login |
| POST | /api/auth/register | Register |

## Izgled JSON zahteva

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