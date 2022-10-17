# Terms

## Ch. 1 - Basic terms

|Term|Explanation|
|--|--|
|Database|Collection of related data within a business or problem setting|
|Database Management System (DBMS)|Software package that allows to define, create, use and maintain a database|
|Database System|Combination of the database and the DBMS|
|Database model / schema|Describes the types of data collected, their relationships, constraints etc.|
|Database state|Current data|
|Conceptual Data Model|High-level depiction of data items and characteristics, used by information architect and business user to create adequate database models. Often using EER or objected oriented models|
|Logical Data Model|Translation of the conceptual data model into an implementation specific environment.|
|External Data Model|Which applications/views/users uses which data items from the logical data model|
|Internal Data Model|The physical (actual) organization of data|
|Three-Layer-Architecture|Consists of the conceptual/logical layer, the external layer and the internal layer from the above concepts|
|Catalog|Contains the definitions of the three layer architecture to ensure consistency|
|Data definition language (DDL)|The language used to express the database’s architectural layers|
|Data manipulation language (DML)|The language used to retrieve, update, add or remove data|
|Data Independence|Changes in data definitions have minimal or no impact on applications|
|Physical Data Independence|Changes in the internal layer have no effect on applications, views or the logical layer|
|Logical Data Independence|Changes in the logical layer have minimal implications on the applications.|
|Structured Data / Unstructured Data|Data in databases are structured such that it is possible to search for a specific name, where as unstructured data could be text-files where it is difficult or impossible to figure out which part of a sentence is a person's name when the term “Name” appears.<br></br> Some data are neither completely structured or unstructured, such as NoSQL and XML databases|
|ACID|To ensure reliable database transactions, DBMS must support Atomicity, Consistency, Isolation & Durability|
|Atomicity|Either the whole transaction is executed or not at all|
|Consistency|A transaction brings the database from one consistent to another.|
|Isolation|The effect of concurrent transactions should be the same as if they were executed in isolation|
|Durability|A successful transaction can be made permanent under all circumstances|

## Ch. 6 - The relational model / Relational Databases

|Term|Explanation|
|--|--|
Relational Model|Formal data model with a mathematical foundation based on set theory and first-order predicate logic.
Relation / Tables|Set of tuples that represent a similar, real-world entity. Mathematically, a relation is a set and thus the tuples are distinct and unordered. 
Tuple / Record / Entity|Ordered list of attribute **values** that describe one entity
Domain|Specifies the range of valid values, e.g. numbers from 0-1000, months from 1-12 etc.
Superkey|No two tuples should contain the same value in this column/attribute
Candidate key|Unique/distinct values. There can be multiple (e.g. product number and product name)
Primary Key|One of the candidate keys used to identify connections to other relations and satisfying a not null constraint.
Alternative Key|Candidate keys that are not primary keys. 
Foreign Key|A value in a tuple that defines the connection to another tuple in another relation. E.g. an order has a supplier, so the order relation contains a value “supplierID” so each tuple in the set of orders contains a connection to one supplier.<br></br>Foreign Keys are used to remove multiple values as a value in a tuple, e.g. when a supplier must supply multiple different orders by having “orderID” as a column in the relation of suppliers. This is a 0:N relation (there can be between 0 and N orders using any supplier). <br></br>For N:M relations, e.g. the relations suppliers and products where some products can be retrieved from multiple suppliers, a new relation such as ‘ProductSupplies’ is created which has the two foreign keys “productID” and “supplierID” and there can now be N x M different tuples in this relation.
Relational Constraints|Integrity constraints that ensures correct and consistent data. Any breach is reported.

## Ch. 7 - Structured Query Language (SQL)

Term|Explanation
--|--
Free-form language|A language (like SQL) that requires no special indentation or other form. SQL is also (mostly) case-insensitive.
SQL Schema|A grouping of tables and other database objects which logically belong together. It is defined by a schema name and an authorization identifier.<br></br>A schema can be created using SQL DDL:<br></br>`CREATE SCHEMA <Name> AUTHORIZATION <User>`<br></br>A table can be created within a schema:<br></br>`CREATE TABLE <Schema>.<Name> ...`
Authorization Identifier|Indicates the user (account) who owns the schema who has full access within the context of the schema. 
Data types|Data types for columns/attributes within a table such as:<br></br>Char, Varchar, Int, Smallint, float, double, date, datetime, time, boolean & blob
Custom domain / custom data type|Define a custom valid domain using SQL DDL:<br></br>`CREATE DOMAIN <Name> AS <Data type> CHECK (VALUE IN(<Comma separated list of values of the specified data type>))`
Referential Integrity Action|Defines the action when a foreign key references a tuple/record that is updated or deleted. See below two actions.
`ON UPDATE/DELETE CASCADE`|All records referencing the updated/deleted record will also be updated/deleted (updating the foreign key)
`ON UPDATE/DELETE RESTRICT`|It is not possible to update/delete this record as long as other records reference it
`DROP` command|Used to delete a database object by defining the type after the drop command and then the name. The above cascade and restrict options similarly defines what should happen in case there are still references present.
`ALTER` command|Used to modify table columns/attributes by adding TABLE and then a table name. Can be used with ADD, DROP or ALTER and then the name of a column. When adding, the data type must be written. 
