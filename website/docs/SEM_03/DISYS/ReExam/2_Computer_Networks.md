# (2) Computer Networks

## Protocols

Processes adhere to protocols going through layers from application to physical layer, each layer adding (or re-constructing from) functionality

Popular methods are TCP and UDP, where TCP is reliable (asserts happened-before and packet-receival) whereas UDP is faster and often used in gaming and streaming etc.

### SYN/SYN+ACK (3-way handshake)

TCP uses acknowledgemenets to make a connection-oriented internet protocol.

The client will initially synchronize (SYN) with the server. The server will send an synchronization + acknowledgement (SYN+ACK) back, and lastly the client will send an acknowledgement (ACK) back. Not before that, data can be sent back and forth.

This enables having multiple processes connect to a server on the same port.

The synchronizations includes randomly generated sequence numbers which are used in the subsequent data transmission to indicate in which order data should be processed. This can be done since the initial synchronization from the client contains a window size which refers to the amount of data before a new 3-way handshake must be made.

## IPv4

Connection-less where each process has an IP address of 4 bytes (8 bit numbers). `(2^8=256)`  
IPv4 ranges from 0.0.0.0 to 255.255.255.255

Each process, e.g. laptop, computer, server etc. have a different IP. Domain Name Servers keep track of which IP a URL (e.g. www.google.com) corresponds to.

## IPv6

The IPv4 protocol only allows up to just above 4 billion IP addresses and thus not enough when we are getting internet of things in our homes. IPv6 is made to allow for enourmously many IP addresses, but requires the whole network to be prepared for them meaning it is not possible to use consistently yet.

## Ports

A computer has some ports, and you can communicate with other processes (peers/servers) through a agreed-upon port.

We have used ports in this course to e.g. "mock" a distributed network, having different terminals running an application on different ports, simulating that messages are sent out of a port and into the same server on a different port.
