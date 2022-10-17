# Dependency Injection

## Repository pattern

Create, read, update, delete on domain objects/entities\
Usually one repository per entity\
Debatable if it should have a `Save()`-method.

## Inversion of Control

### Constructor Injection

Insert dependencies in the constructor using interfaces. (Recommended)

### Property (Setter) Injection

Using a public setter means code can change the implementation(dependency) during runtime which is rarely favourable.

An example of this could be when testing console apps;

```c#
using var writer = new StringWriter();
Console.SetOut(writer);
```

### Interface Injection
