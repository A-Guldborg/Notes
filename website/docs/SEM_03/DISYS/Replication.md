# Replication

Motivation for replication:

- Increased performance
- Availability (probability of failure is 1 minus the probability of individual failure to the power of the number of nodes).
- Fault tolerance
- (Common requirement) Transparency: Client shouldn't know

Assumptions:

- Asynchronous network
- Crash-stop failures only

## 5 stages

1. Request
1. Coordination ("Are we doing this?", order)
1. Execution
1. Agreement (e.g., commit / abort)
1. Response

## Linearisability

Replication correctness according to real-time ordering (requires synchronised clocks)

## Sequentiability

Replication correctness according to program ordering. Linearisability implies sequentiability.

## Passive replication

One primary replica and remaining replica managers are backups.

Client/frontend request the primary replica.

The primary replica checks the requests in the order the requests are received. Then it checks if the request has already been sent and if so, resend the response.

The primary replica executes the request and stores it.

In case of updates, the primary replica sends the updated state, response and unique identifier to the backups who send back an acknowledgement.

The primary replica sends the response to the client/front end.

When the primary replica crashes, a unique backup must be chosen to replace the primary replica, and the new primary must know what the current state/log is at this point in time.

## Active replication

Requires reliably ordered messages across multiple clients.

The client/frontend asks all replicas upon a request (one request at a time).
 
All replicas receives the requests in the same (total) order.

All replicas execute the request deterministically and sends the answer to the client/front end.
