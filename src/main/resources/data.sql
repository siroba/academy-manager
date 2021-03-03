--Data for the initialization of the database 
delete from formativeAction;
insert into formativeAction(ID_formativeAction, name, objectives, mainContents, teacher, remuneration, location, spaces, spacesAvailable, day, numberOfHours, enrollmentPeriodStart, enrollmentPeriodEnd, fee) values 
	(1000, 'initiation to scrum', 'bla', 'blub', 'Julia Artime', 80, 'EPI', 15, 15, '2016-10-05',  2, '2016-09-01', '2016-10-01', 10),
	(1001, 'initiation to agile', 'bla1', 'blub1', 'Pablo Fernandez', 100, 'online', 20, 20, '2016-10-07',  3, '2016-09-29', '2016-10-01', 15);

delete from professional;
insert into professional(ID_professional, name, surname, phone, email) values
	(1000, 'Peter', 'Bauer', '+34175678327', 'peter@uniovi.es'),
	(1001, 'Anna', 'Neumann', '+491678237481', 'anna@uniovi.es');


delete from enrollment;
insert into Enrollment(ID_Enrollment, status, dateEn, name, ID_fa, ID_professional) values 
	(1000, 'CONFIRMED', '2021-03-02', 'exampleName', 1000, 1000),
	(1001, 'RECIEVED', '2021-03-01', 'exampleName2', 1000, 1001);

delete from payment;
insert into Payment(ID_payment, amount, datePay, ID_enrollment) values
	(1000, 20, '2021-03-02', 1000);