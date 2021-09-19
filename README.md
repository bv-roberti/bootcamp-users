# CRUD API for Clients entity

This software is a sample code on how to build REST APIs using spring boot.

## Installation

Use the package manager Maven.

```bash
maven clean install
```

## Usage

```java
java-jar./backend/target/users-0.0.1-SNAPSHOT.jar
```

```bash
curl --location --request 
  GET 'http://localhost:8080/clients
    ?page=0&linesPerPage=6&direction=ASC&orderBy=name'
```

```bash
curl --location --request 
  POST 'http://localhost:8080/clients' \
  --header 'Content-Type: application/json' \
  --data-raw '{
      "name": "Maria Silva",
      "cpf": "12345678901",
      "income": 6500.0,
      "birthDate": "1994-07-20T10:30:00Z",
      "children": 2
  }
'
```

```bash
curl --location --request 
    PUT 'http://localhost:8080/clients/12' \
    --header 'Content-Type: application/json' \
    --data-raw '{
      "name": "Maria Silva",
      "cpf": "12345678901",
      "income": 6500.0,
      "birthDate": "1994-07-20T10:30:00Z",
      "children": 2
    }
'
```

```bash
curl --location --request 
  DELETE 'http://localhost:8080/clients/12'
```