drop table if exists Base_User CASCADE;

create table base_user (
   id serial primary key unique not null ,
   email varchar(50) not null unique ,
   password varchar(100) not null
);

drop table if exists Company CASCADE;
create table Company (
     id serial primary key unique not null,
     ticker varchar(10) not null unique
);


drop table if exists Stock CASCADE;
create table Stock (
   id serial primary key unique not null ,
   company_id int not null ,
   user_id int not null ,
   shares int,
   purchased date not null ,
   sold date ,
   foreign key (company_id) references Company(id) ,
   foreign key (user_id) references Base_User(id)
);

insert into base_user (email, password) values ('tu1@email.com', 'tu1pass');
insert into company (ticker) values ('TSLA');
