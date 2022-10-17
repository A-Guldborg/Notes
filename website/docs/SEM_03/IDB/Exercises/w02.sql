-- BASIC QUERIES
-- Query 1: Name + record of all sports sorted by name
select Name, Record from Sports order by Name

-- Query 2: Name of all sports + at least one result
select Name, Min(Results.result) as worst from Sports join Results on Sports.Id = Results.SportId group by Sports.Id

-- Query 3: The number of athletes who have competed in at least one sport
Select count(distinct people) from people
join results on people.id = results.peopleid

-- Query 4: The ID + name of athletes with at least 20 results
Select Id, Name from people
join results on people.id = results.peopleid
group by (Id, Name)
having count(results) >= 20

-- Query 5: ID + name + gender of athles that currently hold a record
Select distinct People.Id, People.Name, Gender.Description from people 
Join Gender on gender.gender = people.gender
Join results on results.peopleid = people.id
Join sports on sports.record = results.result
Where results.sportid = sports.id

-- Query 6: For each sport that an athlete holds the record, show sports name + number of athletes as numathletes that holds the record
Select Sports.name, count(distinct results.peopleid) as numathletes from Sports
Join Results on sports.id = results.sportid
Where sports.record = results.result
Group by Sports.name
-- Total = 38 and Query 5 = 33 because one person holds the record in multiple sports


-- Query 7: ID + name of athletes + best result + difference between result and record of people with at least twenty results in the triple jump
select distinct people.id, people.name, max(results.result) as best, to_char(sports.record - max(results.result), '0D99') as difference from people
join results on people.id = results.peopleid
join sports on sports.id = results.sportid
where sports.name = 'Triple Jump'
group by people.id, people.name, sports.record
having count(results) >= 20

-- Query 8: ID + name + gender of all athletes who participated in the competition held in Hvide Sande 2009
Select distinct people.id, people.name, gender.description from People
join gender on people.gender = gender.gender
join results on people.id = results.peopleid
join competitions on results.competitionid = competitions.id
where competitions.place = 'Hvide Sande' and extract(year from competitions.held) = 2009

-- Query 9: Name + gender of all people with a lastname that starts with a "J" and ends with "sen"
Select people.name, gender.description from people
join gender on gender.gender = people.gender
where people.name like '% J%sen'

-- Query 10: name of athlete + sport + percentage of record for each row in results
Select people.name, sports.name, 
CASE
	When results.result is NULL then '0%'
	Else to_char(results.result / sports.record * 100, '999%')
END
 as percentage from results
join people on results.peopleid = people.id
join sports on results.sportid = sports.id


-- Query 11: Number of athletes with incomplete result registrations
Select count(distinct people) from results 
join people on people.id = results.peopleid
where results.result is NULL

-- Query 12: For each sport show id and name and best performance over all athletes and competitions as maxres
select sports.id, sports.name, max(results.result) as maxres from sports
join results on sports.id = results.sportid
group by sports.id, sports.name
order by sports.id

-- Query 13: ID and name of athletes who holds at least two records along with the number of records
select people.id, people.name, count(results.result) from people
join results on results.peopleid = people.id
join sports on results.sportid = sports.id
where results.sportid = sports.id and results.result = sports.record
group by people.id, people.name
having count(distinct results.sportid) >= 2

-- ADVANCED QUERIES


-- Thanks to Frederik Petersen frepe for help throughout several queries!
