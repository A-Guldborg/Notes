# Indexes

## Queries

### Point queries

When the `where` clause looks for a specific equality. Can return `0..n` records, depending on the number of data records it matches.

### Range queries

When the `where` clause looks for value between two values, like:

```sql
where percentage between 0 and 100;
```

## Full table scan

Slow, reads every record and filters according to the `where` clause.

## Indexation

Optimizes queries.

Created by the following syntax:

```sql
create index <name> on <table> (<column list>);
```

Mostly using a hash or a B+-tree. A B+-tree can be considered as an extended KD-tree, where the `KD` (e.g., `2D` or `3D`) is the number of columns that is indexed and where each node contains more than two subnodes. A B+-tree is a balanced tree.

### Search key

Not to be confused with other keys (candidate, primary, foreign). These are the keys that the index is sorted on.

### Clustered index

Also called primary index. Data records are sorted according to the index search key.

Really efficient for point and range queries on the search key.

### Unclustered index

Data records are not stored sorted like in the clustered index. From the nature of clustered indexes, only one such can exist and often on the primary key of the relation.

Not good for wide range queries, but very efficient for most point queries and for narrow range queries.

As a rule of thumb: Only if the expected result is to return less than ~1% of all data records.

### Covering indexes

When the index contains all the information that the query expects, so it is only necessary to load the index file and not the actual data files.

Only relevant when queries contains specific columns or count(*). Since it does not read from the data files at all, it does not matter if the index is clustered or not.

## Downsides to indexes

Takes time to maintain the index if the relation is often changed or gains new records. (Important)

Takes up space for the B+-tree data structure including pointers to the extra data records. (Not very important)
