# Design principles

## Unix Philosophy

1. Make each program do one thing well. To do a new job, build afresh rather than complicate old programs by adding new "features".
1. Expect the output of every program to become the input to another, as yet unknown, program. Don't clutter output with extraneous information. Avoid stringently columnar or binary input formats. Don't insist on interactive input.
1. Design and build software, even operating systems, to be tried early, ideally within weeks. Don't hesitate to throw away the clumsy parts and rebuild them.
1. Use tools in preference to unskilled help to lighten a programming task, even if you have to detour to build the tools and expect to throw some of them out after you've finished using them.

## Keep It Simple, Stupid (KISS)

It should be possible to fix/maintain for an average engineer using basic tools.

The principle is exemplified by the story of a team of design engineers, who should build a jet that could be repaired by an average mechanic in the field under combat, with only basic tools.

## You Aren't Gonna Need It (YAGNI)

Don't add functionality until deemed necessary.

## CUPID Design Principles

* Composable: plays well with others
* Unix philosophy: does one thing well
* Predictable: does what you expect
* Idiomatic: feels natural
* Domain-based: the solution domain models the problem domain in language and structure

## Code analysis tools

### NDepend

A tool that can be integrated in e.g. VSCode, which asses how [SOLID](SOLID.md) the source code is.
Measured on the (S)size/complexity of methods, types etc., that the code doesn't use (O)derivates, (L)doesn't use NotImplementedException, (I)avoids big interfaces, (D)has high cohesion and low coupling.

Might not strictly measure SOLID but is a proxy for that.

### BetterCodeHub

Analysis tool for code that measures the following ten principles:

1. Write Short Units of Code
1. Write Simple Units of Code
1. Write Code Once
1. Keep Unit Interfaces Small
1. Separate Concerns in Modules
1. Couple Architecture Components Loosely
1. Keep Architecture Components Balanced
1. Keep Your Codebase Small
1. Automate Tests
1. Write Clean Code
