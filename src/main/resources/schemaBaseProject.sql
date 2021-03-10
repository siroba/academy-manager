--For base project:
drop table Courses;
create table Courses(id int primary key not null, name String not null, day date not null, teacher String not null, location String not null, startTime int, endTime int, price int, spots int);

