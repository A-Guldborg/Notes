# (6) Consensus (including RAFT)

Making computers agree can be difficult with crashing nodes or byzantine failures as well as being on an asynchronous network.

Byzantine failures are failures where nodes do not behave as expected, either maliciously or just random.

## Protocol requirements

- *Termination*: Each process makes a decision
- *Agreement*: All correct processes makes the same decision
- *Integrity*: If correct processes all propose the same value, this value is the decision.

## Usage

Computers and algorithms must be byzantine-fault tolerant in many situations, e.g. in airplanes or spacecrafts, where a fault can cause death. Another popular example is cryptocurrency, such that it requires >50% of the network's hashing power to do malicious attacks.

## CAP theorem

The CAP theorem states that it is possible for any system distributed system to simultaneously provide more than 2 of the following:

### Consistency

Every read receives the most recent write or an error.

### Availability

Every request receives a non-error response, without guaranteeing the most recent write.

### Partition tolerance

The system continues to work despite an arbitrary number of lost or delayed messages between nodes.

## Dolev-Strong (simple)

All nodes are either senders or receivers. Senders make a decision and encrypts it with a shared key, then send it to the receivers who decrypt it. If the data from the senders matches that of the receiver, acknowledge the receipt.

If the sender node does not get an acknowledgement response from every receiver node, it assumes there was a fail and tries again until all nodes agree.

## Paxos (best known, messy)

A proposer sends out a message with a proposal including the proposal number which is hashed.

All other nodes receives this proposal and verifies the sender's signature. If it is correct, accept the proposal and send out the accept of the proposal to all nodes.

If everyone agrees to the proposal, the proposal is accepted. Otherwise, once a node receives a conflicting proposal accept it makes a new proposal with a higher proposal number.

Requires multiple rounds and is complex.

## Raft

### Properties

- *Election safety*: At most one leader per term
- *Leader Append-Only*: The leader can only append to the log, not overwrite or delete data
- *Log Matching*: If two logs match at an index and term, they are identical in all entries up until the index.
- *Leader Completeness*: If a leader commits an entry to the log, all subsequent leaders in later terms will have a copy of the commit.
- *State Machine Safety*: If a server has commited a log entry at a specific index, no other server will appply a different log for the same index.

### Steps

The Raft algorithm first chooses a leader that then is constantly in communication with the remaining nodes to update the log.

#### Election

All nodes are in one of the following states:

- Follower
- Candidate
- Leader

1. If a node is a follower but has not heard from a leader within a given timeframe, it becomes candidate. (randomized election timeout). This starts a new term.
1. A candidate sends out their candidature to all other nodes.
1. If the nodes are not candidates or leaders themselves, they vote to accept the candidate.
1. Once the candidate is chosen by a majority, it broadcasts itself as the leader to all nodes
1. At a specified interval, it will send out a heartbeat to all other nodes to stay the leader

#### Log replication

1. Once a leader is chosen, the client communicates through the leader.
1. When the clients attempts to append data to the log, it is uncommitted at the Leader.
1. The leader broadcasts the uncommitted data to all follower nodes and waits for a majority to respond.
1. Once a majority has responded, the data becomes committed to the leader.
1. Once committed, the leader sends out a message to all followers that the append is now committed and all nodes are in consensus.

#### Network partition

In the event that the network is no longer whole, the Raft algorithm ensures that a leader can still be chosen within the partition that accounts for the majority of the nodes.

Once the network heals and there is no longer a partition, the majority partition will have a greater term number and once the minority partition realises this it replicates the log of the majority partition.

Since the minority partition cannot get a majority, all data appends to the minority leader will never get committed.
