 --use Olimpice
--go

--select * from Participant


--WITH DuplicateCTE AS (
--    SELECT
--        Pid,
--        Name,
--        Medals,
--        Cid,
--        ROW_NUMBER() OVER (PARTITION BY Name, Medals ORDER BY Pid) AS RowNumber
--    FROM Participant
--)

--DELETE FROM DuplicateCTE
--WHERE RowNumber > 1;


--select * from Participant

--UPDATE Participant
--SET Cid = (SELECT Cid FROM Country WHERE Name = 'Canada') 
--WHERE Name = 'Bob Johnson' AND Medals = 1 AND Cid IS NULL;


--select * from Participant


--select * from Country


--INSERT INTO Sports (Name)
--VALUES
--    ('Swimming'),
--    ('Basketball'),
--    ('Athletics');

--select * from Sports

--UPDATE Country
--SET SpId = (SELECT SpId FROM Sports WHERE Name = 'Swimming') 
--WHERE Name = 'United States';

--UPDATE Country
--SET SpId = (SELECT SpId FROM Sports WHERE Name = 'Basketball') 
--WHERE Name = 'Canada';

--UPDATE Country
--SET SpId = (SELECT SpId FROM Sports WHERE Name = 'Athletics') 
--WHERE Name = 'United Kingdom';


--select * from Country


--select * from Sports

--INSERT INTO Stadium (Name, Adress)
--VALUES
--    ('Olympic Stadium', '123 Main Street'),
--    ('Basketball Arena', '456 Park Avenue'),
--    ('Swimming Pool Complex', '789 Broad Street');

--select * from Stadium


--UPDATE Sports
--SET StId = (SELECT StId FROM Stadium WHERE Name = 'Olympic Stadium')
--WHERE Name = 'Athletics';

--UPDATE Sports
--SET StId = (SELECT StId FROM Stadium WHERE Name = 'Swimming Pool Complex')
--WHERE Name = 'Swimming';

--UPDATE Sports
--SET StId = (SELECT StId FROM Stadium WHERE Name = 'Basketball Arena')
--WHERE Name = 'Basketball';

--select * from Sports
--select * from Participant
--select * from Country
--select * from Stadium




