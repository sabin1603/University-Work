CREATE TABLE Participant_Sports
(Pid INT FOREIGN KEY REFERENCES Participant(Pid),
SpId INT FOREIGN KEY REFERENCES Sports(SpId),
CONSTRAINT pk_Participant PRIMARY KEY (Pid, SpId))
-