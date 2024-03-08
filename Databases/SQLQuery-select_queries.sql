--a. 

--selects the participants who have won at least 2 medals and the countries that have at least 5 medals won
SELECT DISTINCT Name FROM Participant WHERE Medals >= 2
UNION
SELECT DISTINCT Name FROM Country WHERE Medals_Won >= 5
ORDER BY Name;


--intersects the countries that have at least 5 medals won and whose index is at most equal to 2
SELECT Name FROM Country WHERE Medals_Won >=5
INTERSECT
SELECT Name FROM Country WHERE Cid <= 2;


--selects the participants with less than 3 medals, except those that have the id equal to 3
SELECT Name FROM Participant WHERE Medals < 3 
EXCEPT
SELECT Name FROM Participant WHERE Pid = 3;




--b

-- Query 1: Inner Join between Participant and Country tables
SELECT * FROM Participant
INNER JOIN Country ON Participant.Cid = Country.Cid;

-- Query 2: Left Join between Participant and Country tables with additional condition
SELECT TOP 10 *
FROM Participant
LEFT JOIN Country ON Participant.Cid = Country.Cid WHERE Country.Medals_Won > 5
ORDER BY Participant.Name DESC;

---- Query 3: Right Join between Participant and Country tables with additional condition
SELECT * FROM Participant
RIGHT JOIN Country ON Participant.Cid = Country.Cid
where Participant.Medals > 2;

-- Query 4: Full Join between Participant and Country tables with combined conditions
SELECT * FROM Participant
FULL JOIN Country ON Participant.Cid = Country.Cid
FULL JOIN Sports On Country.SpId = Sports.SpId
where (Participant.Medals > 2 OR Country.Medals_Won > 5);




--c 

-- Query 1: Selecting distinct records from Participant where Cid is in the set of Country Cid with Medals_won > 5
SELECT DISTINCT * FROM Participant WHERE Cid IN (SELECT Cid FROM Country WHERE Medals_won > 5)
ORDER BY Cid;

-- Query 2: Selecting distinct records from Participant where there exists a record in Country with Medals_won > 5
SELECT DISTINCT * FROM Participant WHERE EXISTS (SELECT 1 FROM Country WHERE Medals_won > 5)
ORDER BY Pid;



--d 

SELECT C.Name AS CountryName, P.Name AS ParticipantName
FROM (
    SELECT Cid, Name
    FROM Country
    WHERE Medals_Won > 5
) AS C
INNER JOIN Participant P ON C.Cid = P.Cid;


--e

-- Query 1: Count of participants in each country with medals between 1 and 5, ordered by participant count descending
SELECT C.Name AS CountryName, COUNT(*) AS ParticipantCount
FROM Country C
INNER JOIN Participant P ON C.Cid = P.Cid
WHERE P.Medals >= 1 AND P.Medals <= 5
GROUP BY C.Name
HAVING COUNT(*) >= 1
ORDER BY ParticipantCount DESC;


-- Query 2: Average number of medals for each country, only including countries with an average medals count greater than the overall average
SELECT C.Name AS CountryName, AVG(P.Medals) AS AvgMedals
FROM Country C
INNER JOIN Participant P ON C.Cid = P.Cid
GROUP BY C.Name
HAVING AVG(P.Medals) >= (SELECT AVG(Medals) FROM Participant);


-- Query 3: Total number of medals for each country, only including countries with a total medals count greater than 1
SELECT C.Name AS CountryName, SUM(P.Medals) AS TotalMedals
FROM Country C
INNER JOIN Participant P ON C.Cid = P.Cid
GROUP BY C.Name
HAVING SUM(P.Medals) > 1
ORDER BY TotalMedals DESC;



select * from Participant
select * from Country