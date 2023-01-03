# Consensus

Each process `p(i)` proposes a value D.

Each process `p(i)` eventually sets decision value `d(i)`

The protocol must satisfy:

- Termination: Each `p(i)` sets its `d(i)`
- Agreement: Each correct `p(i)` chooses the same `d(i)`
- Integrity: If correct processes all proposed the same value, this value is the `d(i)`

## Incentive

Incentive for a node to agree with the rest of the network requires that the nodes have something to lose.

## Byzantine Generals

Only one process (commander) proposes a value + Agreement + Integrity.

Must have strictly less than `1/3` faulty processes, then the protocol is as follows:

- Commander/Leader sends out a command to all nodes.
- All nodes then share with other nodes what they received.
- For all nodes, the decision is what the majority chose.

If the commander is faulty and sends out different commands, then there will not be a majority between the others.

If less than 1/3 processes are faulty but not the commander, then the majority of the others will still be correct.

Note that system must be synchronous if there are faulty nodes.

## CAP Theorem

It is impossible for a distributed data store to simultaneously provide more than two out of the following three guarantees:

- Consistency: Every read receives the most recent write or an error.
- Availability: Every request receives a (non-error) response, without the guarantee that it contains the most recent write.
- Partition tolerance: The system continues to operate despite an arbitrary number of messages being dropped (or delayed) by the network between the nodes.
