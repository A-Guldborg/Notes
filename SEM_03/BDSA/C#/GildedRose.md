# Gilded Rose

Assignment 05

Different items that have different business rules:

- Some lose value over time
- Some gain value over time
- Some have a sell-before date
- Now implement an item that loses value twice as quick

## Refactoring

Make the change easy, then make the easy change.

Make unit tests, then implement the change and test that it still works, then implement the easy change and test it.

When testing, use the following package and test using CollectCoverage to see the test coverage:

```sh
dotnet add package coverlet.msbuild
dotnet test /p:CollectCoverage=true
```
