# Logical Time

## Processes


## Events

An event `e` happens at a process and has an action.

Events are transitive relations such that `e -> e'' if e -> e' and e' -> e''`

If events are not related, such that there is no way to tell if `e` or `e'` happens first, we say they happen concurrently written as `e ∥ e'`

## Logical clocks (theory)

A logical clock L<sub>i</sub> is added to all processes.

### Lamport clocks

Each node has a counter `t` that increments at each local event.

The Lamport timestamp is the value `L(e)` which is the value of `t` after the increment.

When sending a message on a network, attach the Lamport timestamp. When receiving a message, let `t = L(e)` of the received message. Then `t := t+1`

### Vector clocks

With nodes p<sub>1</sub>,...p<sub>n</sub>, `V(a)=<t1, t2, ..., tn>` where `n` is the number of nodes/processes in the system.

Each node has the current vector timestamp `T`. On a local event at node p<sub>i</sub>, `T[i] := <t1, t2, ..., ti, ..., tn> -> <t1, t2, ..., ti+1, ..., tn`

When sending a message, `T` is attached.  
When receiving a message, merge local `T` with `Tm` (received) by taking maximum value.
