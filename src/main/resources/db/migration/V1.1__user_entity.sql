create table BASE_USER (
  id serial PRIMARY KEY ,
  username varchar(255) not NULL ,
  password varchar(255) not NULL ,
  firstName varchar(255) ,
  lastName varchar(255)
);