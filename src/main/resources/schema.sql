--For base project:
drop table FormativeAction;
create table FormativeAction(id int primary key not null, name String not null, objectives String not null, mainContents String not null, teacher int not null, manager int not null, remuneration int, location String not null, spaces int not null, day date not null, numberOfHours int not null, enrollmentPeriodStart date not null, enrollmentPeriodEnd date not null, fee float not null);

drop table teacher;
create table Teacher(id int primary key not null, salary int, name String not null);