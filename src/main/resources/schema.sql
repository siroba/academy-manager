--For base project:
drop table FormativeActions;
create table FormativeActions(name String primary key not null, objectives String not null, mainContents String not null, teacher String not null, remuneration int, location String not null, spaces int not null, day date not null, numberOfHours int not null, enrollmentPeriodStart date not null, enrollmentPeriodEnd date not null);

