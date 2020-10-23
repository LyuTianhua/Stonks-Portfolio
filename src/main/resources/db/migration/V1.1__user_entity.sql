drop table if exists base_user;

create table base_user (
   id integer primary key not null,
   email varchar(50) not null unique ,
   password varchar(100) not null
);


drop table if exists Company;

create table Company (
     id integer primary key not null,
     ticker varchar(10) not null unique,
     data blob
);

drop table if exists Stock;

create table Stock (
   id integer primary key not null ,
   company_id int not null ,
   user_id int not null ,
   shares int,
   purchased date not null ,
   sold date ,
   data VARCHAR(65535) ,
   foreign key (company_id) references Company(id) ,
   foreign key (user_id) references Base_User(id)
);

insert into base_user (email, password) values ('tu1@email.com', 'tu1pass');

insert into base_user (email, password) values ('failed1@email.com', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
insert into base_user (email, password) values ('failed2@email.com', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
insert into base_user (email, password) values ('testuser1@email.com', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
insert into base_user (email, password) values ('testuser2@email.com', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
insert into base_user (email, password) values ('testuser3@email.com', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');

insert into company (ticker) values ('TSLA');

drop table if exists UserLoginRecord;
create table UserLoginRecord (
     id integer primary key not null ,
     user_id int not null ,
     datetime_accessed Date,
     foreign key (user_id) references base_user(id)
);
