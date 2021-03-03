--Data for the initialization of the database 
delete from formativeAction;
insert into formativeAction(id, name, objectives, mainContents, teacher, remuneration, location, spaces, day, numberOfHours, enrollmentPeriodStart, enrollmentPeriodEnd, fee) values 
	(1000, 'initiation to scrum', 'bla', 'blub', 'Julia Artime', 100, 'EPI', 15, '2016-10-05',  2, '2016-09-01', '2016-10-01', 10),
	(1001, 'initiation to agile', 'bla1', 'blub1', 'Pablo Fernandez', 80, 'online', 20, '2016-10-07',  3, '2016-09-29', '2016-10-01', 10);
	

delete from teacher;
insert into teacher(id, salary, name) values
	(1001, null, 'Jorge');
