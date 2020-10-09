drop table if exists Base_User CASCADE;

create table base_user (
   id serial primary key unique not null ,
   email varchar(50) not null unique ,
   password varchar(100) not null
);

drop table if exists Company CASCADE;
create table Company (
     id serial primary key unique not null,
     abbreviation varchar(10) not null unique ,
     name varchar(50)
);


drop table if exists Stock CASCADE;
create table Stock (
   id serial primary key unique not null ,
   company_id int not null ,
   user_id int not null ,
   shares float,
   foreign key (company_id) references Company(id) ,
   foreign key (user_id) references Base_User(id)
);

insert into base_user (email, password) values ('tu1@email.com', 'tu1pass');
insert into company (abbreviation, name) values ('TSLA', 'Tesla');
