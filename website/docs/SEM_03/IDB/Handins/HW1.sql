-- Query 1
select count(*) from person where height is null;

-- Query 2
select count(*) from (select movie.id from movie 
join involved on involved.movieId = movie.Id
join person on involved.personId = person.Id
group by movie.id
having avg(height) > 185) tmp;

-- Query 3
select count(*) from (
select distinct movieId 
from movie_genre
group by movieId, genre
having count(movieId) > 1) tmp

-- Query 4
select count(*) from (
select distinct a.name from involved actors 
join involved directors on actors.movieId = directors.movieId
join person d on d.id = directors.personId
join person a on a.id = actors.personId
where directors.role = 'director' and d.name = 'Quentin Tarantino' and actors.role = 'actor') tmp

-- Query 5
select count(movie.title) from movie
left join involved on movie.id = involved.movieId
where movie.year = 2010 and involved is null

-- Query 6 
select count(*) from (
select count(i1.movieid) from involved i1
join involved i2 on i1.personid = i2.personid and i1.movieid = i2.movieid
where i1.role = 'actor' and i2.role = 'director'
group by i1.personid
having count(i1.movieid) > 3) tmp

-- Query 7
select count(*) from(
select count(movie) from movie
join involved on involved.movieId = movie.id
where movie.year = 2010
group by movie.title
having count(distinct involved.role) = 
(select count(*) from role)) tmp


-- Query 8 543
select count(*) from (
select involved.personId from involved
join movie_genre on movie_genre.movieid = involved.movieId
where movie_genre.genre in (select genre.genre from genre
							where genre.category = 'Misc')
	group by involved.personId
	having count(distinct movie_genre.genre) = 
	(select count(*) from genre where genre.category = 'Misc')) tmp