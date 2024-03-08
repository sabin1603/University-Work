use Olimpice
go

create table Logs (lid int primary key identity, TriggerDate date, TriggerType varchar(50), NameAffectedTable varchar(50), NoAMDRows int)

select * from Logs

--INSERT
--create a copy for the table
create table StadiumT(StId int primary key, Name varchar(50), Adr.ess varchar(50))
go

create trigger add_Stadium on Stadium for
insert as
begin
insert into StadiumT(StId, Name, Adress)
select StId, Name, Adress
from inserted
insert into Logs(TriggerDate, TriggerType, NameAffectedTable, NoAMDRows)
values(GETDATE(), 'INSERT', 'Stadium', @@ROWCOUNT)
end
go




select * from Stadium
select * from StadiumT
insert into Stadium values ('Cluj Arena', '74 Str. Unirii'), ('Cluj Arena', '74 Str. Unirii')
select * from Stadium
select * from StadiumT