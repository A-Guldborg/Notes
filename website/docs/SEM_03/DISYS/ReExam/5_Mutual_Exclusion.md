# (5) Distributed Mutual Exclusion algorithms

In order to access shared resources, we need to do mutual exclusion such that only one process can access an element at a time.

Processes do not have a shared memory or a shared clock, and communicate through message parsing.

Example:  
10 processes each adding 1 to a shared counter a million times. The expectation is that this will cause the shared counter to have a value of 10 million when all processes are finished. However, just implementing this means that when they update the counter (`t = t + 1`), it will read `t` and then add one and then write to `t` again. In the meantime, another process might have done exactly that, such that two processes have each tried to add 1 to the same number, causing the overall result to be `t = t+1` instead of the expected `t = t + 2`

## Desired properties

- **Safe**: At most one process at a time in the critical section
- **Live**: exit/enter requests eventually succeeds
- **Order**: Entry to the critical section respects a happened-before relation of enter() calls

## Central server

One solution is a central server that queues all enter() requests to the critical section and then asserts only one process enters at a time.

## Token ring

The token ring utilizes a circular connection of all nodes such that when a process asks to enter the critical section, it asks the next process in the line, which will then either disregard the request if it itself is trying to access the critical section or accept and ask the next process for access. If the request manages to go all the way around the token ring, that means all processes accepted that the initial process can access the resource, then it will enter. If it isn't accepted, it will try again unless another process is currently attempting to enter.

This solution requires no central server but the token ring must be modified to accept new processes entering the system or failing processes.

Does not meet the *order* criteria.

## Ricart & Agrawala

When trying to enter the critical section, broadcast a request to every other process in the network with a lamport timestamp included and set internal state of the process to `WANTED`. Wait until all other nodes in the network replies to the request, then access the critical section.

If a request is received, then:

- If the current state is `HELD` or if both the current state is `WANTED` and the lamport timestamp of the incoming request is greater than the current processes internal lamport timestamp
  - then: queue request
- Otherwise, reply to request

On exiting the critical section, make sure to reply to all queued requests.

This satisfies the *safe* property where only one node can access the critical section at a time, the *live* property as all requests are queued and replied to eventually and lastly also the *order* property, as they are ordered by the lamport timestamps.

However, this solution is slow in terms of bandwidth and client delay and none of the solutions are fault tolerant.

## Elections

We want an algorithm to elect a leader process among *`n`* processes. This leader process might have different or more responsibility etc. and is thus a sort of mutual exclusion from a set of peers.

### Desired election properties

- *Safe*: `elected_i` is always either `P || false`
- *Live*: Eventually, a process sets `elected_i != false`

### Ring

To request to become leader, a process sends out the call for election with its own process id to its logical neighbor, which will either:

- Accept and pass on the message to its logical neighbor until the message has gone in a ring, or
- Deny and call out for an election itself, if and only if it has a higher process id also

Once an election is won by process `P`, it sends out a message to everyone that it has been elected.

### Bully

Send out an election message to all higher process IDs. If none responds, it is the current highest process id and wins the election. Broadcast the result to everyone repeatedly with a fixed delay.

If receiving an election message, the request must come from a process with lower ID and is thus rejected.

If not receiving the election result within a fixed timeframe, the last leader must have crashed and the current process should thus call for a new election.
