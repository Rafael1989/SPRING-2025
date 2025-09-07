show databases;                    # it will list the databases which are present

SHOW GLOBAL VARIABLES;

create database online_shopping;   # creating the database online_shopping

show databases;                    # now we can see our online_shopping database in the database list

use online_shopping;			   # start using the online_shopping database
show tables;                       # it will show NONE at present because we have not created any tables
# CREATE table
create table users(user_id int,
                   first_name varchar(255),
                   last_name varchar(255),
                   email_id varchar(255),
                   city varchar(255),
                   phone varchar(255));
 
describe users;

#INSERTING INTO 
insert into users values(10, 
                         'Claudia', 
                         'Sand', 
                         'Claudia.Sand@hotmail.com', 
                         'Hanover', 
                         '+1 555 234 5678');
                         
select * from users;

insert into users (first_name, last_name, phone,
           user_id, city, email_id) 
      values ('Julio', 'Chavez', '+1 555 890 9898',
          20, 'Savannah', 'chavezj317@gmail.com');
                    
select * from users;

insert into users values(15, 
                         "Neil", 
                         "Parks", 
                         "neil_parks82@hotmail.com", 
                         "Pleasanton", 
                         "+1 555 567 3456");

select * from users;

insert into users values(19, 'Gina' , 'Ortiz', 
                         'Gina_Ortiz68@hotmail.com', 'Hempstead', '+1 555 963 8521');
insert into users values(11, 'Raj' , 'Chawanda', 
                         'raj_chawanda@hotmail.com', 'Springfield', '+1 555 211 2563');

select * from users;

#Insert row with missing first name  
insert into users values(17, 
                         'MacKenzie', 
                         'zacharymackenzie9@gmail.com', 
                         'Nanavut', 
                         '+1 555 234 5678'); # Error

## Explicity state which column values are being specified
## The missing value is stored as NULL
insert into users (user_id, last_name, email_id,
                   city, phone) 
            values(10, 'MacKenzie', 'zacharymackenzie9@gmail.com', 
                   'Nanavut', '+1 555 234 5678');

select * from users;

## Explicitly specify values to be NULL
insert into users values(31, 
                         'Marcia', 
                         'Lin', 
                         NULL, 
                         'Springfield', 
                         NULL);
                         
select * from users;

# Inserting a wrong datatype, here User_Id is int and we are trying to insert a string
# This will result in an error
insert into users values('TEN', 
                         'Claudia', 
                         'Sand', 
                         'Claudia.Sand@hotmail.com', 
                         'Hanover', 
                         '+1 555 234 5678');  


#Insert duplicate value
insert into users values(10, 
                         'Claudia', 
                         'Sand', 
                         'Claudia.Sand@hotmail.com', 
                         'Hanover', 
                         '+1 555 234 5678');

select * from users;

insert into users values(10, 
                         'Claudia', 
                         NULL, 
                         'Claudia.Sand@hotmail.com', 
                         NULL, 
                         '+1 555 234 5678');

select * from users;

describe users;

truncate table users;

select * from users;

describe users;

drop table users;

describe users;

show tables;


##################################################################
##################################################################
##################################################################


# Creating table with NOT NULL and UNIQUE values
create table users(user_id int NOT NULL unique,
                   first_name varchar(255)NOT NULL,
                   last_name varchar(255),
                   email_id varchar(255),
                   city varchar(255),
                   phone varchar(255) NOT NULL);

describe users;

select * from users;

insert into users values(10, 'Claudia', 'Sand', 
                         'Claudia.Sand@hotmail.com', 'Hanover', 
                         '+1 555 234 5678');

insert into users values (20, 'Julio', 'Chavez',
                          'chavezj317@gmail.com', 'Savannah', 
                          '+1 555 890 9898' );

insert into users values(15, 'Neil', 'Parks', 
                         'neil_parks82@hotmail.com', 'Pleasanton', 
                         '+1 555 567 3456');

select * from users;

## Inserting a duplicate row will result in an error
insert into users values (20, 'Julio', 'Chavez',
                          'chavezj317@gmail.com', 'Savannah', 
                          '+1 555 890 9898' );

## Even the id alone cannot be duplicated
insert into users values(20, 'Gina' , 'Ortiz', 
                         'Gina_Ortiz68@hotmail.com', 'Hempstead', 
                         '+1 555 963 8521');

## This time the value gets loaded into the table
insert into users values(19, 'Gina' , 'Ortiz', 
                         'Gina_Ortiz68@hotmail.com', 'Hempstead', 
                         '+1 555 963 8521');

select * from users;

## Check the constraints for the different columns
describe users;

## Test out the NOT NULL constraints by inserting null values into them
insert into users values(11, NULL , 'Chawanda', 
                         'raj_chawanda@hotmail.com', 'Springfield', 
                         '+1 555 211 2563');

insert into users values(11, 'Raj' , 'Chawanda', 
                         'raj_chawanda@hotmail.com', 'Springfield', 
                         NULL);

insert into users values(NULL, 'Raj' , 'Chawanda', 
                         'raj_chawanda@hotmail.com', 'Springfield', 
                         '+1 555 211 2563');

## Insert valid values to add a new row to the table
insert into users values(11, 'Raj' , 'Chawanda', 
                         'raj_chawanda@hotmail.com', 'Springfield', 
                         '+1 555 211 2563');

select * from users;

## The fields which have not been constrained with NOT NULL can still be NULL
## The city field does not have a UNIQUE constraint, 
## so we can have multiple occurrences of the same city
insert into users values(31, 'Marcia', NULL, 
                         NULL, 
                         'Springfield', 
                         '+1 555 234 8765');

select * from users;


drop table users;
    
    # creating table with primary key 
create table users(user_id int,
                   first_name varchar(255)NOT NULL,
                   last_name varchar(255),
                   email_id varchar(255),
                   city varchar(255),
                   phone varchar(255) NOT NULL, 
                   primary key(user_id));

describe users;

insert into users values(10, 'Claudia', 'Sand', 
                         'Claudia.Sand@hotmail.com', 'Hanover',
                         '+1 555 234 5678');
insert into users values (20, 'Julio', 'Chavez',
                          'chavezj317@gmail.com', 'Savannah', 
                          '+1 555 890 9898' );
insert into users values(15, 'Neil', 'Parks', 
                         'neil_parks82@hotmail.com', 'Pleasanton', 
                         '+1 555 567 3456');

## Inserting a duplicate ids will result in an error
insert into users values(20, 'Gina' , 'Ortiz', 
                         'Gina_Ortiz68@hotmail.com', 'Hempstead', 
                         '+1 555 963 8521');

## A NULL id is also not valid
insert into users values(NULL, 'Gina' , 'Ortiz', 
                         'Gina_Ortiz68@hotmail.com', 'Hempstead', 
                         '+1 555 963 8521');

## This time the value gets loaded into the table
insert into users values(19, 'Gina' , 'Ortiz', 
                         'Gina_Ortiz68@hotmail.com', 'Hempstead', 
                         '+1 555 963 8521');

insert into users values(11, 'Raj' , 'Chawanda', 
                         'raj_chawanda@hotmail.com', 'Springfield', 
                         '+1 555 211 2563');
insert into users values(31, 'Marcia', 'Lin', 
                         'Marcia_Lin89@hotmail.com', 
                         'Springfield', 
                         '+1 555 234 8765');

select * from users;


##################################################################
##################################################################
##################################################################

select * from users;                         

select last_name, city, phone
from users;

select user_id, first_name, city
from users;
                         
select user_id, first_name, city
from users
where city = 'Springfield';

select user_id, first_name, last_name
from users
where city = 'Springfield';

select *
from users
where city = 'Springfield';



## If no rows match the condition, nothing is returned
select *
from users
where city = 'Calgary';

select * from users; 

describe users;

alter table users 
modify column email_id varchar(255) unique;

describe users;

## Inserting a duplicate email fails on this occassion
insert into users values(33, 'Chen', 'Lin', 
                         'Marcia_Lin89@hotmail.com', 
                         'Moab', 
                         '+1 555 111 0357');

## Modify the email address, and it works
insert into users values(33, 'Chen', 'Lin', 
                         'Chen.LinUK@gmail.com', 
                         'Moab', 
                         '+1 555 111 0357');

select * from users;


## Note that the phone number is not unique
describe users;

## Add a new user using an already registered phone number
insert into users values(34, 'Charles', 'Lin', 
                         'chlin@qenel.com', 
                         'Moab', 
                         '+1 555 111 0357');

select * from users;

## This alter table statement fails 
alter table users 
modify column phone varchar(255) unique;

delete from users
where user_id = 34;

select * from users;

alter table users 
modify column phone varchar(255) unique;

describe users;

## Inserting this row will fail due to the UNIQUE constraint on phone
insert into users values(34, 'Charles', 'Lin', 
                         'chlin@qenel.com', 
                         'Moab', 
                         '+1 555 111 0357');

## To drop the UNIQUE constraint, we first need to know what it is called
## Run the query below, and from the results, right-click on the
## CREATE TABLE statement and choose "Open Value in Viewer"
show create table users;

## To get rid of the UNIQUE contraint, use the DROP index statement
drop index phone on users;

describe users;

show create table users;

## Now, the entry with the row with the duplicate phone number can be inserted
insert into users values(34, 'Charles', 'Lin', 
                         'chlin@qenel.com', 
                         'Moab', 
                         '+1 555 111 0357');

select * from users;

## Update the phone number of a user
update users 
set phone = '+1 555 111 9999'
where user_id = 34;

select * from users;

## Updating should ideally be done based on the key column
update users 
set email = 'Gina@qenel.com'
where first_name = 'Gina';

## Updating one column for the whole table is not allowed by default
update users 
set phone= '+1 555 555 555';

select user_id, first_name, city
from users;

## Creating a copy table
create table users_summary  AS
select user_id, first_name, city
from users;

show tables;

describe users_summary;

select * from users_summary;

alter table users_summary
add primary key(user_id);

describe users_summary;

drop table users_summary;

show tables;

describe users;

alter table users      
DROP COLUMN phone;

describe users;

select * from users;

drop table users;

drop database online_shopping;



	



