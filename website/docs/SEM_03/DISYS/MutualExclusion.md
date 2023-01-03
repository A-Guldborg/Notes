# Mutual Exclusion

Use `sync.Mutex` when multiple goroutines are in a single process.

## Distributed Mutual Exclusion (multiple processes)

Assume asynchronous network with reliable message delivery and no process failures.

Let the API be:

```go
enter()
resourceAccess()
exit()
```

### Desired properties

- ME1 Safe: At most one process in critical section at once.
- ME2 Live: Enter/exit requests eventually succeeds.
- ME3 Order: Entry to critical section respects the happens-before relation.

## Solutions

### (1) Central Server

One server where each client requests access which is added to a queue, then the server is responsible for granting access and releasing the resource such that it is mutually exclusive. This will make it possible to meet all desired properties.

### (2) Token Ring

Token goes around a ring and once a client has the token, keep it if the client wants access to the critical section. Once the access is done, release the token. This will satisfy ME1 and ME2 but not necessarily ME3.

### (3) Ricart & Agrawala

When accessing critical section, request access from all other clients. Wait for n-1 replies and then set internal state to `held`.

If another process requests access, then wait until the internal state is no longer `held` and internal state is not `wanted` with a lesser timestamp than the request, then release access.

Satisfies all desired properties because of the timestamps.
