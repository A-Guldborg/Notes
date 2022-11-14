# Homework Assignment 3

Hand-in date November 14th

## 1. SQL DDL

1. For people, the database should keep track of their ID, name, address, phone number, date of birth (DOB), and date of death (DOD). The default value of DOD is (thankfully!) NULL.

```sql
CREATE TABLE People (
    ID SERIAL PRIMARY KEY,
    Name VARCHAR NOT NULL,
    Address VARCHAR,
    PhoneNumber INTEGER,
    DOB DATE,
    DOD DATE DEFAULT NULL
);
```

2. People are further divided into members of WASP and enemies; each person is a member, an enemy, or possibly both. For members, the starting date of membership is registered. Note that WASP members cannot leave the party, even in death! For enemies, the reason is registered.

```sql
CREATE TABLE Member (
    ID INTEGER PRIMARY KEY REFERENCES People(ID),
    Start_date Date NOT NULL
);

CREATE TABLE Enemy (
    ID INTEGER PRIMARY KEY REFERENCES People(ID),
    Reason VARCHAR NOT NULL
);
```

3. For each WASP member, a list of their assets is maintained, which could be used to achieve the WASP agenda. Each asset of a person is identified with the name of the asset, but text fields must also be maintained to a) describe the asset in more detail and b) describe how the asset could potentially be used.

```sql
CREATE TABLE Asset (
    Name VARCHAR NOT NULL,
    MemberID INTEGER REFERENCES Member(ID)
    Detail VARCHAR,
    Uses VARCHAR
);
```

4. Events and relationships between various people (e.g. accidents, business partnership, marriage, mortal enemies, ... ) are registered in a fairly flexible way. The WASP party uses the general term ‘linkings’. Each linking is an entity that is assigned an ID, name, type and a more detailed description. Multiple people may participate in each linking, but the minimum number of people per linking is two, and each person may participate in multiple linkings with different people.

```sql
CREATE TABLE Linking (
    ID SERIAL PRIMARY KEY,
    Name VARCHAR NOT NULL,
    Type VARCHAR,
    Description VARCHAR
);

CREATE TABLE Participate (
    ID SERIAL PRIMARY KEY, // Necessary for requirement 5
    PersonID INTEGER FOREIGN KEY REFERENCES People(ID),
    LinkingID INTEGER FOREIGN KEY REFERENCES Linking(ID),
    PRIMARY KEY (PersonID, LinkingID)
);
```

5. Each participation of a person in a linking is monitored by one member. The monitoring member never changes.

```sql
CREATE TABLE Monitor (
    ParticipateID INTEGER PRIMARY KEY REFERENCES Participate(ID),
    MemberID INTEGER FOREIGN KEY REFERENCES Member(ID)
);
```

6. The WASP party has a list of roles, each with a unique ID and a unique title, as well as a monthly salary. Members take turns filling the roles: each member may fill multiple roles at any given time, and multiple members may fill the same role. However, members may be appointed to each role only once. The start and end dates of each appointment to a role are always known in advance and recorded.

```sql
CREATE TABLE Role (
    ID SERIAL PRIMARY KEY,
    Title VARCHAR NOT NULL,
    Salary INTEGER NOT NULL
);

CREATE TABLE Serve_in (
    RoleID INTEGER,
    MemberID INTEGER,
    Start_date DATE NOT NULL,
    End_date DATE NOT NULL,
    PRIMARY KEY (RoleID, MemberID),
    FOREIGN KEY (RoleID) REFERENCES Role(ID),
    FOREIGN KEY (MemberID) REFERENCES Member(ID)
);
```

7. The WASP party keeps track of other political parties, both domestically and abroad. For each party, the country and name are a unique identifier, but the party also has an ID that serves as a primary key. For each such party, at each time (represented by start and end dates), exactly one WASP member monitors the developments in the external party.

```sql
CREATE TABLE Party (
    ID SERIAL PRIMARY KEY,
    Country VARCHAR NOT NULL,
    Name VARCHAR NOT NULL,
    Monitor INTEGER FOREIGN KEY REFERENCES Member(ID),
    Start_date DATE NOT NULL,
    End_date DATE,
    UNIQUE (Country, Name),
    UNIQUE (Start_date)
);
```

8. The WASP party has sponsors. Each sponsor has an ID, name, address, and industry attribute. Each sponsor may give grants to a number of WASP members. For each grant, the date the grant is awarded is registered, as well as the amount and a text field called ‘payback’ that describes what the sponsor expects in return. Each sponsor may give multiple grants to multiple members, and each member can receive multiple grants from multiple sponsors. However, each grant is for a single person and each sponsor can give multiple sponsorships to each member, but at most one per day.

```sql
CREATE TABLE Sponsor (
    ID SERIAL PRIMARY KEY,
    Name VARCHAR NOT NULL,
    Address VARCHAR NOT NULL,
    Industry VARCHAR NOT NULL
);

CREATE TABLE Grant (
    Date DATE,
    SponsorID INTEGER FOREIGN KEY REFERENCES Sponsor(ID),
    MemberID INTEGER FOREIGN KEY REFERENCES Member(ID),
    Amount INTEGER NOT NULL,
    Payback VARCHAR NOT NULL,
    PRIMARY KEY (Date, SponsorID, MemberID)
);
```

9. Each grant may be reviewed by one member of WASP. The date of the review is decided when the grant is registered, typically one year in the future. At review time, the grant is assigned a numerical grade from 1 to 10, depending on how well the WASP member executed the payback.

**NOTE: Includes extension of `Grant` table from above**

```sql
CREATE TABLE Review (
    ReviewID SERIAL PRIMARY KEY,
    Date DATE NOT NULL,
    Grade INTEGER,
    MemberID INTEGER FOREIGN KEY REFERENCES Member(ID)
);

ALTER TABLE Grant ADD COLUMN ReviewID INTEGER DEFAULT NULL;
ALTER TABLE Grant ADD CONSTRAINT review_fk_constraint FOREIGN KEY (ReviewID) REFERENCES Review(ID);
```

10. All parties and all enemies are two categories of opponents; opponents have an ID. Some WASP members may be assigned to oppose opponents. This opposition appointment has a start date and an end date (the latter may be unknown in the case of open-ended assignments).

```sql
CREATE TABLE Opponent (
    ID SERIAL PRIMARY KEY
);

ALTER TABLE Enemy ADD COLUMN OpponentID INTEGER DEFAULT NULL;
ALTER TABLE Party ADD COLUMN OpponentID INTEGER DEFAULT NULL;
ALTER TABLE Enemy ADD CONSTRAINT enemy_fk_opponent FOREIGN KEY (OpponentID) REFERENCES Opponent(ID);
ALTER TABLE Party ADD CONSTRAINT party_fk_opponent FOREIGN KEY (OpponentID) REFERENCES Opponent(ID);

CREATE TABLE Opposes (
    Start_date DATE NOT NULL,
    End_date DATE,
    MemberID INTEGER FOREIGN KEY REFERENCES Member(ID),
    OpponentID INTEGER FOREIGN KEY REFERENCES Opponent(ID),
    UNIQUE (MemberID, OpponentID)
);
```

## 2. Practical Normalization

### Steps

1. Find all the important FDs in the relations, given the constraints and simplifying assumptions that are listed in detail below.

Note: Significant ones are written `like this`

pid -> pn  
hid -> hs  
`hid -> hz`  
`hid -> hc`  
`hz -> hc`  

2. Decompose the relation until each sub-relation is in 3NF/BCNF, while preserving all non-redundant FDs. Write down the resulting schema description in a simple Relation(columns) format.

`Rentals(pid, hid, pn, s, hs, hz, hc)`

Should be changed to:

`Rentals(pid, hid, pn, s, hs, hz)`

`Decomposed(hz, hc)`

3. Write the detailed SQL commands to create the resulting tables (with primary keys and foreign keys) and populate them, by extracting the relevant data from the original relations.

```sql
CREATE TABLE NewRentals (
    pid INTEGER NOT NULL,
    hid INTEGER NOT NULL,
    pn VARCHAR NOT NULL,
    s INTEGER NOT NULL,
    hs VARCHAR NOT NULL,
    hz INTEGER NOT NULL,
    primary key (pid, hid)
);

INSERT INTO NewRentals
SELECT pid, hid, pn, s, hs, hz
FROM Rentals;

CREATE TABLE decomposed (
    hz INTEGER PRIMARY KEY,
    hc VARCHAR NOT NULL
);

INSERT INTO decomposed
SELECT DISTINCT hz, hc
FROM Rentals;

SELECT * FROM Rentals;

SELECT * FROM NewRentals
JOIN decomposed ON decomposed.hz = NewRentals.hz;
```

4. Select the correct normal form for the decomposed schema.

From `2nd normal form` to `Boyce-Codd Normal Form` (BCNF).

## 3. Index Selection

Consider the following relation with information on parts:
`Part(id, descr, price, stock, ...)`

Select the index that gives the best performance, or `no index` if a full table-scan would yield better performance.

**Available indexes:**

a. Part(id)  
b. Part(stock)  
c. Part(price)  
d. Part(stock, price)  
e. Part(stock, price, id)  
f. Part(stock, price, descr)  
g. No index  

**Query 1:**

```sql
select id, descr, price
from Part
where stock > 33;
```

b: `Part(stock)`

**Query 2:**

```sql
select stock
from Part
where price = 500;
```

c: `Part(price)`  

**Query 3:**

```sql
select id
from Part
where stock > (select max(price) from Part);
```

c: `Part(price)`  
d: `Part(stock)`

**Query 4:**

```sql
select id, descr, price
from Part;
```

g: `no index`
