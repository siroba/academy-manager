--Data for the initialization of the database 
delete from formativeActions;
insert into formativeActions(name, objectives, mainContents, teacher, remuneration, location, space, day, numberOfHours, enrollmentPeriodStart, enrollmentPeriodEnd) values 
	('initiation to scrum', 'bla', 'blub', 'Julia Artime', 20, 'EPI', 15, '2016-10-05',  2, '2016-09-01', '2016-10-01'),
	('initiation to agile', 'bla1', 'blub1', 'Pablo Fernandez', 25, 'online', 20, '2016-10-07',  3, '2016-09-29', '2016-10-01');
	
