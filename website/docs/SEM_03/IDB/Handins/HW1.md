# Homework Assignment 1

Hand-in date September 19th

## 1. SQL Queries

**(1) The person relation contains 573 entries with a registered height greater than 190 centimetres. How many entries do not have a registered height?**

```sql
select count(*) from person where height is null;

= 47316
```

**(2) In the database, there are 365 movies for which the average height of all the people involved is less than 165 centimeters (ignoring people with unregistered height). What is the number of movies for which the average height of all people involved is greater than 185?**

```sql
select count(*) from (select movie.id from movie 
join involved on involved.movieId = movie.Id
join person on involved.personId = person.Id
group by movie.id
having avg(height) > 185) tmp;

= 521
```

**(3) The movie genre relation does not have a primary key, which can lead to a movie having more than one entry with the same genre. How many movies in movie genre have such duplicate entries?**

```sql
select count(*) from (
select distinct movieId 
from movie_genre
group by movieId, genre
having count(movieId) > 1) tmp

= 145
```

**(4) According to the information in the database, 476 different people acted in movies directed by ‘Francis Ford Coppola’. How many different people acted in movies directed by ‘Quentin Tarantino’?**

```sql
select count(*) from (
select distinct a.name from involved actors 
join involved directors on actors.movieId = directors.movieId
join person d on d.id = directors.personId
join person a on a.id = actors.personId
where directors.role = 'director' and d.name = 'Quentin Tarantino' and actors.role = 'actor') tmp

= 7689
```

**(5) Of all the movies produced in 2002, there are 12 that have no registered entry in involved. How many movies produced in 2010 have no registered entry in involved?**

```sql
select count(movie.title) from movie
left join involved on movie.id = involved.movieId
where movie.year = 2010 and involved is null

= 15
```

**(6) In the database, the number of people who have acted in exactly one movie that they have also self-directed is 603. How many people have both self-directed and acted in more than three movies?**

```sql
select count(*) from (
select count(i1.movieid) from involved i1
join involved i2 on i1.personid = i2.personid and i1.movieid = i2.movieid
where i1.role = 'actor' and i2.role = 'director'
group by i1.personid
having count(i1.movieid) > 3) tmp

= 87
```

**(7) Of all the movies produced in 2002, there are 282 that have entries registered in involved for all roles defined in the roles relation. How many movies produced in 2010 have entries registered in involved for all roles defined in the roles relation? Note: This is a relational division query which must work for any schema; you can not use the fact that currently there are only 2 different roles to write a ‘magic number’.**

```sql
select count(*) from(
select count(movie) from movie
join involved on involved.movieId = movie.id
where movie.year = 2010
group by movie.title
having count(distinct involved.role) = 
(select count(*) from role)) tmp

= 237
```

**(8) The number of people who have played a role in movies of all genres in the category ‘Newsworthy’ is 156. How many people have played a role in movies of all genres in the category ‘Misc’?**

```sql
select count(*) from (
select involved.personId from involved
join movie_genre on movie_genre.movieid = involved.movieId
where movie_genre.genre in (select genre.genre from genre
                            where genre.category = 'Misc')
                            group by involved.personId
                            having count(distinct movie_genre.genre) = 
                            (select count(*) from genre where genre.category = 'Misc')) tmp

= 543
```
