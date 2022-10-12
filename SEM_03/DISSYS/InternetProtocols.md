# Protocols

## TCP

Guarantees packets are received. Uses 3-way handshake.

## UDP

Sends out a lot of packets and does not guarantee receival, convenient for things like streaming or DNS servers that gets many queries.

## HTTP

Uses TCP.

## Domain Name Server (DNS)

Server which translates website URLs to IP addresses, such as converting `www.facebook.com` to `31.13.72.36`.

## Border Gateway Protocol (BGP)

A protocol that has a prefixed route to a specific DNS or other autonomous system.  
Can be removed from the DNS to protect against DDOS / a big number of requests.

The BGP can be found using the `dig` command in a shell, which will return the route for a given website. By adding a DNS IP address first, such as 1.1.1.1 (Cloudflare), it is possible to specify from which DNS the BGP should be found.

```sh
dig www.facebook.com

dig @1.1.1.1 www.facebook.com
```
