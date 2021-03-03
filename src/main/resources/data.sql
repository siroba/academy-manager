--Data for the initialization of the database 
delete from TrainingManager;
delete from Aux_Enrollment_Payment;
delete from Professional;
delete from Payment;
delete from Enrollment;
delete from Teacher;
delete from Seminar;
delete from Course;
delete from FormativeAction;

insert into Teacher(ID_teacher, salary, name) values 
	(1, 240, "teacher1"),
	(2, 260, "teacher2");

insert into TrainingManager(ID_trainingManager, name) values 
	(1, "trainer1"),
	(2, "trainer2");     
    
insert into FormativeAction(ID_fa, ID_teacher, nameFa, dateFA, duration, location, renumeration, fee, status, placesAvailable, objectives, mainContent, ID_trainingManager) values 
	(1, 1, "FA1", "10.03.2021", 3, "Spain", 240, 30, "active", 10, "Bier", "Bier brauen und trinken", 1);
