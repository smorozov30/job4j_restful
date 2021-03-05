[![Build Status](https://travis-ci.org/smorozov30/job4j_restful.svg?branch=master)](https://travis-ci.org/smorozov30/job4j_restful)
[![codecov](https://codecov.io/gh/smorozov30/job4j_restful/branch/master/graph/badge.svg?token=NXZTSXS55F)](https://codecov.io/gh/smorozov30/job4j_restful)

# job4j_restful

Первый ознакомительные проект с RESTful.

### Методы API:

#### Employee:
- Получить всех работников. ***GET: /employee/***
- Создать работника. ***POST: /employee/{employeeId}/***
- Обновить работника. ***PUT: /employee/{employeeId}/***
- Удалить работника по id. ***DELETE: /employee/{employeeId}/{id}***

#### Person:
- Получить всех пользователей. ***GET: /person/***
- Получить пользователя по id. ***GET: /person/{id}***
- Создать пользователя. ***POST: /person/***
- Обновить пользователя. ***PUT: /person/***
- Удалить пользователя. ***DELETE: /person/{id}***