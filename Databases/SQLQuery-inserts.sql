--use Olimpice
--go

--INSERT INTO Participant (Name, Medals)
--VALUES ('John Doe', 3),
--       ('Jane Smith', 2),
--       ('Bob Johnson', 1);

--select * from Participant

--INSERT INTO Country (Cid, Name, Medals_Won)
--VALUES
--    (1, 'United States', 10),
--    (2, 'United Kingdom', 5),
--    (3, 'Canada', 3);

--select * from Country


--UPDATE Participant
--SET Cid = (SELECT Cid FROM Country WHERE Name = 'United States') 
--WHERE Name = 'John Doe' AND Medals = 3;

--select * from Participant

--UPDATE Participant
--SET Cid = (SELECT Cid FROM Country WHERE Name = 'United Kingdom') 
--WHERE Name = 'Jane Smith' AND Medals = 2;

--select * from Participant


