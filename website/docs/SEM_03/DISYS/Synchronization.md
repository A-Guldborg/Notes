# Synchronization

## Threads

Does work in parallel. Can cause race conditions if two different threads attempt to access the same resource simultaneously, e.g.:

```md
a = 0;
Thread 1: a = a + 2;
Thread 2: a = a + 3;

Thread 1 accesses variable a = 0 and adds 2 = 2.
Thread 2 accesses variable a = 0 and adds 3 = 3.

Thread 1 writes to variable a = 2;
Thread 2 writes to variable a = 3;

End result: a = 3
Expected: a = 5
```

Solved using `sync.Mutex` in Golang which has methods `Lock()` and `Unlock()`.

## Channels

To communicate between threads, channels can be used:

```go
ch := make(chan int) // int can be replaced with other types

ch <- x // send variable x to the channel

x = <- ch // receive input from the channel and save in variable x

<- ch // receive and discard input from the channel
```

Channels can be used to avoid race conditions by setting variable in one class and using a channel to the thread running this class, which will then be responsible for updating the variable correctly.

## Starvation

Apart from race conditions, multiple threads can also cause starvation which occurs when one thread is continually denied access to a resource because of other threads or system level-problems that gets higher priority.

## Channel failures

Method|Solves
--|--
Checksums|message corruption
Acknowledgements, time-outs, re-transmissions|lost messages
Sequence numbers|duplicated/re-ordered messages
