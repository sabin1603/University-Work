use Olimpice 
go

--vreau sa afisez toate sporturile pentru care participantii au avut mai mult de 3 medale, cu tot cu numele participantilor, id-ul tarii si adresa stadionului. 
create view vAll
as
	select sp.SpId, p.Name, C.Cid, st.Adress
	from Sports sp inner join Participant p on sp.SpId = p.Pid
	inner join Country c on c.Cid = p.Pid
	inner join Stadium st on st.StId = c.Cid
	where Medals > 1
go


select * from vAll

