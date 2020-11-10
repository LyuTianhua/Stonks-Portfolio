drop table if exists base_user;

create table base_user (
                           id integer primary key not null,
                           email varchar(50) not null unique ,
                           password varchar(100) not null,
                           data VARCHAR(65535)

);


drop table if exists Company;

create table Company (
                         id integer primary key not null,
                         ticker varchar(10) not null unique,
                         data varchar(65535),
                         timestamps blob
);

--drop table if exists historicalCompany;
--create table historicalCompany (
--                                   id integer primary key not null,
--                                   ticker varchar(10) not null unique
--);

drop table if exists Stock;

create table Stock (
                       id integer primary key not null ,
                       company_id int not null ,
                       user_id int not null ,
                       shares int,
                       purchased long not null ,
                       sold long ,
                       data varchar(65535),
                       foreign key (company_id) references Company(id) ,
                       foreign key (user_id) references Base_User(id)
);

drop table if exists historicalStock;
create table historicalStock (
	id integer primary key not null ,
	company_id int not null ,
	user_id int not null ,
	shares int,
	purchased long not null ,
	sold long ,
	data varchar(65535),
	foreign key (company_id) references historicalCompany(id) ,
	foreign key (user_id) references Base_User(id)
);

insert into base_user (email, password, data) values ('admin', 'force_allow', '0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
');
insert into base_user (email, password) values ('failed1@email.com', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
insert into base_user (email, password) values ('failed2@email.com', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
insert into base_user (email, password) values ('testuser1@email.com', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
insert into base_user (email, password) values ('testuser2@email.com', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
insert into base_user (email, password) values ('testuser3@email.com', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');


drop table if exists UserLoginRecord;
create table UserLoginRecord (
                                 id integer primary key not null ,
                                 user_id int not null ,
                                 datetime_accessed Date,
                                 foreign key (user_id) references base_user(id)
);