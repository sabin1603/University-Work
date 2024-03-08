use Olimpice
go

alter table Participant
add prize_money int

alter table Participant
drop column prize_money

--constrangere de tip default 
alter table Participant
add prize_money int

alter table Participant
add constraint DF_Medals default 0 for Medals

alter table Participant
drop DF_Medals

create table Broadcasters (Bid int primary key identity,
							name varchar(50),
							SpId int foreign key references Sports(SpId)
							)

drop table Broadcasters

alter table Broadcasters
add constraint FK_Broadcasters foreign key (SpId) references Sports (SpId)

alter table Broadcasters
drop FK_Broadcasters

create table DatabaseVersion (version int not null)

--proceduri
go
create or alter procedure addBroadcastersTable
as 
begin
	create table Broadcasters (
		Bid int primary key identity,
		name varchar(50),
		SpId int foreign key references Sports(SpId)
	)
end

go

create or alter procedure removeBroadcastersTable
as
begin
	if object_id('Broadcasters', 'U') is not null
	begin
		exec('drop table Broadcasters')
	end
end
go

create or alter procedure addPrizeMoneyToParticipant 
as
begin
alter table Participant
add Prize_Money int
end
go

create or alter procedure removePrizeMoneyFromParticipant
as
begin
alter table Participant 
drop column Prize_money
end
go

create or alter procedure addDefaultToMedalsFromParticipant
as
begin
alter table Participant 
add constraint DF_Medals default 0 for Medals
end
go

create or alter procedure removeDefaultFromMedalsFromParticipant
as
begin
alter table Participant 
drop constraint DF_Medals
end
go

create or alter procedure addFKToBroadcasters
as
begin
	alter table Broadcasters
	add constraint FK_Broadcasters foreign key (SpId) references Sports (SpId)
end
go

create or alter procedure removeFKFromBroadcasters
as
begin
	alter table Broadcasters
	drop FK_Broadcasters
end
go

insert into DatabaseVersion values (1) -- initial version

create table proceduresTable (
    fromVersion int,
    toVersion int,
    primary key (fromVersion, toVersion),
    nameProc varchar(max)
)


insert into ProceduresTable values (1, 2, 'addBroadcastersTable')
insert into ProceduresTable values (2, 1, 'removeBroadcastersTable') 
insert into ProceduresTable values (2, 3, 'addFKToBroadcasters')
insert into ProceduresTable values (3, 2, 'removeFKFromBroadcasters')
insert into ProceduresTable values (3, 4, 'addPrizeMoneyToParticipant')
insert into ProceduresTable values (4, 3, 'removePrizeMoneyFromParticipant')
insert into ProceduresTable values (4, 5, 'addDefaultToMedalsFromParticipant')
insert into ProceduresTable values (5, 4, 'removeDefaultFromMedalsFromParticipant')


exec addBroadcastersTable
go
exec addFKToBroadcasters
go
exec removeFKFromBroadcasters
go
exec removeBroadcastersTable
go
exec addPrizeMoneyToParticipant 
go
exec addDefaultToMedalsFromParticipant
go
exec removeDefaultFromMedalsFromParticipant
go
exec removePrizeMoneyFromParticipant 
go

--main
create or alter procedure goToVersion(@newVersion int) as
begin
	declare @curr int
    declare @var varchar(max)
    select @curr=version from DatabaseVersion

    if @newVersion > (select max(toVersion) from proceduresTable)
        raiserror ('Bad version', 10, 1)

    while @curr > @newVersion begin
        select @var=nameProc from proceduresTable where fromVersion=@curr and toVersion=@curr-1
        exec (@var)
        set @curr=@curr-1
		update DatabaseVersion set version=@newVersion
    end

    while @curr < @newVersion begin
        select @var=nameProc from proceduresTable where fromVersion=@curr and toVersion=@curr+1
        exec (@var)
        set @curr=@curr+1
		update DatabaseVersion set version=@newVersion
    end
end
    

execute goToVersion 2


select * from DatabaseVersion