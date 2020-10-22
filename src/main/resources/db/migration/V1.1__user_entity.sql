drop table if exists base_user;

create table base_user (
   id integer primary key not null,
   email varchar(50) not null unique ,
   password varchar(100) not null
);


drop table if exists Company;

create table Company (
     id integer primary key not null,
     name varchar(50),
     ticker varchar(10) not null unique
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
insert into company (ticker) values ('TSLA');
