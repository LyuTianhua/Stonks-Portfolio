drop database if exists CSCI_310_PROJECT_V_2;
create database CSCI_310_PROJECT_V_2;

use CSCI_310_PROJECT_V_2;

create table Users (
    id int not null auto_increment unique primary key ,
    email varchar(50) not null unique key ,
    password varchar(100) not null
);

create table Company (
    id int not null auto_increment  unique primary key,
    name varchar(50) not null unique
);

create table Stock (
    id int not null auto_increment primary key ,
    company_id int not null ,
    user_id int not null ,
    shares float,
    foreign key (company_id) references Company(id) ,
    foreign key (user_id) references Users(id)

);


insert into Users values (1, 'tu1', 'tu1pass');