# (3) Microservices and Web

Avoiding old-school monolith applications where one application is responsible for everything. A request to post a video is done on the same server as the request to watch a video, meaning that even if many more people request to watch videos, it will have a similar latency as for those who want to upload videos.

## Reliability

A system should be prone to hardware, software and human errors.

## Scalability

It should be possible to increase the number of users but maintain a good performance.

## Maintainability

It should be easy to keep the system running and to develop new functionality later on.

## Microservices

With microservices, it is possible to have smaller applications that have different areas of responsibility. Instead of the monolithic structure, the client side will now communicate with the proper microservices depending on what they need.

This allows for easily re-scaling the number of each microservice running depending on traffic. An example could be the Danish tax system, where they can have some servers hosting their usual microservices which allows for fast response times on a daily basis, but once the yearly tax reports are made, the microservice responsible for this area can be deployed in hundreds allowing for much more traffic in this area than usual and downscaling the traffic on other microservices that are not used as much simultaneously.

The scalability is done e.g. using Docker containers, Cloud Native or similar.

## Remote Procedure Call (RPC)

Old ways was RMI, Soap etc.

New ways to communicate is RESTful and RPC APIs.

REST uses JSON over HTTP, whereas gRPC uses binary communication over HTTP which is faster. (Protocol buffers)

A request can be sent using a pre-decided interface with some parameters, where the server will respond with the result much like a function call in an application (`getResult(parameter) => return result`)

Asynchronous networks makes request->response non-trivial.

### At-least-once

At least once means that the server might send duplicate messages to ensure data transmission is reliable, meaning the client must handle duplicate messages and remove such.

### At-most-once

At most once means that the server only sends packets once, so the client does not need to handle duplicates but must handle (or ignore) lost or corrupted packets by requesting them anew.

At most once is more useful where data loss is acceptable, e.g. in logging or monitoring systems.

### Technology

High cohesion - low coupling

gRPC allows for business agility, where small development teams are responsible for different codebases and deprecated microservices can be left without hurting the rest of the business' applications.

It also means that one microservice can use one technology stack where a different microservice in the same business uses a completely different technology stack.

The implementation is hidden behind interface files, reducing security risks.

Asynchronous such that if one microservice fails it does not impact the rest of the application.
