DROP TABLE Enrollment;
CREATE TABLE IF NOT EXISTS Enrollment(
    ID_fa           integer NOT NULL,
    ID_professional integer NOT NULL,
    status          text NOT NULL,
    timeEn          datetime NOT NULL,
    CONSTRAINT PK_Enrollment PRIMARY KEY ( ID_fa, ID_professional ),
    CONSTRAINT FK_Enrollment_Professional FOREIGN KEY ( ID_professional ) REFERENCES Professional ( ID_professional ),
    CONSTRAINT FK_FormativeAction_Enrollment FOREIGN KEY ( ID_fa ) REFERENCES FormativeAction ( ID_fa ),
    CHECK ( status IN('RECEIVED','CONFIRMED','CANCELLED') )
);

DROP TABLE Invoice;
CREATE TABLE Invoice (
 ID_invoice INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
 dateIn     datetime NOT NULL,
 ID_fa      integer NOT NULL,
 CONSTRAINT FK_161 FOREIGN KEY ( ID_fa ) REFERENCES FormativeAction ( ID_fa )
);

DROP TABLE FormativeAction;
CREATE TABLE IF NOT EXISTS FormativeAction(
    ID_fa           INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    nameFa          text NOT NULL,
    fee             real NOT NULL,
    totalPlaces     integer NOT NULL,
    objectives       text NOT NULL,
    mainContent     text NULL,
    status          text NOT NULL,
    enrollmentStart datetime NOT NULL,
    enrollmentEnd   datetime NOT NULL,
    CONSTRAINT FORMATIVEACTION_STATUS_CONSTRAINT CHECK ( status IN ('ACTIVE', 'DELAYED', 'EXECUTED', 'CANCELLED') )
);

DROP TABLE Session;
CREATE TABLE IF NOT EXISTS Session(
	ID_session		INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	ID_fa			INTEGER NOT NULL,
    location        text NOT NULL,
    duration        real NOT NULL,
    sessionStart    timestamp NOT NULL,
    teacherName     text NULL,
    remuneration    real NOT NULL,
    CHECK ( duration >0 ),
 	CONSTRAINT FK_200 FOREIGN KEY ( ID_fa ) REFERENCES FormativeAction ( ID_fa )
);

DROP TABLE Payment;
CREATE TABLE IF NOT EXISTS Payment(
    ID_payment      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    amount          real NOT NULL,
    datePay         datetime NOT NULL,
    sender          text NOT NULL,
    receiver        text NOT NULL,
    address         text NOT NULL,
    fiscalNumber    text NOT NULL,
    confirmed       boolean NOT NULL,
    refundStatus    text DEFAULT 'NONE',
    ID_fa           integer NOT NULL,
    ID_professional integer NOT NULL,
    CONSTRAINT FK_104 FOREIGN KEY ( ID_fa, ID_professional ) REFERENCES Enrollment ( ID_fa, ID_professional ),
    CHECK ( refundStatus IN('NONE', 'SOLICITED', 'REFUNDED') )
);

DROP TABLE PaymentTeacher;
CREATE TABLE PaymentTeacher (
 ID_payment      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
 amount       real NOT NULL,
 datePay      date NOT NULL,
 sender       text NOT NULL,
 receiver     text NOT NULL,
 address      text NOT NULL,
 fiscalNumber text NOT NULL,
 confirmed    boolean NOT NULL,
 ID_invoice   integer NOT NULL,
 CONSTRAINT FK_158 FOREIGN KEY ( ID_invoice ) REFERENCES Invoice ( ID_invoice )
);



DROP TABLE Professional;
CREATE TABLE IF NOT EXISTS Professional(
    ID_professional INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    name            text NOT NULL,
    surname         text NOT NULL,
    phone           text NOT NULL,
    email           text NOT NULL
);
