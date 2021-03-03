drop table TrainingManager;
drop table Aux_Enrollment_Payment;
drop table Professional;
drop table Payment;
drop table Enrollment;
drop table Teacher;
drop table Seminar;
drop table Course;
drop table FormativeAction;


CREATE TABLE FormativeAction (
 ID_fa              integer NOT NULL,
 ID_teacher         integer NULL,
 nameFa             text NOT NULL,
 dateFA             date NOT NULL,
 duration           real NOT NULL,
 location           text NOT NULL,
 renumeration       real NOT NULL,
 fee                real NOT NULL,
 status		    text NOT NULL,
 placesAvailable    integer NOT NULL,
 objectives	    text NOT NULL,
 mainContent        text NULL,
 ID_trainingManager integer NOT NULL,
 CONSTRAINT PK_FormativeAction PRIMARY KEY ( ID_fa ),
 CONSTRAINT FK_FORMATIVEACTION_TEACHER FOREIGN KEY ( ID_teacher ) REFERENCES Teacher ( ID_teacher ),
 CONSTRAINT FK_101 FOREIGN KEY ( ID_trainingManager ) REFERENCES TrainingManager ( ID_trainingManager ),
 CHECK ( duration >0 )
);

CREATE TABLE Course (
 ID_fa integer NOT NULL,
 CONSTRAINT PK_Course PRIMARY KEY ( ID_fa )
);

CREATE TABLE Seminar (
 ID_fa integer NOT NULL,
 CONSTRAINT PK_Seminar PRIMARY KEY ( ID_fa )
);

CREATE TABLE Teacher (
 ID_teacher integer NOT NULL,
 salary     real NOT NULL,
 name       text NOT NULL,
 CONSTRAINT PK_Teacher PRIMARY KEY ( ID_teacher )
);

CREATE TABLE Enrollment (
 ID_fa           integer NOT NULL,
 ID_professional integer NOT NULL,
 status          text NOT NULL,
 timeEn          datetime NOT NULL,
 name            text NOT NULL,
 CONSTRAINT PK_Enrollment PRIMARY KEY ( ID_fa, ID_professional ),
 CONSTRAINT AK1_Enrollment UNIQUE ( ID_fa ),
 CONSTRAINT AK2_Enrollment UNIQUE ( ID_professional ),
 CONSTRAINT FK_Enrollment_Professional FOREIGN KEY ( ID_professional ) REFERENCES Professional ( ID_professional ),
 CONSTRAINT FK_FormativeAction_Enrollment FOREIGN KEY ( ID_fa ) REFERENCES FormativeAction ( ID_fa ),
 CHECK ( status IN('received','confirmed','cancelled') )
);

CREATE TABLE Payment (
 ID_payment   integer NOT NULL,
 amount       real NOT NULL,
 datePay      date NOT NULL,
 receiver     text NOT NULL,
 sender       text NOT NULL,
 ID_invoice   text NOT NULL,
 fiscalNumber text NOT NULL,
 address      text NOT NULL,
 paid         boolean NOT NULL,
 CONSTRAINT PK_Payment PRIMARY KEY ( ID_payment )
);

CREATE TABLE Professional (
 ID_professional integer NOT NULL,
 name            text NOT NULL,
 surname         text NOT NULL,
 phone           text NOT NULL,
 email           text NOT NULL,
 CONSTRAINT PK_Professional PRIMARY KEY ( ID_professional )
);

CREATE TABLE Aux_Enrollment_Payment (
 ID_payment      integer NOT NULL,
 ID_fa           integer NOT NULL,
 ID_professional integer NOT NULL,
 CONSTRAINT PK_Aux_Enrollment_Payment PRIMARY KEY ( ID_payment, ID_fa, ID_professional ),
 CONSTRAINT FK_91 FOREIGN KEY ( ID_fa, ID_professional ) REFERENCES Enrollment ( ID_fa, ID_professional ),
 CONSTRAINT FK_95 FOREIGN KEY ( ID_payment ) REFERENCES Payment ( ID_payment )
);

CREATE TABLE TrainingManager (
 ID_trainingManager integer NOT NULL,
 name               text NOT NULL,
 CONSTRAINT PK_TrainingManager PRIMARY KEY ( ID_trainingManager )
);


