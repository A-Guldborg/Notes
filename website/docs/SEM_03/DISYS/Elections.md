# Elections

Elect a server from a set of peers to generate mutual exclusion.

Any process may begin the election process (concurrently).

## Requirements

- E1 Safety: Always either elected: P or elected: false
- E2 Liveness: Eventually processes set elected != false.

## Solution 1: Ring

Requirements:

- Synchronous Distributed System
- Reliable messaging (no failures)
- Each process knows its process id
- Communicates only with "logical neighbor"

During an elect-leader process, highest process id wins. Once a message is back to the owner, a leader has been elected.

Worst case uses 3N-1 for a single election in bandwidth and turn-around.

## Solution 2: Bully

Requirements:

- Synchronous Distributed System
- Crash failures allowed
- Can detect crash failures by timeouts
- Fixed set of processes known to all
- Messages: `Election`, `Answer`, `Coordinator`

Calls election by sending message `Election` to processes with higher ids.

If caller does not receive any answers within timeout limit, then process wins election. When winning, send out `Coordinator` to all processes.

If receiving a call for election, (which has a lower process id), then reply (reject the leader) then call for election.

If no rejected in an election (Receiving an answer) but no `Coordinator` message is received within a timelimit, then a process with higher id must have crashed and then call for new election.
