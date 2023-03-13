# (4) Time in Distributed Systems

## Physical Time

### International Atomic Time (TAI)

Man-made time decided by atomic clocks, where a second is defined by the oscillations of the cesium atom.

### Astronomic Time (UTC)

Based on earths rotations (irregular)

### Computer clocks

Based on oscillations of quartz crystals - not as precise as TAI

## Logical time

We need to make sure what happens before what. Processes are not perfect - Maybe the server experiences a DDoS attack or similar such that the server is slower or more fault-prone. Maybe messages are lost during transmission because of (other) failing nodes or corruption of data.

### Chat systems

Who sent which message? What if the response comes before the reply for a 3rd party in the same chat?

What information did a process have when it sent a message?

### Streaming services / Netflix

Netflix must make sure there are not too much stuttering of the video and that messages are not too delayed - rather miss one frame than waiting a long time to make sure all frames arrive in the correct order.

### Lamport Timestamps

Each process keeps a timestamp, and for every event, increase the timestamp by 1. Attach the local process' timestamp to each sent message, such that when a process receive a message, they will update their local timestamp to whatever is bigger:

- The received message's timestamp + 1
- Its own local timestamp

### Vector clocks

Similar to Lamport timestamps, but instead of keeping track of a process' local time only, it keeps track of all processes' timestamps, and whenever receiving a message, it will update its internal vector clock to be the maximum of either the incoming message's vector clock or its own current vector clock for all processes in the vector clocks.

This makes it possible to determine the order of events for a different process than the process itself.

## Networks

### Perfect network

A network in which messages always arrive and with a known delay *`d`*.

It is then possible to synchronize clocks to determine order of messages by the receiver synchronizing its clock to *`T+d`*.

### Synchronous networks

Differs to the perfect network where the delay is *at most `d`*, and receiver synchronizes to *`T+D/2`*. Then synchronization error is at most *`D/2`*.

If there is a minimum delay bound, then add this minimum delay bound to the *`D/2`* calculation: *`(D+min)/2+min`*.

### Real networks

Asynchronous with unbounded delays, unreliable such that not all messages arrive, partial undeterministic failures.

In an asynchronous distributed system, clocks can be synchronized with Cristians method:

- Estimate `R` = round-trip time.
- `T` = Time for process 1
- `U` = Time for process 2 = `T + R/2`

With Cristians algorithm, ask a server that always knows the exact time, e.g. a GPS-receiver. Then check the time that passed between sending the request and receiving the response `(t1, t4)`, and assume that the network delay was the same for the message each way. The server should send the time receiving the request and sending the response `(t2, t3)` in the response.

Then `(t4-t1)-(t3-t2) = R` and since we can assume the request part of `R` is approximately equal to the response part of `R`, we can set the client's clock to `t3 + R/2`.

### Network Time Protocol (NTP)

A protocol using some main servers with multiple `Stratums`, where the top tier (Stratum 0) uses atomic clocks. Only the tier just below can query the time from these servers, easing the load. The tier below will the synchronize its clock using the above Cristian's method and be open for queries for the tier below.
