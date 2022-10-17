# Remote Procedure Call (RPC)

The reason to create complex distributed systems with microservices: Reliability, scalability and maintainability

- Reliability
  - Tolerating hardware and software faults, including human errors
- Scalability
  - Meet increasing demand
  - Bigger loads and better performance, e.g. latency
- Maintainability
  - Keywords: Operability, simplicity and evolvability
  - How will me or someone else maintain the source code?

## Lifecycle

Indepedent teams create different microservices so it is possible to have different releases.

## Request - Response

Send a request to a webservice, e.g.

```sh
curl "https://api.open-meteo.com/v1/forecast? latitude=55.6763&longitude=12.5681&current_weather=true"
```

This returns a JSON response which is wasting resources due to the numerous times a string is returned if the response contains several entries/records. This is an example of a RESTful communication over HTTP using JSON.

A more resourceful solution is using [gRPC](#grpc) which uses a binary connection, still over HTTP.

### How to handle missing responses

- Retry (At least once) - Bad, if the request is along the lines of transferring a money amount.
  - Could add an identifying number / hashcode etc. so the request can be marked as a duplicate by the receiving server.
- Start an error procedure
- At most once (It is the receivers issue if not everything is received)

## States

### Stateful

The microservice remembers the requester

### Stateless

The microservice does not remember the requester and the requester must encode the required information from the requester in each request.

## gRPC

Good for internal microservices where the two actors (client + server) can agree upon the data format. For external microservices, RESTful services are a better option.

### Asynchronous vs synchronous

I can move on and come back and retrieve the response at a later time when doing asynchronous requests, while synchronous requests means the client must wait for a response before moving on. Asynchronous is mostly best, but synchronous is necessary at something like an authentication point

gRPC supports both.

### Streaming

Streaming goes one or two ways and is several messages from one client to a server or vice versa. This can be server-streaming, client-streaming or bi-directional-streaming.
