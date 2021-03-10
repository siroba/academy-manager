--Data for the initialization of the database 
delete from courses;
insert into courses(id, name, day, teacher, location, startTime, endTime, price, spots) values 
	('100', 'initiation to scrum', '2016-10-05', 'Julia Artime', 'EPI', 18, 21, 20, 15),
	('101', 'initiation to agile', '2016-10-06', 'Pablo', 'online', 17, 19, 15, 20),
	('102', 'initiation to databases', '2016-10-06', 'Rosa', 'online', 16, 18, 20, 30),
	('103', 'initiation to java', '2016-10-07', 'Juan', 'EPI', 12, 16, 30, 10);
