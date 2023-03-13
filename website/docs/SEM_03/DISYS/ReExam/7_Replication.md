# (7) Replication

To ensure service availability, an algorithm to replicate a server/service will lower the risk of inavailability. If one node/server has probability `p` to become unavailable, then `n` replicas will make the overall service have `1 - p^n` risk of becoming unavailable.

Commonly, a requirement is that the client should not know about the replicas.

## Active replication

1. Client multicast the request to all replicas and does not send new requests until it receives a response.
1. The replicas coordinate the order of the requests such that it is sequentially ordered
1. All replicas executes the request deterministically
1. All replicas sends the response to the client

## Passive replication

1. Client (frontend) sends request to primary replica including a unique request identifier
1. If request is already calculated, the replica just sends back the response
1. Otherwise, execute request and store response.
1. Sends request, identifier and response to backup replicas
1. Retrieve acknowledgement from backup replicas
1. The primary replica sends the response to the client

In the event that the primary replica crashes, a backup replica must be chosen, i.e. using a leader election algorithm.
