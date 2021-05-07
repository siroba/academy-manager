
CREATE TABLE IF NOT EXISTS Enrollment(
    ID_fa           integer NOT NULL,
    ID_professional integer NOT NULL,
    status          text NOT NULL,
    timeEn          datetime NOT NULL,
    category	    text NOT NULL,
    CONSTRAINT PK_Enrollment PRIMARY KEY ( ID_fa, ID_professional ),
    CONSTRAINT FK_Enrollment_Professional FOREIGN KEY ( ID_professional ) REFERENCES Professional ( ID_professional ),
    CONSTRAINT FK_FormativeAction_Enrollment FOREIGN KEY ( ID_fa ) REFERENCES FormativeAction ( ID_fa ),
    CHECK ( status IN('RECEIVED','CONFIRMED','CANCELLED') )
);

CREATE TABLE IF NOT EXISTS FormativeAction(
    ID_fa           INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    nameFa          text NOT NULL,
    totalPlaces     integer NOT NULL,
    objectives      text NOT NULL,
    mainContent     text NULL,
    status          text NOT NULL,
    enrollmentStart datetime NOT NULL,
    enrollmentEnd   datetime NOT NULL,
    CONSTRAINT FORMATIVEACTION_STATUS_CONSTRAINT CHECK ( status IN ('ACTIVE', 'DELAYED', 'EXECUTED', 'CANCELLED') )
);


CREATE TABLE IF NOT EXISTS Session(
    ID_session	    INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    ID_fa	     	INTEGER NOT NULL,
    location        text NOT NULL,
    duration        real NOT NULL,
    sessionStart    timestamp NOT NULL
    CHECK ( duration >0 ),
 	CONSTRAINT FK_200 FOREIGN KEY ( ID_fa ) REFERENCES FormativeAction ( ID_fa )
);


CREATE TABLE IF NOT EXISTS Fee(
    ID_fee		INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    amount		real NOT NULL,
    category	text NOT NULL,
    ID_fa		integer NOT NULL,
    CONSTRAINT FK_201 FOREIGN KEY ( ID_fa ) REFERENCES FormativeAction ( ID_fa )
    Constraint Uniqe_205 unique(ID_fa, category)
);

CREATE TABLE IF NOT EXISTS Invoice (
	ID_invoice 			INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	amount				real NOT NULL,
	dateIn     	 		date NOT NULL,
	sender          	text NOT NULL,
	receiver        	text NOT NULL,
	address         	text,
	fiscalNumber    	text,
	ID_fa      			integer NOT NULL,
	ID_professional     integer NOT NULL,
	description     	text,
	CONSTRAINT FK_161 FOREIGN KEY ( ID_fa ) REFERENCES FormativeAction ( ID_fa ),
	CONSTRAINT FK_162 FOREIGN KEY ( ID_professional ) REFERENCES Professional ( ID_professional )
);

CREATE TABLE IF NOT EXISTS Payment(
    ID_payment      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    amount          real NOT NULL,
    datePay         datetime NOT NULL,
    confirmed       boolean default 1,
    cash 	     	boolean default 0,
    ID_invoice      integer NOT NULL,
	description     text,
    CONSTRAINT FK_104 FOREIGN KEY ( ID_invoice ) REFERENCES Invoice ( ID_invoice )
);

CREATE TABLE IF NOT EXISTS Teacher (
	ID_teacher 	 integer PRIMARY KEY AUTOINCREMENT NOT NULL,
	name            text NOT NULL,
   	surname         text NOT NULL,
   	phone           text NOT NULL,
    	email           text NOT NULL,
    	fiscalNumber	 text NOT NULL	
);

CREATE TABLE IF NOT EXISTS InvoiceTeacher (
	ID_invoice 		text PRIMARY KEY NOT NULL,
	amount			real NOT NULL,
	dateIn     		date NOT NULL,
	sender          text NOT NULL,
	receiver        text NOT NULL,
	address         text NOT NULL,
	fiscalNumber    text NOT NULL,
	ID_fa      		integer NOT NULL,
	ID_teacher   	integer NOT NULL,
	description     text,
	CONSTRAINT FK_163 FOREIGN KEY ( ID_fa ) REFERENCES FormativeAction ( ID_fa ),
	CONSTRAINT FK_256 FOREIGN KEY ( ID_teacher ) REFERENCES Teacher ( ID_teacher )
);

CREATE TABLE IF NOT EXISTS PaymentTeacher (
	ID_payment   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	amount       real NOT NULL,
	datePay      date NOT NULL,
	confirmed    boolean NOT NULL,
	ID_invoice   text NOT NULL,
	description  text,
	CONSTRAINT FK_158 FOREIGN KEY ( ID_invoice ) REFERENCES InvoiceTeacher ( ID_invoice )
);

CREATE TABLE IF NOT EXISTS Professional(
    ID_professional INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name            text NOT NULL,
    surname         text NOT NULL,
    phone           text NOT NULL,
    email           text NOT NULL
);

CREATE TABLE IF NOT EXISTS TeacherTeaches (
	ID_teacher   	INTEGER NOT NULL,
	ID_fa        	integer NOT NULL,
	remuneration 	real NOT NULL,
	CONSTRAINT PK_TeacherTeaches PRIMARY KEY (ID_teacher, ID_fa),
	CONSTRAINT FK_244 FOREIGN KEY ( ID_teacher ) REFERENCES Teacher ( ID_teacher ),
	CONSTRAINT FK_249 FOREIGN KEY ( ID_fa ) REFERENCES FormativeAction ( ID_fa )
);
