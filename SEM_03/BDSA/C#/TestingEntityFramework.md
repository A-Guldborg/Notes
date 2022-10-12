# Testing Entity Framework

Use `SQLite` for in-memory testing
Do this by adding, in test class, the following dotnet command:

```sh
dotnet add package Microsoft.EntityFrameworkCore.Sqlite
```

Create the connection by doing the following code:

```c#
private readonly Sqliteconnection _connection;
private readonly Context _context;
// Constructor
public Tests()
{
    _connection = new SqliteConnection("Filename=:memory:");
    _connection.Open();
    var builder = new DbContextOptionsBuilder<Context>();
    builder.useSqlite(_connection);
    _context = new Context(builder.Options);
    _context.Database.EnsureCreated();
}

public void Dispose() {
    _context.Dispose();
    _connection.Dispose();
}
```

## Why not `ContextOptionBuilder<T>().UseInMemoryDatabase`?

Because Microsoft.EntityFrameworkCore

## Shared database entries

Only database entries from the constructor will be available in tests, as tests are atomic and `XUnit` creates a new object of the test class (using the constructor) and then runs the test method for each test. This ensures that a test deleting an entry in the database, or a test adding an entry in the database, will not cause other tests to fail from constraints such as `Unique`.

## Data Transfer Objects (DTO)

An object type that builds the transition between the database, which uses IDs, and the program, which does not use IDs.
