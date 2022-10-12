# Design Patterns

What is and isn't a design pattern depends on the point of view, and someone's design pattern might be someone else's basic building block.

## Cheat sheet

See [Design Patterns Card](http://www.mcdonaldland.info/files/designpatterns/designpatternscard.pdf) for a cheatsheet of design patterns.

## Observer

Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

## Singleton

The intent is to ensure a class only has one instance, and provide a global point of access. In UML, this can be shown with a getInstance method which is static, visualized by an underlined method signature.

## Strategy

Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

## Facade

Intent is to provide a unified interface to a set of interfaces in a subsystem.

## Mediator

Define an object that encapsulates how a set of objects interact. Mediator promotes loose coupling by keeping objects from referring to each other explicitly, and it lets you vary their interaction independently.

## Null Object

By using this pattern, we can ensure that functions always return valid objects, even when they fail. Those objects that represent failure do “nothing.”

## Template Method

Define the skeleton of an algorithm in an operation, deferring some steps to subclasses. Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.

## Command pattern

Encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.
