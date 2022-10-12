# Concurrency, threads, parallelism

## Concurrency

A property of systems where more than one computation can be executed simultaneously.
Hereby, the order of which the computations run is unimportant.

## Parallelism

The act of doing things concurrently.

## Multithreading

A single-thread system has a heap storage and one stack for the main thread. A multi-thread system has a stack for each thread, where the heap storage is global scope.

Create a new thread using:

```cs
var t = new Thread(() => 
{
    // do something
});
t.Start();
```

To make sure a thread is finished before

## Asynchronous methods

Used to present the impression of concurrency or parallelism.

A way to call a method and just continue until the response is back.

## Race condition

Shared data must be threadsafe, as multiple threads accessing (writing to) the same data structure can cause issues, e.g. where you attempt to add multiple items to the same list without giving it the opportunity to extend the underlying data structure's size.

Solve using a lock, where `sb` is a stringbuilder in the following example:

```cs
lock(sb) {
    sb.Append(x);
}
```

Since this can slow down a program, a concurrent implementation of a data structure can be wise:

```cs
var sb = ConcurrentQueue<string>();
```

## Deadlock

A situation where two actions are awaiting eachothers actions and thus neither acts.

This could be two transactions using the same two accounts in a bank. If one attempts to lock a and then b, while the other locks b then a, that causes a deadlock.

The solution would be to find a deterministic order of the accounts and lock them in this order, meaning that despite one transaction being `a -> b` and the other being `b -> a`, both transactions should lock first `a` then `b`.

## Task Parallel Library (TPL)

## Asynchronous Programming
