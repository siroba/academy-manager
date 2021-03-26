--Data for the initialization of the database 

insert into FormativeAction(ID_fa, nameFa, fee, totalPlaces, objectives, mainContent, status, enrollmentStart, enrollmentEnd) values
	(1000, 'Initiation to scrum',	15, 6,	'some objctives', 'some main content', 'EXECUTED', '2021-01-01 00:00:00.0',	'2021-02-01 00:00:00.0'),
	(1001, 'Initiation to agile',	10, 10, 'some objctives', 'some main content', 'EXECUTED', '2021-01-01 00:00:00.0',	'2021-02-08 00:00:00.0'),
	(1002, 'JDBC',  				30, 4,  'some objctives', 'some main content', 'ACTIVE',   '2021-03-01 00:00:00.0',	'2021-04-08 00:00:00.0'),
	(1003, 'Java programming',		20, 5,	'some objctives', 'some main content', 'ACTIVE',   '2021-04-01 00:00:00.0',	'2021-04-28 00:00:00.0');

insert into Session(ID_session, ID_fa, location, duration, sessionStart, teacherName, remuneration) values
	(0, 1000, 'online', 2, '2021-02-03 15:00:00.0', 'Jose Garcia Fanjul', 50),
	(1, 1000, 'online', 2, '2021-02-04 15:00:00.0', 'Jose Garcia Fanjul', 50),
	(2, 1000, 'online', 2, '2021-02-05 15:00:00.0', 'Jose Garcia Fanjul', 50),
	(3, 1001, 'online', 3, '2021-02-10 09:00:00.0', 'Jose Garcia Fanjul', 60),
	(4, 1001, 'online', 3, '2021-02-11 09:00:00.0', 'Jose Garcia Fanjul', 60),
	(5, 1002, 'online', 3, '2021-04-10 12:00:00.0', 'Maria Teresa Gonzales Aparicio', 100),
	(6, 1003, 'online', 4, '2021-05-01 10:00:00.0', 'Victor Manuel Alvarez Garcia', 80);

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
	
insert into Invoice_Teacher(Id_invoice , dateIn , sender, receiver, address, fiscalNumber,ID_fa) values

	(4000, '2021-01-02', 'senderName', 'recieverName', 'address', 'fiscalNumber' , 1000),
	(4001, '2021-01-03 ', 'senderName', 'recieverName', 'address', 'fiscalNumber', 1001),
	(4002, '2021-01-04 ', 'senderName', 'recieverName', 'address', 'fiscalNumber',  1002);
	
insert into Invoice_Professional(Id_invoice , dateIn , sender, receiver, address, fiscalNumber,ID_fa , ID_professional) values

	(5000, '2021-01-02', 'senderName', 'recieverName', 'address', 'fiscalNumber' , 1000),
	(5001, '2021-01-03 ', 'senderName', 'recieverName', 'address', 'fiscalNumber', 1001),
	(5002, '2021-01-04 ', 'senderName', 'recieverName', 'address', 'fiscalNumber',  1002);
	(5003, '2021-01-02', 'senderName', 'recieverName', 'address', 'fiscalNumber' , 1000),
	(5004, '2021-01-03 ', 'senderName', 'recieverName', 'address', 'fiscalNumber', 1001),
	(5005, '2021-01-04 ', 'senderName', 'recieverName', 'address', 'fiscalNumber',  1002);

insert into Payment(ID_payment, amount, datePay, confirmed, ID_invoice) values
	(3000, 15, '2021-01-02 ', true, 1000, 5000),
	(3001, 15, '2021-01-04 ', true, 1000, 5001),
	(3002, 15, '2021-01-07 ', true, 1000, 5002),
	(3003, 15, '2021-01-11 ',  true, 1000, 5003),
	(3004, 15, '2021-01-14 ', true, 1000, 5004),
	(3005, 15, '2021-01-16 ', true, 1000, 5005),
	(3006, 10, '2021-01-02 ', true, 1001, 5000),
	(3007, 10, '2021-01-13 ',  true, 1001, 5001),
	(3008, 10, '2021-01-24 ',  true, 1001, 5002),
	(3009, 10, '2021-02-04 ',  true, 1001, 5003),
	(3010, 10, '2021-02-01 ',  true, 1001, 5004),
	(3011, 30, '2021-03-22 ',  false, 1002, 5000),
	(3012, 30, '2021-03-03 ',  false, 1002, 5005),
	(3013, 30, '2021-03-05 ',  true, 1002, 5001);




insert into PaymentTeacher( ID_payment, amount, datePay, sender, receiver, address, fiscalNumber, confirmed, ID_invoice) values
(3000, 15, '2021-01-02 ', 'senderName', 'recieverName', 'address', 'fiscalNumber', true, 4000),
	(3001, 15, '2021-01-04 ', 'senderName', 'recieverName', 'address', 'fiscalNumber', false, 4001),
	(3002, 15, '2021-01-07 ', 'senderName', 'recieverName', 'address', 'fiscalNumber', false, 4002);
