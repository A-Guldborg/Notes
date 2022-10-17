# Dining philosophers

This is the golang-solution to the dining philosophers problem, by the "Go program" group, as part of BSDISYS1KU 2022.

## Running the program

The program can be run from the command line using the following command:

```bash
go run dining_philosophers [<philosophers>] [<minimum_eats>]
```

- If `philosophers` is not defined, it will default to 5.
- If `minimum_eats` is not defined, it will default to 3.

The program accepts zero or two arguments, you can't only supply philosophers.

eg.

```bash
go run dining_philosophers 15 300
```

## Output

The program will print the state changes of every philosopher, and when done will print the time taken for all philosophers to eat the minimum amount of times.
