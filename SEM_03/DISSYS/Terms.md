# Terms

|Term|Explanation|
--|--
Models|A model of a software architecture in three abstraction levels:<br>-Physical models<br>-Architectural models (Which servers/clients communicate with which)<br>-Fundamental models (How do we handle interactions between two server/clients etc)
Synchronous vs. Asynchronous|
Security Model|Sending communication through open networks should handle security so that external entities cannot intercept, understand, misuse, edit the communication sent
Reliability|The application should continue to work correctly despite hardware, software or human errors. 
Scalability|When the system grows, there should be reasonable ways to deal with it.
Maintainability|It should be possible for multiple developers to productively work on the application without disrupting the current behaviour.
Load parameter|The load parameters are used to monitor performance of an application. Load parameters are chosen to be the most expensive action such as requests to a web server or the ratio between reads and writes in a database.
System resources|Physical resources (CPU; memory, bandwidth etc.) that might need to be increased when scaling. 
Latency|Waiting time for a process, where it is not doing anything
Bandwidth|Bits per second that can be communicated
Jitter|Variation of delivery time
Response time|The time between a user makes a request until the result is shown back to the user. The response time varies a lot, but can be considered a distribution of values instead of a single value. Response times should preferably be reported as percentiles (e.g. median), not as an arithmetic mean. 
Service Level Objectives (SLO)|The intent of the applications expected service levels
Service Level Agreements (SLA)|The agreement with a customer to the consequences/rights when the application does not live up to the SLO.
Head-of-line blocking|When a few slow processes block faster processes to be performed, making small/fast processes have slower response times than they should.
Tail latency Amplification|When a request requires multiple calls for the backend services and the response time is slow due to one or few ongoing processes, despite many faster processes.
Elastic|Dynamically/Automatically add computing resources when necessary.

## Ch. 1 - Reliable, Scalable and Maintainable Applications
Most applications are more data-intensive than compute-intensive.  
With different requirements for the application, several data systems are often used.  
It is often the application code’s responsibility to keep caches and indexes across data systems in sync.

*Reliability* is an application’s ability to continuously work despite faults. Faults are when a small part of the application deviates from its specifications, whereas a failure is when the whole system stops providing the required service to the user. It is impossible to completely remove all faults, but an application should aim to prevent faults turning into failures.  
Faults/Errors:

- Hardware
- Software
- Humans

*Scalability* is an application’s ability to continue being reliable despite growing in size.  
Consider Twitter, where the number of reads is far outweighing the number of writes. Thus in Twitter’s case, it makes sense to increase the workload when writing so that a read becomes easier. This is done by writing to all the tweeter’s followers. But due to celebrities with many followers, this can cause performance issues when celebrities tweet. Instead, these people will all add to a database/table which will be fetched when a user follows the given tweeter and combined with the person’s own database.  

*Maintainability* is the cost of fixing bugs, keeping systems operational, adapting software to new platforms, modifying it for new use cases etc. on released software. This consists of three parts:  

- Operability (Being easy to continue systems operating smoothly)  
- Simplicity (Make it easy for others to understand the system)  
- Evolvability (Make it easy to develop further features to the system or changing the system based on new use cases)
