create database cs310;

drop table if exists Users;
create table Users (
    id int not null  unique primary key ,
    email varchar(50) not null unique ,
    password varchar(100) not null
);

drop table if exists Company;
create table Company (
    id int not null  unique primary key,
    name varchar(50) not null unique
);


drop table if exists Stock;
create table Stock (
    id int not null primary key ,
    company_id int not null ,
    user_id int not null ,
    shares float,
    foreign key (company_id) references Company(id) ,
    foreign key (user_id) references Users(id)

);


insert into Users values (1, 'tu1', 'tu1pass');