# REST

Representational State Transfer

## HTTP Request

Contains:

URI: string  
Query: key/value pairs  
Method: string  
Header: key/value pairs  
Body: string/binary  

## HTTP Response

Status-Code: number  
Header: key/value pairs  
Body: string/binary  

### Status-Codes

Code|Meaning
--|--
2xx|Everything is good
3xx|Permanently or temporarily moved (deprecated)
4xx|Something went wrong with the request - Such as bad request or unauthorized/forbidden
418|I'm a teapot
5xx|Server error

### HTTP Headers

Key/value pairs  

Field|Description|Example
--|--|--
Accept|I understand|text/plain<br></br>application/json
Content-Type|I'm sending|application/json
Authorization|Who I Am (Authentication)|Bearer ey...

## REST Request

`GET https://store.com/prices/Apples`

### Response

`1.9`

## ASP.NET Core Web API

Builds ontop of MVC however an API response does not return a view and the user is an application instead.

To list .NET new types, run the following command.

```sh
dotnet new --list
```

To create a new .NET Core Web API run the following command in terminal:

```sh
dotnet new webapi -o <ProjectName>
```

### Swagger documentation

