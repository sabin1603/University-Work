use Olimpice
go

--check an int 
create function checkInt(@n int)
returns int as
begin
	declare @no int 
	if @n>1 and @n <=100
		set @no = 1
	else
		set @no = 0
	return @no
end
go

--check varchar
create function checkVarchar(@v varchar(100))
returns bit as
begin
	declare @b bit
	if @v IS NOT NULL AND @v LIKE '%[a-zA-Z0-9]%'
		set @b = 1
	else
		set @b = 0
	return @b
end 
go


--create a stored procedure for insert in two tables
create procedure addStadiumsVolunteers @stname varchar(100), @adress varchar(100), @volname varchar(100), @volAge int
as
begin
	--validate the parameters
	if dbo.checkVarchar(@stname) = 1 and dbo.checkVarchar (@adress) = 1 and dbo.checkVarchar (@volname) = 1 and dbo.checkInt(@volAge) = 1
	begin
		insert into Stadium (Name, Adress) values (@stname, @adress);
		print 'values added into Stadium';
		declare @stid int=SCOPE_IDENTITY();
		insert into Volunteer (Name, age, StID) values (@volname, @volAge, @stid);
		print 'values added into Volunteer'
		select * from Stadium;
		select * from Volunteer;
	end
	else
	begin
		print 'the parameters are not correct';
		select * from Stadium;
		select * from Volunteer;
	end
end
go

 drop procedure addStadiumsVolunteers 

exec addStadiumsVolunteers 'Stadionul 9 Mai', '47 Str. Soarelui', 'Pista Matei', 20
exec addStadiumsVolunteers 'Stadionul Stefan Cel Mare', '210 Str. Pacii', 'Andrei Calin', 19
exec addStadiumsVolunteers  'BT Arena', '70 Str. Libertatii', 'Morosanu Vlad', 56 
exec addStadiumsVolunteers 'Allianz Arena', '88 Bayern Street', 'Mocanu Mihai', 21
select * from Volunteer

----create a stored procedure for insert
--create procedure addStadiums @n varchar(100), @a varchar(100)
--as
--begin
--    -- validate the parameters @n, @a
--    if dbo.checkVarchar(@n) = 1 and dbo.checkVarchar(@a) = 1
--    begin
--        insert into Stadium (Name, Adress) values (@n, @a);
--        print 'value added';
--        select * from Stadium;
--    end
--    else
--    begin
--        print 'the parameters are not correct';
--        select * from Stadium;
--    end
--end;

--exec addStadiums 'Santiago Bernabeu', '26 Sunny Street'
--exec addStadiums 'National Arena', '16 Unirii Street'

--select * from Stadium
--go

--create procedure addVolunteers @n varchar(50), @a int, @stId int
--as
--begin
--    -- validate the parameters @n, @a
--    if dbo.checkVarchar(@n) = 1 and dbo.checkInt(@a) = 1
--    begin
--        insert into Volunteer (Name, Age, StId) values (@n, @a, @stId);
--        print 'value added';
--        select * from Volunteer;
--    end
--    else
--    begin
--        print 'the parameters are not correct';
--        select * from Volunteer;
--    end
--end;

--drop procedure addStadiums
--drop procedure addVolunteers

--exec addVolunteers 'Popescu Mihai', 20, 1
--exec addVolunteers 'Popa Catalina', 19, 2
--exec addVolunteers 'Becali George', 22, 3
--exec addVolunteers 'Chirila Tudor', 24, 4

----delete from Volunteer 
----where Name like 'P'

--select * from Volunteer

delete from Volunteer where VolId > 6 and VolId < 9