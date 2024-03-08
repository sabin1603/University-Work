use Olimpice
go

if exists (select name from sys.indexes where name=N'N_idx_Adress')
	drop index N_idx_Adress on StadiumT
go

create nonclustered index N_idx_Adress on StadiumT(Adress);
go

insert into Stadium(Name, Adress) values ('Ghencea Stadium', '34 Mihai Viteazul bvd')
select * from Stadium

select * from StadiumT

select * from Stadium order by StId
select * from Stadium order by Name

select * from Stadium where Name like '% Arena' 


select * from Stadium s inner join Sports sp on s.StId = sp.StId where sp.Name like '%ball'