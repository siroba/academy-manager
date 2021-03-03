--For base project:
drop table FormativeAction;
create table FormativeAction(
ID_formativeAction int primary key not null, 
name String not null, 
objectives String not null, 
mainContents String not null, 
teacher String not null, 
remuneration int not null, 
location String not null, 
spaces int not null, 
spacesAvailable int not null, 
day date not null, 
numberOfHours int not null, 
enrollmentPeriodStart date not null, 
enrollmentPeriodEnd date not null,
fee int not null);

drop table Professional;
Create Table Professional(
ID_professional INTEGER PRIMARY KEY,
name VARCHAR (255) NOT NULL,
surname VARCHAR (255) NOT NULL,
phone VARCHAR (255) NOT NULL,
email VARCHAR (255) NOT NULL
);

drop table Enrollment;
CREATE TABLE Enrollment(
ID_Enrollment INTEGER PRIMARY KEY,
status BOOLEAN NOT NULL,
dateEn DATE NOT NULL,
name VARCHAR(255) NOT NULL,
ID_fa INTEGER NOT NULL,
ID_professional INTEGER NOT NULL,
CONSTRAINT FK_Enrollment_FormativeActions FOREIGN KEY(ID_fa) REFERENCES FormativeActions(ID_fa),
CONSTRAINT FK_Enrollment_Professional FOREIGN KEY(ID_professional ) REFERENCES Professional(ID_professional)

);

drop table Payment;
CREATE TABLE Payment(
ID_payment INTEGER PRIMARY KEY,
amount int NOT NULL,
datePay DATE NOT NULL,
ID_enrollment INTEGER NOT NULL UNIQUE, 
CONSTRAINT FK_Payment_Enrollment FOREIGN KEY(ID_enrollment) REFERENCES Enrollment(ID_Enrollment )
);