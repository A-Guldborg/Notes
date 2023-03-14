# (1) Concurrency (Races & Deadlocks)

## Race Conditions

A race condition is when a program does not give the desired outcome for some interleavings of the operations of multiple threads/goroutines.

One way to force an issue like this is to spawn `n` threads that have a loop that increments a shared variable by 1 x times.

Then the variable should have the value x * the number of threads spawned, but in reality the code `t = t + 1` will take a very brief moment from reading the variable `t` before adding `1` and saving it to `t` again. Thus, multiple threads can access and read the variable with the same value `y`, but when adding `1` they all get to `y+1` and write this amount to the variable.

### Races solution

Channels!!

Use one routine that is the only one that can access the "shared" variable, then have a channel from other threads/goroutines to this one, that can make sure only one process at a time accesses the variable.

However routines will lock if they are waiting for an answer in a channel that nobody is communicating to.

### Starvation

If a resource is accessed by multiple threads and often, this will cause some processes to wait in order to gain access, but if the threads with higher priority requests to access the resource again and again before the lower-priority process gets access, then this lower-priority thread/process will be in a starvation.

## Deadlocks

Deadlocks are when a program halts because multiple processes are waiting for eachother.

An example of this issue was encountered in the dining philosophers issue, where having 3 philosophers that wants to request access to a fork on both sides meant that the philosophers would deadlock. If it was implemented such that a philosopher always asks left first, then right, and if it gets access to both it will eat and release them again, then a deadlock can occur:

All three philosophers ask left first, but since they all ask left they will likely all be allowed access to the left fork but then fail when attempting to take the right fork, waiting for it to be released but since it is held by another philosopher, it will not be released until a chain reaction happens.

### Deadlocks solution

Generally, one can avoid having circular dependencies - that is of course not relevant for the dining philosophers problem, but in general making sure multiple threads depend on eachother can help avoid this issue.

When circular dependencies are necessary, (random) timeouts can help. In the context of the dining philosophers problem, that would mean that a philsopher would maybe wait a random amount of time to access the second fork otherwise release the first one again. Once this is released, it can immediately be grabbed by the philosopher that was waiting for this fork - unless this philosopher also had released their other fork first, but in that case the third philosopher should have grabbed that fork.

### Livelocks

If the timeout for taking the forks is not random, it might cause a livelock where the philosophers continously changes their state in order to release access to the other philosophers, but doing so simultaneously and then simultaneously attempting to access the same resource again such that they end up in the same situation. This is a livelock meaning that the processes changes states trying to solve the deadlock but doing it in a way such that they are still not able to progress.
