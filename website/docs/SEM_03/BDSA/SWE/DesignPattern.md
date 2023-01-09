# Design Patterns

What is and isn't a design pattern depends on the point of view, and someone's design pattern might be someone else's basic building block.

It is a reusable solution to a commonly occuring problem in software design.

## Cheat sheet

See [Design Patterns Card](http://www.mcdonaldland.info/files/designpatterns/designpatternscard.pdf) for a cheatsheet of design patterns.

## Creational Design Patterns

Design patterns that deal with object creation mechanisms, trying to create objects in a manner suitable to the situation.

### Singleton

The intent is to ensure a class only has one instance, and provide a global point of access. In UML, this can be shown with a getInstance method which is static, visualized by an underlined method signature.

### Factory

Consider an example of a Zoo with different Animals, where we want to log whenever we get a new Animal upon `new Dog/Cat/Zebra();`. Using a Factory, where an abstract AnimalFactory exists, which has a logging method that takes the animal type as input, then we have a subclass for each concrete Animal type which has a `CreateAnimal()` method that calls the superclass' logging method such that there are no code duplication of how to do the logging of animal creation, and that clients creating animals does not need to consider what else should be done when creating animals (That is the factories' responsibility).

### Builder

When creating a new object, e.g. a House, this object might have many properties such as the number of doors, windows, rooms etc. Instead of requiring the clients to input this upon creation, a Builder pattern can be used such that all these values are configured seperately before creation. This also increases readability and flexibility of the code, see the example below:

```csharp
public class Person
{
  public string FirstName { get; private set; }
  public string LastName { get; private set; }
  public int Age { get; private set; }
  public string Address { get; private set; }
  public string PhoneNumber { get; private set; }

  private Person(Builder builder)
  {
    this.FirstName = builder.FirstName;
    this.LastName = builder.LastName;
    this.Age = builder.Age;
    this.Address = builder.Address;
    this.PhoneNumber = builder.PhoneNumber;
  }

  public class Builder
  {
    public string FirstName { get; private set; }
    public string LastName { get; private set; }
    public int Age { get; private set; }
    public string Address { get; private set; }
    public string PhoneNumber { get; private set; }

    public Builder(string firstName, string lastName)
    {
      this.FirstName = firstName;
      this.LastName = lastName;
    }

    public Builder Age(int age)
    {
      this.Age = age;
      return this;
    }

    public Builder Address(string address)
    {
      this.Address = address;
      return this;
    }

    public Builder PhoneNumber(string phoneNumber)
    {
      this.PhoneNumber = phoneNumber;
      return this;
    }

    public Person Build()
    {
      return new Person(this);
    }
  }
}

// Usage
Person person = new Person.Builder("John", "Doe")
  .Age(30)
  .Address("123 Main Street")
  .PhoneNumber("123-456-7890")
  .Build();
```

## Structural Design Patterns

Design patterns that deal with object composition, creating relationships between objects to form larger structures.

### Bridge

This is used if for instance a class is composed of different elements/parts/properties. An example is when classes BlueBall, YellowBall, BlueSquare, YellowSquare exists, making the number of classes be equal to the number of different colors multiplied by the number of different shapes. Once there are 5 different colors, adding a new shape requires 5 new classes to be made, one for each color with the shape.

The solution is to select one of them, e.g. the shape, and have each shape have a property of color, such that adding a color requires adding exactly one new class that uses an interface `Color`, and likewise for a shape extending the interface `Shape` and having a property `Color`.

### Adapter

A class (the adapter) is created and used to convert data from the program to the form that is required in another component that takes input in a different format. This could be converting data from objects to JSON or XML. Normally it could be possible to change the input type in the data processing component, but if other components rely on the data processing component, or if you don't have access to the code of this component (external dependency), then an adapter can be used.

### Composite

When some class can contain itself recursively, i.e. a box containing boxes and products, and there is a method that needs to access all elements (including nested elements) of a given box, then this can be handleded recursively. When the method is called, the box will first ask it's boxes the answer to the query, then answer itself with it's own contents/products and returning the answer aggregated.

Taking the outermost box and querying the method `.Count()` will thus recursively call `.Count()` on it's contained boxes and then return the sum of these answers plus the count of products in the box, giving the total number of contained products recursively with a very simple query.

### Facade

Intent is to provide a unified interface to a set of interfaces in a subsystem.

When using an external framework, but only requiring a small set of the methods from this framework, then a facade pattern allows for only the facade pattern to know this specific framework, and changing the framework from e.g. `libgit2sharp` to `Github` would mean only the facade class needs to change and not multiple files.

### Gateway

Similar to the [Facade pattern](#facade) but exposes only a limited set of the complex subsystem methods, not all.

### Proxy

Consider a class/object that takes a lot of system resource, and thus preferably only lives when methods of the object are called and not inbetween. Instead of asking all clients that uses this object to get rid of the abundant system resources when it is not required, adding a proxy with the same interface as the object itself can be beneficial. This proxy then handles starting the resources up and disposing of them afterwards, meaning that all clients can abstract from the usage of system resources. Clients just needs to use the proxy instead of the object itself.

## Behavioral Design Patterns

Design patterns that deal with communication between objects, what goes on between objects and how they operate together.

### Visitor

A pattern used to add specific functionality but without adding new possibly breaking changes. Also, because currently existing classes should adhere to the single responsibility principle, we don't want to add new functionality that does not relate to the classes current responsibility. So if for instance we want to be able to export a data structure to XML format, we will use the Visitor pattern by having a Visitor interface with methods for each type of node in the data structure, such as `doForCity(City c)`, `doForCountry(Country c)` etc.

Then we create an XMLVisitor which extends the Visitor, and then define how to export to XML format for each type of node with the correct node type as input. Then each node type will implement a method similar to this (assuming we do this on the City class):

```csharp
void accept(Visitor v)
{
    v.doForCity(this);
}
```

This way, we do not break the single responsibility priniciple from SOLID, and we do not need to use casting. One client might want this code added to their production, while another client might want to have a similar visitor but for JSON which will then be easy to have next to the XMLVisitor.

### Observer

Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically. Can be considered like a subscription device, such that different objects that wants to know when an object updates can subscribe to it. This could be a logger, that wants to log each time an object updates - not necessarily the same (Adheres to single responsibility principle). Could also be used in a video game, where players can subscribe to information on when the boss fight begins, new levels are added etc. Then the boss fight object would have a list of subscribers, logs etc which it would notify and pass itself as an object to it's subscribers who will then query the information they need from the notification.

### Strategy

Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it. An example could be a map service, which is a map of e.g. a country. Then the user wants to be able to find the best route from A to B, but with different options that alters the algorithm. Maybe the user wants to find the shortest route to save gas money, or maybe the user wants to find the quickest route to save time. Maybe the user wants to find the scenic route.

Each routing algorithm would then be it's own strategy, and the map would at any given time has a link to one of the strategies. A strategy can be swapped out if the user clicks a specific button, and once the user asks for a route from A to B, then the map will just call the `route(A, B)` on whatever strategy it currently holds a link to.

### Mediator

Define an object that encapsulates how a set of objects interact. Mediator promotes loose coupling by keeping objects from referring to each other explicitly, and it lets you vary their interaction independently.

A mediator could be in a login form, where the mail should be validated before clicking `Login`. Instead of coupling the `Login`-button to the email-field, such that the validation happens upon click, both fields only communicate with a `Mediator`. This Mediator will then know, that when the Login-button is clicked, it should either verify the contents of the email-field itself or ask the email-field to validate itself. This also means that a login-button can be reused other places in the code with a different dependency-injected mediator to avoid code duplication.

### Null Object

By using this pattern, we can ensure that functions always return valid objects, even when they fail. Those objects that represent failure do “nothing.”

The way this works is by having a replica of the objects, so for instance if the class `Animal` exists with a set of subclasses such as `Dog` or `Cat`, then no fields should ever be `Animal x = null;` but instead `Animal x = new NullAnimal()` that inherits from the Animal class. But the `NullAnimal` class only has empty methods. If the class `Animal` has concrete methods, then the `NullAnimal` should override these with empty bodies. This way null reference exceptions are avoided, however it might also be more unclear when bugs are relating to data or the code itself.

### Template Method

Define the skeleton of an algorithm in an operation, deferring some steps to subclasses. Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.

An example could be pulling and processing data from different file types, where opening the file and retrieving the data might be different for each file type, but processing the data and saving it to a database is equal. Thus, a base class can have these methods implemented while the subclasses overrides the steps that differ from file to file, then on runtime select the correct Strategy that matches the filetype used.

### Command pattern

Encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.

This means that there will be a `Command` interface that has a method to execute the command, then concrete command classes such as a `SaveCommand` will then implement the business logic that saves a file when executed. This command can then be dependency injected into buttons, commands such as `ctrl + s` or into an automatic repeating command executor, such that multiple different components can issue the command without knowing which areas it affects or how. The command then knows which application, component etc. that it should use the command on.
