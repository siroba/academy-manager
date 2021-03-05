drop table FormativeAction;
CREATE TABLE FormativeAction(
    ID_fa           integer NOT NULL,
    nameFa          text NOT NULL,
    dateFA          datetime NOT NULL,
    duration        real NOT NULL,
    location        text NOT NULL,
    remuneration    real NOT NULL,
    fee             real NOT NULL,
    totalPlaces     integer NOT NULL,
    objectives       text NOT NULL,
    mainContent     text NULL,
    teacherName     text NULL,
    status          text NOT NULL,
    enrollmentStart datetime NOT NULL,
    enrollmentEnd   datetime NOT NULL,
    CONSTRAINT PK_FormativeAction PRIMARY KEY ( ID_fa ),
    CHECK ( duration >0 ),
    CONSTRAINT FORMATIVEACTION_STATUS_CONSTRAINT CHECK ( status IN ('active', 'executed', 'cancelled') )
);


drop table Professional;
CREATE TABLE Professional(
    ID_professional integer NOT NULL,
    name            text NOT NULL,
    surname         text NOT NULL,
    phone           text NOT NULL,
    email           text NOT NULL,
    CONSTRAINT PK_Professional PRIMARY KEY ( ID_professional )
);

drop table Enrollment;
CREATE TABLE Enrollment(
    ID_fa           integer NOT NULL,
    ID_professional integer NOT NULL,
    status          text NOT NULL,
    timeEn          datetime NOT NULL,
    CONSTRAINT PK_Enrollment PRIMARY KEY ( ID_fa, ID_professional ),
    CONSTRAINT AK1_Enrollment UNIQUE ( ID_fa, ID_professional ),
    CONSTRAINT FK_Enrollment_Professional FOREIGN KEY ( ID_professional ) REFERENCES Professional ( ID_professional ),
    CONSTRAINT FK_FormativeAction_Enrollment FOREIGN KEY ( ID_fa ) REFERENCES FormativeAction ( ID_fa ),
    CHECK ( status IN('received','confirmed','cancelled') )
);

drop table Payment;
CREATE TABLE Payment(
    ID_payment      integer NOT NULL,
    amount          real NOT NULL,
    datePay         date NOT NULL,
    sender          text NOT NULL,
    reciever        text NOT NULL,
    address         text NOT NULL,
    fiscalNumber    text NOT NULL,
    confirmed       boolean NOT NULL,
    ID_fa           integer NOT NULL,
    ID_professional integer NOT NULL,
    CONSTRAINT PK_Payment PRIMARY KEY ( ID_payment ),
    CONSTRAINT FK_104 FOREIGN KEY ( ID_fa, ID_professional ) REFERENCES Enrollment ( ID_fa, ID_professional )
);
