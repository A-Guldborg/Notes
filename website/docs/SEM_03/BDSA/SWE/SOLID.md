# Solid Principles

## Single-Responsibility Principle (SRP)

A class should have only one reason to change.

This was previously know as the Do One Thing and Do It Well (DOTADIW).

Similar to `Objects First with Java`'s `Method Cohesion`, which states that a method is responsible for one, and only one, well-defined task.

## Open/Closed Principle (OCP)

From Robert C. Martin et al. `Agile Principles, Patterns and Practices in C#`:

Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification.

In other words;

"You should be able to extend the behavior of a system without having to modify that system."

## Liskov Substitution Principle (LSP)

Subtypes must be substitutable for their base types.

## Interface Segregation Principle (ISP)

Clients should not be forced to depend on methods they do not use.

## Dependency-Inversion Principle (DIP)

A. High-level modules should not depend on low-level modules. Both should depend on abstractions.

B. Abstractions should not depend upon details. Details should depend upon abstractions.

Depend upon Abstractions. Do not depend upon concretions from `JetBrains: What to look for in a Code Review: SOLID Principles`
