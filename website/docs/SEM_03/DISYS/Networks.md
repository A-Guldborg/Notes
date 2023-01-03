# Networks and time

## Perfect Network

Messages always arrive and always with known delay `D`.

Synchronize clocks by sender sending time `T` and receiver setting time to `T+D`.

## Synchronous networks

Messages always arrive and always with a delay of maximum `D`. (Bounded).

Synchronize clocks by sender sending time `T` and receiver setting time to `T + D/2`.

Synchronization error is at most `D/2`.

## Physical Time

### International Atomic Time (TAI)

Man-made time based on atomic clocks. 1 second is 9.192.631.770 oscillations of the cesium atom.

### Astronomic Time (UTC)

Based on earths rotations, which is irregular.

### Computer system clocks

Uses a quartz crystal for oscillations. Not as precise as atomic clocks. Depends on temperature.

## Logical Time

Each process p<sub>i</sub> in p<sub>1</sub>,...p<sub>n</sub> has a `clock` C<sub>i</sub>.

Process p<sub>i</sub> also has state s<sub>i</sub>

Processes have actions:

- Send
- Receive
- Internal

### Happened-before

If either of these are true, then we say an event happens before another event:

1. If `e -> i e'` then `e -> e'`

2. `send(msg) -> receive(msg)`

3. transitive closure: if `e -> e'` and `e' -> e''` then `e -> e''`

### Concurrent events

Events are concurrent if we cannot determine `a -> b` and `b -> a`, written as `a || b`.

### Causality

If `a -> b` then `a` might have caused `b`, but if `a || b` then we know for certain that `a` did not cause `b`.

### Logical Clocks / Lamport Timestamps

Each process p<sub>i</sub> contains a timestamp L<sub>i</sub>.

At all events at p<sub>i</sub>, let L<sub>i</sub> = L<sub>i</sub> + 1.

When sending messages: `send(m)`, mount time `t=`L<sub>i</sub> on `m`.

When receiving messages: `receive(m)`, L<sub>i</sub>`=max(m.t,`L<sub>i</sub>`)`, then increment L<sub>i</sub> = L<sub>i</sub> + 1.

This is Lamport timestamps with the properties: if `a->b` then `L(A)<L(b)`.

### Vector clocks

Assuming `n` nodes in a network, each node has a vector timestamp: `V(a)=<t1, ... tn>`.

At any local event for node `pi`, increment `ti` by 1.

When sending messages, mount `V(a)` to the message.

When receiving message `m`, for each `T` in `V(a)`, update the vector time to be the max of `mt(i)` and `V(a)`
