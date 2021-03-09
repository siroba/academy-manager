--Data for the initialization of the database 

insert into FormativeAction(ID_fa, nameFa, dateFa, duration, location, remuneration, fee, totalPlaces, objectives, mainContent, teacherName, status, enrollmentStart, enrollmentEnd) values
	(1000, 'Initiation to scrum',	'2021-02-03 15:00:00.0', 2, 'online', 50, 15, 6,	'some objctives', 'some main content', 'Jose Garcia Fanjul',				'EXECUTED', '2021-01-01 00:00:00',	'2021-02-01 00:00:00'),
	(1001, 'Initiation to agile',	'2021-02-10 15:00:00.0', 3, 'online', 60, 10, 10, 'some objctives', 'some main content', 'Jose Garcia Fanjul',				'EXECUTED', '2021-01-01 00:00:00',	'2021-02-08 00:00:00'),
	(1002, 'JDBC',  				'2021-04-10 12:00:00.0', 3, 'online', 100, 30, 4, 'some objctives', 'some main content', 'Maria Teresa Gonzales Aparicio',	'ACTIVE', '2021-03-01 00:00:00',	'2021-04-08 00:00:00'),
	(1003, 'Java programming',		'2021-05-01 10:00:00.0', 4, 'online', 80, 20, 5,	'some objctives', 'some main content', 'Victor Manuel Alvarez Garcia',		'ACTIVE', '2021-04-01 00:00:00',	'2021-04-28 00:00:00');


insert into Professional(ID_professional, name, surname, phone, email) values
	(2000, 'Pablo',		'Gonzales', 	'+341762568901', 	'pablo.gonzales@uniovi.es'),
	(2001, 'Juan',		'Fernandez', 	'+3413705978356', 	'juan.fernandez@uniovi.es'),
	(2002, 'Carlo', 	'Perez', 		'+3427889123675', 	'carlo.perez@uniovi.es'),
	(2003, 'Alvaro', 	'Lopez', 		'+3436789863729', 	'alvaro.lopez@uniovi.es'),
	(2004, 'Maria', 	'Meier', 		'+491755676536', 	'maria.meier@rub.de'),
	(2005, 'Paul', 		'Bauer', 		'+4917064578933', 	'paul.bauer@rub.de');

insert into Enrollment(ID_fa, ID_professional, status, timeEn) values
	(1000, 2000, 'CONFIRMED', '2021-01-02 16:00:00'),
	(1000, 2001, 'CONFIRMED', '2021-01-04 17:00:00'),
	(1000, 2002, 'CONFIRMED', '2021-01-07 18:00:00'),
	(1000, 2003, 'CONFIRMED', '2021-01-10 19:00:00'),
	(1000, 2004, 'CONFIRMED', '2021-01-13 10:00:00'),
	(1000, 2005, 'CONFIRMED', '2021-01-16 13:00:00'),
	(1001, 2000, 'CONFIRMED', '2021-01-02 12:00:00'),
	(1001, 2001, 'CONFIRMED', '2021-01-13 14:00:00'),
	(1001, 2002, 'CONFIRMED', '2021-01-23 22:00:00'),
	(1001, 2003, 'CONFIRMED', '2021-02-03 20:00:00'),
	(1001, 2004, 'CONFIRMED', '2021-02-01 08:00:00'),
	(1002, 2000, 'RECEIVED', '2021-03-02 12:00:00'),
	(1002, 2005, 'RECEIVED', '2021-03-05 08:00:00'),
	(1002, 2001, 'CONFIRMED', '2021-03-05 08:00:00');

insert into Payment(ID_payment, amount, datePay, sender, receiver, address, fiscalNumber, confirmed, ID_fa, ID_professional) values
	(3000, 15, '2021-01-02 17:00:00', 'senderName', 'recieverName', 'address', 'fiscalNumber', true, 1000, 2000),
	(3001, 15, '2021-01-04 18:00:00', 'senderName', 'recieverName', 'address', 'fiscalNumber', true, 1000, 2001),
	(3002, 15, '2021-01-07 20:00:00', 'senderName', 'recieverName', 'address', 'fiscalNumber', true, 1000, 2002),
	(3003, 15, '2021-01-11 12:00:00', 'senderName', 'recieverName', 'address', 'fiscalNumber', true, 1000, 2003),
	(3004, 15, '2021-01-14 13:00:00', 'senderName', 'recieverName', 'address', 'fiscalNumber', true, 1000, 2004),
	(3005, 15, '2021-01-16 14:00:00', 'senderName', 'recieverName', 'address', 'fiscalNumber', true, 1000, 2005),
	(3006, 10, '2021-01-02 18:00:00', 'senderName', 'recieverName', 'address', 'fiscalNumber', true, 1001, 2000),
	(3007, 10, '2021-01-13 15:00:00', 'senderName', 'recieverName', 'address', 'fiscalNumber', true, 1001, 2001),
	(3008, 10, '2021-01-24 08:00:00', 'senderName', 'recieverName', 'address', 'fiscalNumber', true, 1001, 2002),
	(3009, 10, '2021-02-04 10:00:00', 'senderName', 'recieverName', 'address', 'fiscalNumber', true, 1001, 2003),
	(3010, 10, '2021-02-01 15:00:00', 'senderName', 'recieverName', 'address', 'fiscalNumber', true, 1001, 2004),
	(3011, 30, '2021-03-22 13:00:00', 'senderName', 'recieverName', 'address', 'fiscalNumber', false, 1002, 2000),
	(3012, 30, '2021-03-03 13:00:00', 'senderName', 'recieverName', 'address', 'fiscalNumber', false, 1002, 2005),
	(3013, 30, '2021-03-05 10:00:00', 'senderName', 'recieverName', 'address', 'fiscalNumber', true, 1002, 2001);
